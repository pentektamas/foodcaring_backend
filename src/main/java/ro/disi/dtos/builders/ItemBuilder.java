package ro.disi.dtos.builders;

import ro.disi.dtos.ItemDTO;
import ro.disi.entities.Item;

public class ItemBuilder {
    public ItemBuilder() {
    }

    public static ItemDTO toItemDTO(Item item) {
        return new ItemDTO(item.getId(), item.getName(), item.getDescription(), item.getImage(), item.getPrice());
    }

    public static Item toEntity(ItemDTO itemDTO) {
        return new Item(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getImage(), itemDTO.getPrice());
    }

    public static Item toEntityWithId(ItemDTO itemDTO) {
        return new Item(itemDTO.getId(), itemDTO.getName(), itemDTO.getDescription(), itemDTO.getImage(), itemDTO.getPrice());
    }

}
