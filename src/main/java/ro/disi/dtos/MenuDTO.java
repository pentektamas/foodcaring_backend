package ro.disi.dtos;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ro.disi.entities.Item;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class MenuDTO {

    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private List<ItemDTO> itemList;

    public MenuDTO(UUID id, @NotNull String name, @NotNull List<ItemDTO> itemList) {
        this.id = id;
        this.name = name;
        this.itemList = itemList;
    }

    public MenuDTO() {
    }

    public MenuDTO(@NotNull String name, @NotNull List<ItemDTO> itemList) {
        this.name = name;
        this.itemList = itemList;
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

    public List<ItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemDTO> itemList) {
        this.itemList = itemList;
    }
}
