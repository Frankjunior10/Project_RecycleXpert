package backend.project.serviceimpl;

import backend.project.dtos.VolunteerDTO;
import backend.project.dtos.VolunteerProgressDTO;
import backend.project.entities.Consultation;
import backend.project.entities.Event;
import backend.project.entities.RegisterEvent;
import backend.project.entities.User;
import backend.project.entities.Volunteer;
import backend.project.exceptions.InvalidActionException;
import backend.project.exceptions.KeyRepeatedDataException;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.UserRepository;
import backend.project.repositories.VolunteerRepository;
import backend.project.services.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private UserRepository userRepository;


    public boolean isNameDuplicated(String volunteerName){
        List<Volunteer> listNombreDuplicados = volunteerRepository.findByVolunteerName(volunteerName);
        return (listNombreDuplicados.size()>0);
    }

    // Modificar esta parte para que Voluntarios liste viendo si tiene usuario
    @Override
    public List<Volunteer> listAllVolunteers() {
        return volunteerRepository.findAll();
    }

    // PUNTOS QUE ACUMULAN LOS VOLUNTARIOS ------
    // Método para añadir puntos y actualizar el nivel del voluntario
    public void addPointsToVolunteer(Long volunteerId, int points) {
        Volunteer volunteer = findById(volunteerId);

        // Añadir puntos al voluntario
        volunteer.setPoints(volunteer.getPoints() + points);

        // Actualizar nivel basado en los puntos acumulados
        updateLevel(volunteer);

        // Guardar los cambios en la base de datos
        volunteerRepository.save(volunteer);
    }

    @Override
    public VolunteerProgressDTO getVolunteerProgress(Long volunteerId) {
        return null;
    }

    // Lógica para actualizar el nivel del voluntario según sus puntos
    private void updateLevel(Volunteer volunteer) {
        int points = volunteer.getPoints();
        int newLevel = calculateLevel(points); // Método para calcular el nivel según los puntos

        if (newLevel != volunteer.getLevel()) {
            volunteer.setLevel(newLevel);
        }
    }
    // Método que define el nivel basado en el total de puntos
    private int calculateLevel(int points) {
        if (points >= 1000) return 5;
        else if (points >= 500) return 4;
        else if (points >= 250) return 3;
        else if (points >= 100) return 2;
        else return 1;
    }
    //------------------
    public int getVolunteerPoints(Long id) {
        Volunteer volunteer = findById(id);
        return volunteer.getPoints();
    }

    @Override
    public Volunteer addVolunteer(VolunteerDTO volunteerdto) {

        //NOTA - FALTA COLOCAR LOS CAMPOS DE CATEGORIA, LEVEL , NIVEL
        Volunteer volunteer = new Volunteer();
        volunteer.setVolunteerName(volunteerdto.getVolunteerName());
        volunteer.setEmail(volunteerdto.getEmail());
        volunteer.setAddress(volunteerdto.getAddress());

        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer findById(Long id){
        Volunteer volunteerFound = volunteerRepository.findById(id).orElse(null);
        if(volunteerFound == null){
            throw new ResourceNotFoundException("Volunteer with id: " + id + " not found");
        }
        return volunteerFound;
    }

    @Override
    public void deleteVolunteer(Long id){
        Volunteer volunteerFound = volunteerRepository.findById(id).orElse(null);
        if (volunteerFound==null) {
            throw new ResourceNotFoundException("No se puede encontrar la venta con id: "+ id);
        }
        volunteerRepository.delete(volunteerFound);
    }

    @Override
    public Volunteer updateVolunteer(Long id, VolunteerDTO volunteerdto) {
        Volunteer volunteerFound = findById(id); // Busca el voluntario por ID, o lanza una excepción si no lo encuentra.

        // Actualizar el nombre si no es nulo y no está duplicado
        if(volunteerdto.getVolunteerName() != null && !volunteerdto.getVolunteerName().equals(volunteerFound.getVolunteerName())) {
            if (!isNameDuplicated(volunteerdto.getVolunteerName())) {
                volunteerFound.setVolunteerName(volunteerdto.getVolunteerName());
            } else {
                throw new KeyRepeatedDataException("Volunteer name: " + volunteerdto.getVolunteerName() + " cannot be duplicated.");
            }
        }
        // Actualizar el email si no es nulo
        if(volunteerdto.getEmail() != null && !volunteerdto.getEmail().equals(volunteerFound.getEmail())) {
            volunteerFound.setEmail(volunteerdto.getEmail());
        }
        // Actualizar la dirección si no es nula
        if(volunteerdto.getAddress() != null && !volunteerdto.getAddress().equals(volunteerFound.getAddress())) {
            volunteerFound.setAddress(volunteerdto.getAddress());
        }
        // Guardar los cambios en la base de datos
        return volunteerRepository.save(volunteerFound);
    }

    public List<Event> getVolunteerRegisteredEvents(Long volunteerId) {
        // Esto es para buscar el voluntario por su ID
        Volunteer volunteer = findById(volunteerId);

        // Verificamos que el voluntario tenga eventos registrados
        if (volunteer.getRegisterEvents() != null && !volunteer.getRegisterEvents().isEmpty()) {
            // Mapeamos los eventos de los registros de eventos
            return volunteer.getRegisterEvents()
                    .stream()
                    .map(RegisterEvent::getEvent)
                    .collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException("No registered events found for volunteer with id: " + volunteerId);
        }
    }
}
