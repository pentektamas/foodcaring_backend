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
        return new Donor(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getLocation(), personDTO.getPhoneNumber(), donorAccount, false);
    }

    public static RestaurantResponsible toRestaurantResponsible(PersonDTO personDTO) {
        Account restaurantResponsibleAccount = new Account(personDTO.getUsername(), personDTO.getPassword(), personDTO.getRole());
        return new RestaurantResponsible(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getLocation(), personDTO.getPhoneNumber(), restaurantResponsibleAccount);
    }
    public static PersonDTO toPerson(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setLocation(person.getLocation());
        personDTO.setPhoneNumber(person.getPhoneNumber());
        personDTO.setRole(person.getAccount().getRole());
        personDTO.setUsername(person.getAccount().getUsername());
        return personDTO;
    }
}
