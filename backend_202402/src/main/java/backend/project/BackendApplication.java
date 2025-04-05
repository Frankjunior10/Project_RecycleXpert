package backend.project;

import backend.project.dtos.EventTypeDTO;
import backend.project.dtos.UserDTO;
import backend.project.dtos.VolunteerDTO;
import backend.project.entities.Authority;
import backend.project.entities.EventType;
import backend.project.entities.Organization;
import backend.project.entities.User;
import backend.project.repositories.AuthorityRepository;
import backend.project.repositories.OrganizationRepository;
import backend.project.repositories.UserRepository;
import backend.project.repositories.VolunteerRepository;
import backend.project.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(
		VolunteerRepository volunteerRepository,
		UserRepository userRepository,
		OrganizationRepository organizationRepository,
		UserService userService,
		AuthorityService authorityService,
		VolunteerService volunteerService,
		EventTypeService eventTypeService,
		OrganizationService organizationService

	) {
		return args -> {
			authorityService.addAuthority(new Authority(0L,"VOLUNTARIO",null));
			authorityService.addAuthority(new Authority(0L,"ORGANIZACION",null));

			// Crear un usuario
			userService.addUser(new UserDTO(0L,"Frank","123","VOLUNTARIO"));
			userService.addUser(new UserDTO(0L,"Luis","321","ORGANIZACION"));
			authorityService.addAuthority(new Authority(0L,"ADMINISTRADOR",null));


			// Crear voluntarios de prueba

			volunteerService.addVolunteer(new VolunteerDTO(0L, "Ana Pérez", "ana@example.com", "Av. Siempre Viva 123", 50, "Reciclaje", 1, 1L));
			volunteerService.addVolunteer(new VolunteerDTO(0L, "Carlos López", "carlos@example.com", "Calle Falsa 456", 30, "Reciclaje", 2, 2L));
			volunteerService.addVolunteer(new VolunteerDTO(0L, "María Gómez", "maria@example.com", "Calle Real 789", 80, "Educación", 1, 3L));



			// Crear tipos de eventos
			eventTypeService.addEventType(new EventType(0L, "Reciclaje", "Evento de reciclaje comunitario", 10, null));
			eventTypeService.addEventType(new EventType(0L, "Educación", "Charla educativa", 20, null));
			eventTypeService.addEventType(new EventType(0L, "Salud", "Campaña de salud", 15, null));


			// Crear Organización
			Organization org1 = new Organization();
			org1.setOrganizationName("Green Future");
			org1.setOrgType("Non-Profit");
			org1.setContactInfo("contact@greenfuture.org");
			org1.setCollaborationArea("Environmental Protection");

			Organization org2 = new Organization();
			org2.setOrganizationName("Education for All");
			org2.setOrgType("NGO");
			org2.setContactInfo("info@educationforall.org");
			org2.setCollaborationArea("Educational Outreach");

			Organization org3 = new Organization();
			org3.setOrganizationName("HealthFirst");
			org3.setOrgType("Healthcare Provider");
			org3.setContactInfo("support@healthfirst.com");
			org3.setCollaborationArea("Community Health");

			// Guardar organizaciones en el repositorio
			organizationRepository.save(org1);
			organizationRepository.save(org2);
			organizationRepository.save(org3);

			System.out.println("Organizaciones creadas con éxito:");
			System.out.println("1. " + org1.getOrganizationName());
			System.out.println("2. " + org2.getOrganizationName());
			System.out.println("3. " + org3.getOrganizationName());

			System.out.println("Tipos de eventos y voluntarios de prueba creados con éxito.");
			System.out.println("Tipos de eventos creados con éxito.");
			System.out.println("Voluntarios de prueba creados con éxito.");
		};


	}

}
