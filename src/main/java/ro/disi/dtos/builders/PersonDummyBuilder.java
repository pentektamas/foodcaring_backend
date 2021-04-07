package ro.disi.dtos.builders;

import ro.disi.dtos.PersonDummyDetailsDTO;
import ro.disi.entities.PersonDummy;
import ro.disi.dtos.PersonDummyDTO;

public class PersonDummyBuilder {

    private PersonDummyBuilder() {
    }

    public static PersonDummyDTO toPersonDummyDTO(PersonDummy person) {
        return new PersonDummyDTO(person.getId(), person.getName(), person.getAge());
    }

    public static PersonDummyDetailsDTO toPersonDummyDetailsDTO(PersonDummy person) {
        return new PersonDummyDetailsDTO(person.getId(), person.getName(), person.getAddress(), person.getAge());
    }

    public static PersonDummy toEntity(PersonDummyDetailsDTO personDummyDetailsDTO) {
        return new PersonDummy(personDummyDetailsDTO.getName(),
                personDummyDetailsDTO.getAddress(),
                personDummyDetailsDTO.getAge());
    }
}
