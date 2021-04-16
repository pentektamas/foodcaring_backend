package ro.disi.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class RestaurantResponsible extends Person {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Restaurant restaurant;

    public RestaurantResponsible(String firstName, String lastName, String location, String phoneNumber, Account account, Restaurant restaurant) {
        super(firstName, lastName, location, phoneNumber, account);
        this.restaurant = restaurant;
    }

    public RestaurantResponsible(String firstName, String lastName, String location, String phoneNumber, Account account) {
        super(firstName, lastName, location, phoneNumber, account);
    }

    public RestaurantResponsible() {

    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
