package backend.project.serviceimpl;

import backend.project.dtos.DonationDTO;
import backend.project.entities.Donation;
import backend.project.entities.Event;
import backend.project.entities.Volunteer;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.DonationRepository;
import backend.project.repositories.EventRepository;
import backend.project.repositories.VolunteerRepository;
import backend.project.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class DonationServiceImpl implements DonationService {
    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    public Donation createDonationFromDTO(DonationDTO donationDTO) {
        // Encuentra el evento y el voluntario
        Event event = eventRepository.findById(donationDTO.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event with id " + donationDTO.getEventId() + " not found"));

        Volunteer volunteer = volunteerRepository.findById(donationDTO.getVolunteerId())
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer with id " + donationDTO.getVolunteerId() + " not found"));

        // Crea una nueva entidad Donation
        Donation donation = new Donation();
        donation.setDonorName(donationDTO.getDonorName());
        donation.setAmount(donationDTO.getAmount());
        donation.setDonationDate(new Date());
        donation.setEvent(event);
        donation.setVolunteer(volunteer);

        return donationRepository.save(donation);
    }

    public DonationDTO convertToDTO(Donation donation) {
        DonationDTO dto = new DonationDTO();
        dto.setId(donation.getId());
        dto.setDonorName(donation.getDonorName());
        dto.setAmount(donation.getAmount());
        dto.setDonationDate(donation.getDonationDate());
        dto.setEventId(donation.getEvent().getId());
        dto.setVolunteerId(donation.getVolunteer().getId());
        return dto;
    }

    @Override
    public Donation createDonation(Long eventId, Donation donation) {
        return null;
    }

    @Override
    public List<Donation> getAllDonationsForEvent(Long eventId) {
        return List.of();
    }

    @Override
    public Donation getDonationById(Long donationId) {
        return null;
    }
}

