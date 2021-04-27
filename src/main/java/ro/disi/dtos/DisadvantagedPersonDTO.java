package ro.disi.dtos;

import ro.disi.utils.Role;

import java.util.Set;
import java.util.UUID;

public class DisadvantagedPersonDTO extends PersonDTO {

    private boolean helped;

    private int priority;

    private String allergies;

    private Set<MenuDTO> wishList;

    public DisadvantagedPersonDTO() {
    }

    public DisadvantagedPersonDTO(String firstName, String lastName, String location, String phoneNumber, String username, String password, Role role, String allergies, Set<MenuDTO> wishList) {
        super(firstName, lastName, location, phoneNumber, username, password, role);
        this.allergies = allergies;
        this.wishList = wishList;
    }

    public DisadvantagedPersonDTO(UUID id, String firstName, String lastName, String location, String phoneNumber, String username, String password, Role role, String allergies, Set<MenuDTO> wishList) {
        super(id, firstName, lastName, location, phoneNumber, username, password, role);
        this.allergies = allergies;
        this.wishList = wishList;
    }

    public DisadvantagedPersonDTO(UUID id, String firstName, String lastName, String location, String phoneNumber, String username, String password, Role role, int priority, String allergies, Set<MenuDTO> wishList) {
        super(id, firstName, lastName, location, phoneNumber, username, password, role);
        this.priority = priority;
        this.allergies = allergies;
        this.wishList = wishList;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Set<MenuDTO> getWishList() {
        return wishList;
    }

    public void setWishList(Set<MenuDTO> wishList) {
        this.wishList = wishList;
    }
}
