package ro.disi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
public class DisadvantagedPerson extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private boolean helped;
    @Column(nullable = false)
    private int priority;
    @Column
    private String allergies;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "disadvantaged_person_wishlist",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private Set<Menu> wishList;

    public DisadvantagedPerson(String firstName, String lastName, String location, String phoneNumber, Account account, boolean helped, int priority, String allergies, Set<Menu> wishList) {
        super(firstName, lastName, location, phoneNumber, account);
        this.helped = helped;
        this.priority = priority;
        this.allergies = allergies;
        this.wishList = wishList;
    }

    public DisadvantagedPerson(String firstName, String lastName, String location, String phoneNumber, Account account, boolean helped, String allergies, Set<Menu> wishList) {
        super(firstName, lastName, location, phoneNumber, account);
        this.helped = helped;
        this.allergies = allergies;
        this.wishList = wishList;
    }

    public DisadvantagedPerson(String firstName, String lastName, String location, String phoneNumber, Account account, int priority, String allergies, Set<Menu> wishList) {
        super(firstName, lastName, location, phoneNumber, account);
        this.priority = priority;
        this.allergies = allergies;
        this.wishList = wishList;
    }

    public DisadvantagedPerson(String firstName, String lastName, String location, String phoneNumber, Account account) {
        super(firstName, lastName, location, phoneNumber, account);
    }


    public DisadvantagedPerson(UUID id, String firstName, String lastName, String location, String phoneNumber, Account account) {
        super(id, firstName, lastName, location, phoneNumber, account);
    }

    public DisadvantagedPerson(UUID id, String firstName, String lastName, String location, String phoneNumber, Account account, int priority, String allergies, Set<Menu> wishList) {
        super(id, firstName, lastName, location, phoneNumber, account);
        this.priority = priority;
        this.allergies = allergies;
        this.wishList = wishList;
    }

    public DisadvantagedPerson() {
    }


    public boolean isHelped() {
        return helped;
    }

    public void setHelped(boolean helped) {
        this.helped = helped;
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

    public Set<Menu> getWishList() {
        return wishList;
    }

    public void setWishList(Set<Menu> wishList) {
        this.wishList = wishList;
    }
}
