package ro.disi.dtos;

import ro.disi.utils.Role;

import java.util.UUID;

public class DisadvantagedPersonDTO extends PersonDTO {

    private boolean helped;

    private int priority;

    public DisadvantagedPersonDTO() {
    }

    public DisadvantagedPersonDTO(String firstName, String lastName, String location, String phoneNumber, String username, String password, Role role) {
        super(firstName, lastName, location, phoneNumber, username, password, role);
    }

    public DisadvantagedPersonDTO(UUID id, String firstName, String lastName, String location, String phoneNumber, String username, String password, Role role) {
        super(id, firstName, lastName, location, phoneNumber, username, password, role);
    }

    public DisadvantagedPersonDTO(UUID id, String firstName, String lastName, String location, String phoneNumber, String username, String password, Role role, int priority) {
        super(id, firstName, lastName, location, phoneNumber, username, password, role);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
