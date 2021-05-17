package ro.disi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.MenuDTO;
import ro.disi.dtos.RestaurantDTO;
import ro.disi.dtos.builders.MenuBuilder;
import ro.disi.dtos.builders.RestaurantBuilder;
import ro.disi.entities.Menu;
import ro.disi.entities.Restaurant;
import ro.disi.repositories.MenuRepository;
import ro.disi.repositories.RestaurantRepository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuService.class);

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<MenuDTO> findMenus() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .map(MenuBuilder::toMenuDTO)
                .collect(Collectors.toList());
    }

    public List<MenuDTO> findMenusByRestaurant(UUID restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (!restaurantOptional.isPresent()) {
            LOGGER.error("Restaurant with id {} was not found in db", restaurantId);
            throw new ResourceNotFoundException(Restaurant.class.getSimpleName() + "with id" + restaurantId);
        }
        List<Menu> menus = menuRepository.findAllByRestaurant(restaurantOptional.get());
        return menus.stream()
                .map(MenuBuilder::toMenuDTO)
                .collect(Collectors.toList());
    }

    public MenuDTO findMenuById(UUID uuid) {
        Optional<Menu> prosumerOptional = menuRepository.findById(uuid);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Menu with id {} was not found in db", uuid);
            throw new ResourceNotFoundException(Menu.class.getSimpleName() + "with id" + uuid);
        }
        return MenuBuilder.toMenuDTO(prosumerOptional.get());
    }

    public UUID insertMenu(UUID restaurantId, MenuDTO menuDTO) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (!restaurantOptional.isPresent()) {
            LOGGER.error("Restaurant with id {} was not found in db", restaurantId);
            throw new ResourceNotFoundException(Menu.class.getSimpleName() + " with id: " + restaurantId);
        }
        Menu menu = MenuBuilder.toEntity(menuDTO);
        Restaurant restaurant = restaurantOptional.get();
        menu.setRestaurant(restaurant);
        menu = menuRepository.save(menu);
        restaurant.getMenus().add(menu);
        restaurantRepository.save(restaurant);
        LOGGER.debug("Menu with id {} was inserted in db", menu.getId());
        return menu.getId();
    }

    public UUID deleteMenu(UUID id) {
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if (!menuOptional.isPresent()) {
            LOGGER.error("Menu with id {} was not found in db", id);
            throw new ResourceNotFoundException(Menu.class.getSimpleName() + " with id: " + id);
        }
        menuRepository.deleteById(id);
        return id;
    }

    public MenuDTO updateMenu(MenuDTO menuDTO) {
        Menu menu = MenuBuilder.toEntityWithId(menuDTO);
        Optional<Menu> itemOptional = menuRepository.findById(menu.getId());
        if (!itemOptional.isPresent()) {
            LOGGER.error("Menu with id {} was not found in db", menu.getId());
            throw new ResourceNotFoundException(Menu.class.getSimpleName() + " with id: " + menu.getId());
        }
        Menu updatedMenu = menuRepository.save(menu);
        LOGGER.debug("Menu with id {} was updated in db", menu.getId());
        return MenuBuilder.toMenuDTO(menu);
    }
}
