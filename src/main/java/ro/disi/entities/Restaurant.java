package ro.disi.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Set<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(String name, String location, Set<Menu> menus) {
        this.name = name;
        this.location = location;
        this.menus = menus;
    }

    public Restaurant(UUID id, String name, String location, Set<Menu> menus) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.menus = menus;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
