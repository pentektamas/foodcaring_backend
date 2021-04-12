package ro.disi.dtos.builders;

import ro.disi.dtos.PersonDTO;
import ro.disi.entities.*;

public class PersonBuilder {
    public static Admin toAdmin(PersonDTO personDTO) {
        Account adminAccount = new Account(personDTO.getUsername(), personDTO.getPassword(), personDTO.getRole());
        return new Admin(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getLocation(), personDTO.getPhoneNumber(), adminAccount);
    }

    public static DisadvantagedPerson toDisadvantagedPerson(PersonDTO personDTO) {
        Account disadvantagedPersonAccount = new Account(personDTO.getUsername(), personDTO.getPassword(), personDTO.getRole());
        return new DisadvantagedPerson(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getLocation(), personDTO.getPhoneNumber(), disadvantagedPersonAccount);
    }

    public static Donor toDonor(PersonDTO personDTO) {
        Account donorAccount = new Account(personDTO.getUsername(), personDTO.getPassword(), personDTO.getRole());
        return new Donor(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getLocation(), personDTO.getPhoneNumber(), donorAccount);
    }

    public static RestaurantResponsible toRestaurantResponsible(PersonDTO personDTO) {
        Account restaurantResponsibleAccount = new Account(personDTO.getUsername(), personDTO.getPassword(), personDTO.getRole());
        return new RestaurantResponsible(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getLocation(), personDTO.getPhoneNumber(), restaurantResponsibleAccount);
    }
}
