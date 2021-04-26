package ro.disi.dtos.builders;

import ro.disi.dtos.*;
import ro.disi.entities.*;

public class DisadvantagedPersonBuilder {


    private DisadvantagedPersonBuilder() {
    }

    public static DisadvantagedPerson toEntityWithId(DisadvantagedPersonDTO disadvantagedPersonDTO) {
        Account accountDisadvantagePerson = new Account(disadvantagedPersonDTO.getUsername(), disadvantagedPersonDTO.getPassword(), disadvantagedPersonDTO.getRole());
        return new DisadvantagedPerson(disadvantagedPersonDTO.getId(), disadvantagedPersonDTO.getFirstName(), disadvantagedPersonDTO.getLastName(), disadvantagedPersonDTO.getLocation(), disadvantagedPersonDTO.getPhoneNumber(), accountDisadvantagePerson, disadvantagedPersonDTO.getPriority(), disadvantagedPersonDTO.getAllergies());
    }

    public static DisadvantagedPerson toEntity(DisadvantagedPersonDTO disadvantagedPersonDTO) {
        Account accountDisadvantagePerson = new Account(disadvantagedPersonDTO.getUsername(), disadvantagedPersonDTO.getPassword(), disadvantagedPersonDTO.getRole());
        return new DisadvantagedPerson(
                disadvantagedPersonDTO.getFirstName(),
                disadvantagedPersonDTO.getLastName(),
                disadvantagedPersonDTO.getLocation(),
                disadvantagedPersonDTO.getPhoneNumber(),
                accountDisadvantagePerson,
                disadvantagedPersonDTO.getPriority(), disadvantagedPersonDTO.getAllergies());
    }

    public static DisadvantagedPersonDTO toDisadvantagedPersonDTO(DisadvantagedPerson disadvantagedPerson) {
        return new DisadvantagedPersonDTO(disadvantagedPerson.getId(), disadvantagedPerson.getFirstName(), disadvantagedPerson.getLastName(), disadvantagedPerson.getLocation(), disadvantagedPerson.getPhoneNumber(), disadvantagedPerson.getAccount().getUsername(), disadvantagedPerson.getAccount().getPassword(), disadvantagedPerson.getAccount().getRole(), disadvantagedPerson.getAllergies());
    }

    public static DisadvantagedPersonDTO toDisadvantagedPersonDtoWithPriority(DisadvantagedPerson disadvantagedPerson) {
        return new DisadvantagedPersonDTO(disadvantagedPerson.getId(), disadvantagedPerson.getFirstName(), disadvantagedPerson.getLastName(), disadvantagedPerson.getLocation(), disadvantagedPerson.getPhoneNumber(), disadvantagedPerson.getAccount().getUsername(), disadvantagedPerson.getAccount().getPassword(), disadvantagedPerson.getAccount().getRole(), disadvantagedPerson.getPriority(), disadvantagedPerson.getAllergies());
    }

}
