package ro.disi.dtos.builders;

import ro.disi.dtos.ItemDTO;
import ro.disi.dtos.WeeklyMenuDTO;
import ro.disi.entities.Item;
import ro.disi.entities.WeeklyMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WeeklyMenuBuilder {
    public static WeeklyMenuDTO toWeeklyMenuDTO(WeeklyMenu weeklyMenu) {
        List<Item> items = weeklyMenu.getItemList();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        if (items != null) {
            for (Item item : items) {
                itemDTOS.add(ItemBuilder.toItemDTO(item));
            }
        }
        return new WeeklyMenuDTO(
                weeklyMenu.getId(),
                weeklyMenu.getName(),
                itemDTOS,
                weeklyMenu.getStartDate(),
                weeklyMenu.getEndDate(),
                weeklyMenu.getDiscountPercent(),
                (100.0 - weeklyMenu.getDiscountPercent()) / 100.0 * getPriceForItemList(weeklyMenu.getItemList()) //e.g. 0.75*(priceItem1 + ... +priceItemN)
        );
    }

    public static WeeklyMenu toEntity(WeeklyMenuDTO weeklyMenuDTO) {
        List<ItemDTO> itemDTOS = weeklyMenuDTO.getItemList();
        List<Item> itemList = new ArrayList<>();
        if (itemDTOS != null) {
            for (ItemDTO itemDTO : itemDTOS) {
                itemList.add(ItemBuilder.toEntityWithId(itemDTO));
            }
        }
        return new WeeklyMenu(
                weeklyMenuDTO.getName(),
                itemList,
                weeklyMenuDTO.getStartDate(),
                weeklyMenuDTO.getEndDate(),
                weeklyMenuDTO.getDiscountPercent()
        );
    }

    public static WeeklyMenu toEntityWithId(WeeklyMenuDTO weeklyMenuDTO) {
        List<ItemDTO> itemDTOS = weeklyMenuDTO.getItemList();
        List<Item> itemList = new ArrayList<>();
        if (itemDTOS != null) {
            for (ItemDTO itemDTO : itemDTOS) {
                itemList.add(ItemBuilder.toEntityWithId(itemDTO));
            }
        }
        return new WeeklyMenu(
                weeklyMenuDTO.getId(),
                weeklyMenuDTO.getName(),
                itemList,
                weeklyMenuDTO.getStartDate(),
                weeklyMenuDTO.getEndDate(),
                weeklyMenuDTO.getDiscountPercent()
        );
    }

    private static Double getPriceForItemList(List<Item> items) {
        Double totalPrice = 0.0;
        for (Item item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    private static Double getPriceForItemDTOList(List<ItemDTO> itemDTOList) {
        Double totalPrice = 0.0;
        for (ItemDTO itemDTO : itemDTOList) {
            totalPrice += itemDTO.getPrice();
        }
        return totalPrice;
    }

}
