package ro.disi.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    @Lob
    private String image;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "itemList", cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private List<Menu> menuList;

    public Item() {
    }


    public Item(String name, String description, String image, Double price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public Item(UUID id, String name, String description, String image, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
