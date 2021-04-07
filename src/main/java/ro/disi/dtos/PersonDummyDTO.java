package ro.disi.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class PersonDummyDTO extends RepresentationModel<PersonDummyDTO> {
    private UUID id;
    private String name;
    private int age;

    public PersonDummyDTO() {
    }

    public PersonDummyDTO(UUID id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDummyDTO personDummyDTO = (PersonDummyDTO) o;
        return age == personDummyDTO.age &&
                Objects.equals(name, personDummyDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
