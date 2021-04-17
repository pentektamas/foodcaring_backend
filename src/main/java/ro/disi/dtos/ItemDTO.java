package ro.disi.dtos;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ItemDTO {

    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String image;

    @NotNull
    private Double price;

    public ItemDTO(@NotNull String name, @NotNull String description, String image, @NotNull Double price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public ItemDTO(UUID id, @NotNull String name, @NotNull String description, String image, @NotNull Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public ItemDTO() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
