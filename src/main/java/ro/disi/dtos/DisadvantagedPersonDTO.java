package ro.disi.dtos;

import ro.disi.utils.Role;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class DisadvantagedPersonDTO extends PersonDTO {

    private int nrOfHelps;

    private int priority;

    private String allergies;

    private Set<MenuDTO> wishList;

    public DisadvantagedPersonDTO() {
    }

    public DisadvantagedPersonDTO(String firstName, String lastName, String location, String phoneNumber, String username, String password, Role role, String allergies, Set<MenuDTO> wishList, int nrOfHelps) {
        super(firstName, lastName, location, phoneNumber, username, password, role);
        this.allergies = allergies;
        this.wishList = wishList;
        this.nrOfHelps = nrOfHelps;
    }

    public DisadvantagedPersonDTO(UUID id, String firstName, String lastName, String location, String phoneNumber, String username, String password, Role role, String allergies, Set<MenuDTO> wishList, int nrOfHelps) {
        super(id, firstName, lastName, location, phoneNumber, username, password, role);
        this.allergies = allergies;
        this.wishList = wishList;
        this.nrOfHelps = nrOfHelps;
    }

    public DisadvantagedPersonDTO(UUID id, String firstName, String lastName, String location, String phoneNumber, String username, String password, Role role, int priority, String allergies, Set<MenuDTO> wishList, int nrOfHelps) {
        super(id, firstName, lastName, location, phoneNumber, username, password, role);
        this.priority = priority;
        this.allergies = allergies;
        this.wishList = wishList;
        this.nrOfHelps = nrOfHelps;
    }

    public int getNrOfHelps() {
        return nrOfHelps;
    }

    public void setNrOfHelps(int nrOfHelps) {
        this.nrOfHelps = nrOfHelps;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DisadvantagedPersonDTO)) return false;
        if (!super.equals(o)) return false;
        DisadvantagedPersonDTO that = (DisadvantagedPersonDTO) o;
        return nrOfHelps == that.nrOfHelps &&
                getPriority() == that.getPriority() &&
                Objects.equals(getAllergies(), that.getAllergies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nrOfHelps, getPriority(), getAllergies());
    }
}
