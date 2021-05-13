package ro.disi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.disi.entities.*;
import ro.disi.repositories.*;

import javax.transaction.Transactional;
import java.util.*;

import static ro.disi.utils.ItemImages.*;

@Service
public class DataPopulator implements InitializingBean {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RestaurantResponsibleRepository restaurantResponsibleRepository;

    @Autowired
    DonorRepository donorRepository;

    @Autowired
    DisadvantagedPersonRepository disadvantagedPersonRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    AllergenRepository allergenRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataPopulator.class);

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {

        insertAdmins();
        insertDonors();
        insertRestaurantResponsibles();
        insertDisadvantagedPersons();

        insertRestaurants();
        insertMenus();
        insertItems();
    }

    private void insertAdmins() {
        if (adminRepository.count() > 0) {
            return;
        }

        List<Admin> adminList = new ArrayList<>();
        adminList.add(new Admin("Ionel", "Popescu", "Str. Ciupercilor, Nr. 12, Cluj-Napoca", "0746240445",
                new Account("admin", getEncoder().encode("pass"), Role.ADMIN)));
        adminList.add(new Admin("Alexandra", "Stan", "Str. Liliacul, Nr. 17, Bucuresti", "0746244445",
                new Account("admin2", getEncoder().encode("pass"), Role.ADMIN)));

        for (Admin admin : adminList) {
            adminRepository.save(admin);
        }

        LOGGER.info("Admin accounts were added by the populator!");
    }

    private void insertDonors() {
        if (donorRepository.count() > 0) {
            return;
        }

        List<Donor> donorList = new ArrayList<>();
        donorList.add(new Donor("Lorena", "Iacobescu", "Str. Carnaval, Nr. 102, Cluj-Napoca", "0742240445",
                new Account("donor", getEncoder().encode("pass"), Role.DONOR)));
        donorList.add(new Donor("Mihai", "Ulici", "Str. Castanelor, Nr. 20, Cluj-Napoca", "0736244445",
                new Account("donor2", getEncoder().encode("pass"), Role.DONOR)));

        for (Donor donor : donorList) {
            donorRepository.save(donor);
        }

        LOGGER.info("Donor accounts were added by the populator!");
    }

    private void insertDisadvantagedPersons() {
        if (disadvantagedPersonRepository.count() > 0) {
            return;
        }

        List<Menu> menuList = menuRepository.findAll();

        Set<Menu> wishListMenus1 = new HashSet<>();
        wishListMenus1.add(menuList.get(2));

        Set<Menu> wishListMenus2 = new HashSet<>();
        wishListMenus2.add(menuList.get(0));
        wishListMenus2.add(menuList.get(1));

        List<DisadvantagedPerson> disadvantagedPersonList = new ArrayList<>();
        disadvantagedPersonList.add(new DisadvantagedPerson("Costel", "Ionescu", "Str. Munteanu, Nr. 102, Cluj-Napoca", "0752240445",
                new Account("disa", getEncoder().encode("pass"), Role.DISADVANTAGED_PERSON), 0, "peanuts; chocolate", wishListMenus1));
        disadvantagedPersonList.add(new DisadvantagedPerson("Stefan", "Marinescu", "Str. Florilor, Nr. 20, Cluj-Napoca", "0786244445",
                new Account("disa2", getEncoder().encode("pass"), Role.DISADVANTAGED_PERSON), 2, "peanuts; strawberries; eggs; milk", wishListMenus2));
        disadvantagedPersonList.add(new DisadvantagedPerson("Constanta", "Pop", "Str. Castanelor, Nr. 44, Cluj-Napoca", "0736144445",
                new Account("disa3", getEncoder().encode("pass"), Role.DISADVANTAGED_PERSON), 1, "soy; bananas; tuna; salami; blueberries", new HashSet<>()));

        for (DisadvantagedPerson disadvantagedPerson : disadvantagedPersonList) {
            disadvantagedPersonRepository.save(disadvantagedPerson);
        }

        LOGGER.info("Disadvantaged person accounts were added by the populator!");
    }

    private void insertRestaurantResponsibles() {
        if (restaurantResponsibleRepository.count() > 0) {
            return;
        }

        restaurantRepository.deleteAll();
        insertRestaurants();
        List<Restaurant> restaurantList = restaurantRepository.findAll();

        List<RestaurantResponsible> restaurantResponsibleList = new ArrayList<>();
        restaurantResponsibleList.add(new RestaurantResponsible("Lorena", "Iacobescu", "Str. Carnaval, Nr. 102, Cluj-Napoca", "0742240445",
                new Account("resp", getEncoder().encode("pass"), Role.RESTAURANT_RESPONSIBLE), restaurantList.get(0)));
        restaurantResponsibleList.add(new RestaurantResponsible("Mihai", "Ulici", "Str. Castanelor, Nr. 20, Cluj-Napoca", "0736244445",
                new Account("resp2", getEncoder().encode("pass"), Role.RESTAURANT_RESPONSIBLE), restaurantList.get(1)));
        restaurantResponsibleList.add(new RestaurantResponsible("Veronica", "Micle", "Str. Plopilor, Nr. 2, Cluj-Napoca", "0738244445",
                new Account("resp3", getEncoder().encode("pass"), Role.RESTAURANT_RESPONSIBLE)));

        for (RestaurantResponsible restaurantResponsible : restaurantResponsibleList) {
            restaurantResponsibleRepository.save(restaurantResponsible);
        }

        LOGGER.info("RestaurantResponsible accounts were added by the populator!");
    }

    private void insertRestaurants() {
        if (restaurantRepository.count() > 0) {
            return;
        }

        menuRepository.deleteAll();
        insertMenus();
        List<Menu> menuList = menuRepository.findAll();

        Set<Menu> menus1 = new HashSet<>();
        menus1.add(menuList.get(0));

        Set<Menu> menus2 = new HashSet<>();
        menus2.add(menuList.get(1));
        menus2.add(menuList.get(2));

        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant("Floarea Soarelui", "Str. Primaverii, Nr. 12, Cluj-Napoca", menus1));
        restaurantList.add(new Restaurant("Eating Sunshine", "Str. Verii, Nr. 4, Cluj-Napoca", menus2));
        restaurantList.add(new Restaurant("Flamingo Cuisine", "Str. Iernii, Nr. 123, Cluj-Napoca", new HashSet<>()));

        for (Restaurant restaurant : restaurantList) {
            restaurantRepository.save(restaurant);
        }

        LOGGER.info("Restaurants were added by the populator!");

    }

    private void insertMenus() {
        if (menuRepository.count() > 0) {
            return;
        }

        insertItems();
        List<Item> itemList = itemRepository.findAll();
        int length = itemList.size();

        List<Item> itemList1 = randomSubList(itemList, length - 1);
        List<Item> itemList2 = randomSubList(itemList, length - 2);

        List<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu("Meniul Zilei", itemList1));
        menuList.add(new Menu("Meniu de Primavara", itemList2));
        menuList.add(new Menu("Meniu", itemList));

        for (Menu menu : menuList) {
            menuRepository.save(menu);
        }

        LOGGER.info("Menus were added by the populator!");

    }

    private void insertItems() {
        if (itemRepository.count() > 0) {
            return;
        }

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("Pasta Carbonara", "The classical Italian taste", PASTA, 20.5));
        itemList.add(new Item("Tripe Soup", "The classical Romanian taste", CIORBA_BURTA, 14.5));
        itemList.add(new Item("Wiener Schnitzel", "The classical austrian taste", SCHNITZEL, 18.99));

        for (Item item : itemList) {
            itemRepository.save(item);
        }

        LOGGER.info("Items were added by the populator!");
    }


    private PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static <T> List<T> randomSubList(List<T> list, int newSize) {
        list = new ArrayList<>(list);
        Collections.shuffle(list);
        return list.subList(0, newSize);
    }
}
