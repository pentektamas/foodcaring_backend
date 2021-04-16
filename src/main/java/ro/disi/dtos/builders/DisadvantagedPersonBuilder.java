package ro.disi.dtos.builders;

import ro.disi.dtos.DisadvantagedPersonDTO;
import ro.disi.dtos.PersonDummyDTO;
import ro.disi.dtos.PersonDummyDetailsDTO;
import ro.disi.entities.Account;
import ro.disi.entities.DisadvantagedPerson;
import ro.disi.entities.PersonDummy;

public class DisadvantagedPersonBuilder {



    private DisadvantagedPersonBuilder() {
    }

  /*  public static DisadvantagedPersonDTO toDisadvantagedPersonDTO(DisadvantagedPerson  disadvantagedPerson) {
        return new DisadvantagedPersonDTO(disadvantagedPerson.getId(), disadvantagedPerson.getFirstName(), disadvantagedPerson.getLastName(),disadvantagedPerson.getLocation(),disadvantagedPerson.getPhoneNumber()
                ,disadvantagedPerson.getAccount().getUsername(),disadvantagedPerson.getAccount().getPassword(),disadvantagedPerson.getAccount().getRole());
    }
*/
    public static PersonDummyDetailsDTO toPersonDummyDetailsDTO(PersonDummy person) {
        return new PersonDummyDetailsDTO(person.getId(), person.getName(), person.getAddress(), person.getAge());
    }

    public static DisadvantagedPerson toEntity(DisadvantagedPersonDTO disadvantagedPersonDTO) {
        Account accountDisadvantagePerson = new Account(disadvantagedPersonDTO.getUsername(), disadvantagedPersonDTO.getPassword(),disadvantagedPersonDTO.getRole());
        return new DisadvantagedPerson(
                disadvantagedPersonDTO.getFirstName(),
                disadvantagedPersonDTO.getLastName(),
                disadvantagedPersonDTO.getLocation(),
                disadvantagedPersonDTO.getPhoneNumber(),
                accountDisadvantagePerson);
    }
}
