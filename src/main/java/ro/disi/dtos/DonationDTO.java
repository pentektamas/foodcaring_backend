package ro.disi.dtos;

import ro.disi.entities.Donor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class DonationDTO {

    private UUID id;

    @NotNull
    private MenuDTO menu;

    @NotNull
    private RestaurantDTO restaurant;

    @NotNull
    private List<DisadvantagedPersonDTO> disadvantagedPersons;

    @NotNull
    private DonorDTO donor;

    public DonationDTO() {
    }

    public DonationDTO(MenuDTO menuDTO, RestaurantDTO restaurantDTO, List<DisadvantagedPersonDTO> disadvantagedPersonDTOS, DonorDTO donorDTO) {
        this.menu = menuDTO;
        this.restaurant = restaurantDTO;
        this.disadvantagedPersons = disadvantagedPersonDTOS;
        this.donor = donorDTO;
    }

    public DonationDTO(UUID id, MenuDTO menuDTO, RestaurantDTO restaurantDTO, List<DisadvantagedPersonDTO> disadvantagedPersonDTOS, DonorDTO donorDTO) {
        this.id = id;
        this.menu = menuDTO;
        this.restaurant = restaurantDTO;
        this.disadvantagedPersons = disadvantagedPersonDTOS;
        this.donor = donorDTO;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MenuDTO getMenu() {
        return menu;
    }

    public void setMenu(MenuDTO menu) {
        this.menu = menu;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public List<DisadvantagedPersonDTO> getDisadvantagedPersons() {
        return disadvantagedPersons;
    }

    public void setDisadvantagedPersons(List<DisadvantagedPersonDTO> disadvantagedPersons) {
        this.disadvantagedPersons = disadvantagedPersons;
    }

    public DonorDTO getDonor() {
        return donor;
    }

    public void setDonor(DonorDTO donor) {
        this.donor = donor;
    }

    @Override
    public String toString() {
        return "DonationDTO{" +
                "id=" + id +
                ", menu=" + menu +
                ", donor=" + donor.getUsername() +
                ", disadvantagedPersons=" + disadvantagedPersons +
                '}';
    }
}
