package ro.disi.dtos;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MenuDTO {

    private UUID id;

    @NotNull
    private String name;

    private List<ItemDTO> itemList;


    public MenuDTO() {
    }

    public MenuDTO(@NotNull String name, @NotNull List<ItemDTO> itemList) {
        this.name = name;
        this.itemList = itemList;
    }

    public MenuDTO(UUID id, @NotNull String name, List<ItemDTO> itemList) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        MenuDTO menuDTO = (MenuDTO) o;
        return Objects.equals(id, menuDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
