package ro.disi.dtos;

import ro.disi.utils.Role;

import java.util.UUID;

public class DisadvantagedPersonDTO extends PersonDTO {

    private boolean helped;

    public DisadvantagedPersonDTO() {
    }

    public DisadvantagedPersonDTO(String firstName, String lastName, String location, String phoneNumber, String username, String password, Role role) {
        super(firstName, lastName, location, phoneNumber, username, password, role);
    }

    public DisadvantagedPersonDTO(UUID id, String firstName, String lastName, String location, String phoneNumber, String username, String password, Role role) {
        super(id, firstName, lastName, location, phoneNumber, username, password, role);
    }

    public DisadvantagedPersonDTO(UUID id, String firstName, String lastName, String location, String phoneNumber, String username, String password, Role role, boolean helped) {
        super(id, firstName, lastName, location, phoneNumber, username, password, role);
        this.helped = helped;
    }

    public boolean isHelped() {
        return helped;
    }

    public void setHelped(boolean helped) {
        this.helped = helped;
    }
}
