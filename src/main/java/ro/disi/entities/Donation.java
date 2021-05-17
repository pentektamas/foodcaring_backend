package ro.disi.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Donation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Menu menu;

    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    private Donor donor;

    private Date date;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "donation_disadvantaged_person_list",
            joinColumns = @JoinColumn(name = "donation_id"),
            inverseJoinColumns = @JoinColumn(name = "disadvantaged_person_list_id", referencedColumnName = "id", table = "disadvantaged_person"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Set<DisadvantagedPerson> disadvantagedPersonList = new HashSet<>();

    public Donation(UUID id, Menu menu, Restaurant restaurant, Set<DisadvantagedPerson> disadvantagedPersonList, Donor donor, Date date) {
        this.id = id;
        this.menu = menu;
        this.restaurant = restaurant;
        this.disadvantagedPersonList = disadvantagedPersonList;
        this.donor = donor;
        this.date = date;
    }

    public Donation(Menu menu, Restaurant restaurant, Set<DisadvantagedPerson> disadvantagedPersonList, Donor donor, Date date) {
        this.menu = menu;
        this.restaurant = restaurant;
        this.disadvantagedPersonList = disadvantagedPersonList;
        this.donor = donor;
        this.date = date;
    }

    public Donation() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<DisadvantagedPerson> getDisadvantagedPersonList() {
        return disadvantagedPersonList;
    }

    public void setDisadvantagedPersonList(Set<DisadvantagedPerson> disadvantagedPersonList) {
        this.disadvantagedPersonList = disadvantagedPersonList;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
