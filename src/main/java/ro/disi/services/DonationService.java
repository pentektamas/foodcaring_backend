package ro.disi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.DonationDTO;
import ro.disi.dtos.RestaurantDTO;
import ro.disi.dtos.builders.DonationBuilder;
import ro.disi.entities.Donation;
import ro.disi.entities.Menu;
import ro.disi.repositories.DonationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DonationService {
    private final DonationRepository donationRepository;
    private final RestaurantService restaurantService;
    private static final Logger LOGGER = LoggerFactory.getLogger(DonationService.class);


    @Autowired
    public DonationService(DonationRepository donationRepository, RestaurantService restaurantService) {
        this.donationRepository = donationRepository;
        this.restaurantService = restaurantService;
    }

    public List<DonationDTO> findDonations() {
        List<Donation> donations = donationRepository.findAll();
        return donations
                .stream()
                .map(DonationBuilder::toDonationDTO)
                .collect(Collectors.toList());
    }

    public DonationDTO findDonationById(UUID uuid) {
        Optional<Donation> donationOptional = donationRepository.findById(uuid);
        if (!donationOptional.isPresent()) {
            LOGGER.error("Donation with id {} was not found in db", uuid);
            throw new ResourceNotFoundException(Menu.class.getSimpleName() + "with id" + uuid);
        }
        return DonationBuilder.toDonationDTO(donationOptional.get());
    }

    public UUID insertDonation(DonationDTO donationDTO) {
        Donation donation = DonationBuilder.toEntity(donationDTO);
        donation = donationRepository.save(donation);
        LOGGER.debug("Donation with id {} was inserted in db", donation.getId());
        return donation.getId();
    }

    public UUID deleteDonation(UUID id) {
        Optional<Donation> donationOptional = donationRepository.findById(id);
        if (!donationOptional.isPresent()) {
            LOGGER.error("Donation with id {} was not found in db", id);
            throw new ResourceNotFoundException(Donation.class.getSimpleName() + " with id: " + id);
        }
        donationRepository.deleteById(id);
        return id;
    }

    public DonationDTO updateDonation(DonationDTO donationDTO) {
        Donation donation = DonationBuilder.toEntityWithId(donationDTO);
        Optional<Donation> donationOptional = donationRepository.findById(donation.getId());
        if (!donationOptional.isPresent()) {
            LOGGER.error("Donation with id {} was not found in db", donation.getId());
            throw new ResourceNotFoundException(Donation.class.getSimpleName() + " with id: " + donation.getId());
        }
        Donation updatedDonation = donationRepository.save(donation);
        LOGGER.debug("Donation with id {} was updated in db", donation.getId());
        return DonationBuilder.toDonationDTO(updatedDonation);
    }
}
