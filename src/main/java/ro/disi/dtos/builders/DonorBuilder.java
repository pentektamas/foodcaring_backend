package ro.disi.dtos.builders;

import ro.disi.dtos.DonorDTO;
import ro.disi.entities.Account;
import ro.disi.entities.Restaurant;
import ro.disi.entities.Donor;
import ro.disi.utils.Role;

public class DonorBuilder {

    public static Donor toDonor(DonorDTO donorDTO) {
        Account donorAccount = new Account(donorDTO.getUsername(), donorDTO.getPassword(), Role.DONOR);
        return new Donor(donorDTO.getId(), donorDTO.getFirstName(), donorDTO.getLastName(),
                donorDTO.getLocation(), donorDTO.getPhoneNumber(),
                donorAccount, donorDTO.getHasDonated());
    }

    public static DonorDTO toDonorDTO(Donor donor) {
        return new DonorDTO(donor.getId(), donor.getFirstName(), donor.getLastName(),
                donor.getLocation(), donor.getPhoneNumber(),
                donor.getAccount().getUsername(), donor.getAccount().getPassword(), donor.getHasDonated());
    }
}
