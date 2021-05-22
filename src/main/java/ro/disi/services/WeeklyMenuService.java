package ro.disi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.RestaurantDTO;
import ro.disi.dtos.WeeklyMenuDTO;
import ro.disi.dtos.builders.RestaurantBuilder;
import ro.disi.dtos.builders.WeeklyMenuBuilder;
import ro.disi.entities.Restaurant;
import ro.disi.entities.WeeklyMenu;
import ro.disi.repositories.RestaurantRepository;
import ro.disi.repositories.WeeklyMenuRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WeeklyMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeeklyMenuService.class);

    private final WeeklyMenuRepository weeklyMenuRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public WeeklyMenuService(WeeklyMenuRepository weeklyMenuRepository, RestaurantRepository restaurantRepository) {
        this.weeklyMenuRepository = weeklyMenuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<WeeklyMenuDTO> findWeeklyMenus() {
        List<WeeklyMenu> weeklyMenus = weeklyMenuRepository.findAll();
        return weeklyMenus.stream()
                .map(WeeklyMenuBuilder::toWeeklyMenuDTO)
                .collect(Collectors.toList());
    }

    public List<WeeklyMenuDTO> findWeeklyMenusByRestaurant(UUID restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (!restaurantOptional.isPresent()) {
            LOGGER.error("Restaurant with id {} was not found in db", restaurantId);
            throw new ResourceNotFoundException(WeeklyMenu.class.getSimpleName() + "with id" + restaurantId);
        }
        List<WeeklyMenu> weeklyMenus = weeklyMenuRepository.findAllByRestaurant(restaurantOptional.get());
        return weeklyMenus.stream()
                .map(WeeklyMenuBuilder::toWeeklyMenuDTO)
                .collect(Collectors.toList());
    }


    public WeeklyMenuDTO findWeeklyMenuById(UUID uuid) {
        Optional<WeeklyMenu> prosumerOptional = weeklyMenuRepository.findById(uuid);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("WeeklyMenu with id {} was not found in db", uuid);
            throw new ResourceNotFoundException(WeeklyMenu.class.getSimpleName() + "with id" + uuid);
        }
        return WeeklyMenuBuilder.toWeeklyMenuDTO(prosumerOptional.get());
    }

    public UUID insertWeeklyMenu(UUID restaurantId, WeeklyMenuDTO weeklyMenuDTO) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (!restaurantOptional.isPresent()) {
            LOGGER.error("Restaurant with id {} was not found in db", restaurantId);
            throw new ResourceNotFoundException(Restaurant.class.getSimpleName() + " with id: " + restaurantId);
        }

        WeeklyMenu weeklyMenu;
        if(weeklyMenuDTO.getId()!=null){
            weeklyMenu = WeeklyMenuBuilder.toEntityWithId(weeklyMenuDTO);
        } else {
            weeklyMenu = WeeklyMenuBuilder.toEntity(weeklyMenuDTO);
        }
        Restaurant restaurant = restaurantOptional.get();
        weeklyMenu.setRestaurant(restaurant);

        weeklyMenu = weeklyMenuRepository.save(weeklyMenu);
        restaurant.getMenus().add(weeklyMenu);
        restaurantRepository.save(restaurant);
        LOGGER.debug("WeeklyMenu with id {} was inserted in db", weeklyMenu.getId());
        return weeklyMenu.getId();
    }

    public UUID deleteWeeklyMenu(UUID id) {
        Optional<WeeklyMenu> weeklyMenuOptional = weeklyMenuRepository.findById(id);
        if (!weeklyMenuOptional.isPresent()) {
            LOGGER.error("Weekly with id {} was not found in db", id);
            throw new ResourceNotFoundException(WeeklyMenu.class.getSimpleName() + " with id: " + id);
        }
        weeklyMenuRepository.deleteById(id);
        return id;
    }

    public WeeklyMenuDTO updateMenu(WeeklyMenuDTO weeklyMenuDTO) {
        WeeklyMenu weeklyMenu = WeeklyMenuBuilder.toEntityWithId(weeklyMenuDTO);
        Optional<WeeklyMenu> itemOptional = weeklyMenuRepository.findById(weeklyMenu.getId());
        if (!itemOptional.isPresent()) {
            LOGGER.error("WeeklyMenu with id {} was not found in db", weeklyMenu.getId());
            throw new ResourceNotFoundException(WeeklyMenu.class.getSimpleName() + " with id: " + weeklyMenu.getId());
        }
        WeeklyMenu updatedWeeklyMenu = weeklyMenuRepository.save(weeklyMenu);
        LOGGER.debug("WeeklyMenu with id {} was updated in db", weeklyMenu.getId());
        return WeeklyMenuBuilder.toWeeklyMenuDTO(updatedWeeklyMenu);
    }
}
