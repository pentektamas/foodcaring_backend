package ro.disi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.disi.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.disi.dtos.MenuDTO;
import ro.disi.dtos.builders.MenuBuilder;
import ro.disi.entities.Menu;
import ro.disi.repositories.MenuRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuService.class);

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<MenuDTO> findMenus() {
        List<Menu> menus = menuRepository.findAll();
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

    public UUID insertMenu(MenuDTO menuDTO) {
        Menu menu = MenuBuilder.toEntity(menuDTO);
        menu = menuRepository.save(menu);
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
        Optional<Menu> caregiverOptional = menuRepository.findById(menu.getId());
        if (!caregiverOptional.isPresent()) {
            LOGGER.error("Menu with id {} was not found in db", menu.getId());
            throw new ResourceNotFoundException(Menu.class.getSimpleName() + " with id: " + menu.getId());
        }
        Menu updatedMenu = menuRepository.save(menu);
        LOGGER.debug("Menu with id {} was updated in db", menu.getId());
        return MenuBuilder.toMenuDTO(menu);
    }
}
