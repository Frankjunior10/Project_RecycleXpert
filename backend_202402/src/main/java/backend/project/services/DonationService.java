package backend.project.services;

import backend.project.entities.Donation;

import java.util.List;

public interface DonationService {

    public Donation createDonation(Long eventId, Donation donation);
    public List<Donation> getAllDonationsForEvent(Long eventId);
    public Donation getDonationById(Long donationId);
}
