package ro.disi.dtos.builders;

import ro.disi.dtos.DisadvantagedPersonDTO;
import ro.disi.dtos.DonationDTO;
import ro.disi.entities.DisadvantagedPerson;
import ro.disi.entities.Donation;
import ro.disi.entities.Donor;
import ro.disi.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DonationBuilder {

    public DonationBuilder() {
    }

    public static DonationDTO toDonationDTO(Donation donation) {
        if (donation == null) return null;
        Set<DisadvantagedPersonDTO> disadvantagedPersonDTOS = donation.getDisadvantagedPersonList().stream()
                .map(DisadvantagedPersonBuilder::toDisadvantagedPersonDtoWithPriority).collect(Collectors.toSet());

        return new DonationDTO(
                donation.getId(),
                MenuBuilder.toMenuDTO(donation.getMenu()),
                RestaurantBuilder.toRestaurantDTO(donation.getRestaurant()),
                disadvantagedPersonDTOS,
                DonorBuilder.toDonorDTO(donation.getDonor()), donation.getDate()
        );
    }

    public static Donation toEntity(DonationDTO donationDTO) {
        if (donationDTO == null) return null;
        Set<DisadvantagedPerson> disadvantagedPeople = donationDTO.getDisadvantagedPersons().stream()
                .map(DisadvantagedPersonBuilder::toEntityWithId).collect(Collectors.toSet());

        return new Donation(
                MenuBuilder.toEntityWithId(donationDTO.getMenu()),
                RestaurantBuilder.toEntityWithId(donationDTO.getRestaurant()),
                disadvantagedPeople,
                DonorBuilder.toDonor(donationDTO.getDonor()), donationDTO.getDate()
        );
    }

    public static Donation toEntityWithId(DonationDTO donationDTO) {
        if (donationDTO == null) return null;
        Set<DisadvantagedPerson> disadvantagedPeople = donationDTO.getDisadvantagedPersons().stream()
                .map(DisadvantagedPersonBuilder::toEntityWithId).collect(Collectors.toSet());

        return new Donation(
                donationDTO.getId(),
                MenuBuilder.toEntityWithId(donationDTO.getMenu()),
                RestaurantBuilder.toEntityWithId(donationDTO.getRestaurant()),
                disadvantagedPeople,
                DonorBuilder.toDonor(donationDTO.getDonor()), donationDTO.getDate()
        );
    }

}
