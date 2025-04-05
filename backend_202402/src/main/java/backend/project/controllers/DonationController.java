package backend.project.controllers;

import backend.project.dtos.DonationDTO;
import backend.project.entities.Donation;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.serviceimpl.DonationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class DonationController {
    @Autowired
    private DonationServiceImpl donationService;

    // Crear una donación para un evento específico
    @PostMapping("/donations")
    public ResponseEntity<DonationDTO> createDonation(@RequestBody DonationDTO donationDTO) {
        try {
            // Llama al servicio para procesar el DTO
            Donation newDonation = donationService.createDonationFromDTO(donationDTO);

            // Convierte la entidad en un DTO antes de devolverla
            DonationDTO responseDTO = donationService.convertToDTO(newDonation);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las donaciones para un evento específico
    @GetMapping("/donations/event/{eventId}")
    public ResponseEntity<List<DonationDTO>> getAllDonationsForEvent(@PathVariable Long eventId) {
        try {
            List<Donation> donations = donationService.getAllDonationsForEvent(eventId);

            // Convierte las entidades en DTOs
            List<DonationDTO> donationDTOs = donations.stream()
                    .map(donationService::convertToDTO)
                    .toList();

            return new ResponseEntity<>(donationDTOs, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

