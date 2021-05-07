package ro.disi.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<DisadvantagedPerson> disadvantagedPersonList;

    public Donation(UUID id, Menu menu, Restaurant restaurant, List<DisadvantagedPerson> disadvantagedPersonList) {
        this.id = id;
        this.menu = menu;
        this.restaurant = restaurant;
        this.disadvantagedPersonList = disadvantagedPersonList;
    }

    public Donation(Menu menu, Restaurant restaurant, List<DisadvantagedPerson> disadvantagedPersonList) {
        this.menu = menu;
        this.restaurant = restaurant;
        this.disadvantagedPersonList = disadvantagedPersonList;
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

    public List<DisadvantagedPerson> getDisadvantagedPersonList() {
        return disadvantagedPersonList;
    }

    public void setDisadvantagedPersonList(List<DisadvantagedPerson> disadvantagedPersonList) {
        this.disadvantagedPersonList = disadvantagedPersonList;
    }
}
