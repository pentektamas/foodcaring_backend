package ro.disi.dtos.builders;

import ro.disi.dtos.DisadvantagedPersonDTO;
import ro.disi.dtos.DonationDTO;
import ro.disi.entities.DisadvantagedPerson;
import ro.disi.entities.Donation;
import ro.disi.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class DonationBuilder {

    public DonationBuilder() {
    }

    public static DonationDTO toDonationDTO(Donation donation) {
        List<DisadvantagedPersonDTO> disadvantagedPersonDTOS = new ArrayList<>();
        List<DisadvantagedPerson> disadvantagedPeople = donation.getDisadvantagedPersonList();

        if (disadvantagedPeople != null) {
            for (DisadvantagedPerson disadvantagedPerson : disadvantagedPeople) {
                disadvantagedPersonDTOS.add(DisadvantagedPersonBuilder.toDisadvantagedPersonDTO(disadvantagedPerson));
            }
        }

        return new DonationDTO(
                donation.getId(),
                MenuBuilder.toMenuDTO(donation.getMenu()),
                RestaurantBuilder.toRestaurantDTO(donation.getRestaurant()),
                disadvantagedPersonDTOS
        );
    }

    public static Donation toEntity(DonationDTO donationDTO) {
        List<DisadvantagedPerson> disadvantagedPeople = new ArrayList<>();
        List<DisadvantagedPersonDTO> disadvantagedPersonDTOS = donationDTO.getDisadvantagedPersons();

        if (disadvantagedPersonDTOS != null) {
            for (DisadvantagedPersonDTO disadvantagedPersonDTO : disadvantagedPersonDTOS) {
                disadvantagedPeople.add(DisadvantagedPersonBuilder.toEntityWithId(disadvantagedPersonDTO));
            }
        }
        System.out.println(donationDTO.getMenu());
        return new Donation(
                MenuBuilder.toEntityWithId(donationDTO.getMenu()),
                RestaurantBuilder.toEntityWithId(donationDTO.getRestaurant()),
                disadvantagedPeople
        );
    }

    public static Donation toEntityWithId(DonationDTO donationDTO) {
        List<DisadvantagedPerson> disadvantagedPeople = new ArrayList<>();
        List<DisadvantagedPersonDTO> disadvantagedPersonDTOS = donationDTO.getDisadvantagedPersons();

        if (disadvantagedPersonDTOS != null) {
            for (DisadvantagedPersonDTO disadvantagedPersonDTO : disadvantagedPersonDTOS) {
                disadvantagedPeople.add(DisadvantagedPersonBuilder.toEntityWithId(disadvantagedPersonDTO));
            }
        }

        return new Donation(
                donationDTO.getId(),
                MenuBuilder.toEntityWithId(donationDTO.getMenu()),
                RestaurantBuilder.toEntityWithId(donationDTO.getRestaurant()),
                disadvantagedPeople
        );
    }
}
