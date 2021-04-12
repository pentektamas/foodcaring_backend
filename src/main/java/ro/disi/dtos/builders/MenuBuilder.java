package ro.disi.dtos.builders;

import ro.disi.dtos.ItemDTO;
import ro.disi.dtos.MenuDTO;
import ro.disi.entities.Item;
import ro.disi.entities.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuBuilder {

    public static MenuDTO toMenuDTO(Menu menu) {
        List<Item> items = menu.getItemList();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            itemDTOS.add(ItemBuilder.toItemDTO(item));
        }
        return new MenuDTO(menu.getId(), menu.getName(), itemDTOS);
    }

    public static Menu toEntity(MenuDTO menuDTO) {
        List<ItemDTO> itemDTOS = menuDTO.getItemList();
        List<Item> itemList = new ArrayList<>();
        for (ItemDTO itemDTO : itemDTOS) {
            itemList.add(ItemBuilder.toEntity(itemDTO));
        }
        return new Menu(menuDTO.getId(), menuDTO.getName(), itemList);

    }
}