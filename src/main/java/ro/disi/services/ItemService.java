package ro.disi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.ItemDTO;
import ro.disi.dtos.builders.ItemBuilder;
import ro.disi.entities.Item;
import ro.disi.repositories.ItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDTO> findItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(ItemBuilder::toItemDTO)
                .collect(Collectors.toList());
    }

    public ItemDTO findItemById(UUID uuid) {
        Optional<Item> prosumerOptional = itemRepository.findById(uuid);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Item with id {} was not found in db", uuid);
            throw new ResourceNotFoundException(Item.class.getSimpleName() + "with id" + uuid);
        }
        return ItemBuilder.toItemDTO(prosumerOptional.get());
    }

    public UUID insertItem(ItemDTO itemDTO) {
        Item item = ItemBuilder.toEntity(itemDTO);
        item = itemRepository.save(item);
        return item.getId();
    }

    public UUID deleteItem(UUID id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (!itemOptional.isPresent()) {
            LOGGER.error("Item with id {} was not found in db", id);
            throw new ResourceNotFoundException(Item.class.getSimpleName() + " with id: " + id);
        }
        itemRepository.deleteById(id);
        return id;
    }

    public ItemDTO updateItem(ItemDTO itemDTO) {
        Item item = ItemBuilder.toEntityWithId(itemDTO);
        Optional<Item> itemOptional = itemRepository.findById(item.getId());
        if (!itemOptional.isPresent()) {
            LOGGER.error("Item with id {} was not found in db", item.getId());
            throw new ResourceNotFoundException(Item.class.getSimpleName() + " with id: " + item.getId());
        }
        Item updatedItem = itemRepository.save(item);
        LOGGER.debug("Item with id {} was updated in db", item.getId());
        return ItemBuilder.toItemDTO(item);
    }
}
