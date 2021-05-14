package ro.disi.dtos;


import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class DonationDTO {

    private UUID id;

    @NotNull
    private MenuDTO menu;

    @NotNull
    private RestaurantDTO restaurant;

    @NotNull
    private Set<DisadvantagedPersonDTO> disadvantagedPersons;

    @NotNull
    private DonorDTO donor;

    private Date date;

    public DonationDTO() {
    }

    public DonationDTO(MenuDTO menuDTO, RestaurantDTO restaurantDTO, Set<DisadvantagedPersonDTO> disadvantagedPersonDTOS, DonorDTO donorDTO, Date date) {
        this.menu = menuDTO;
        this.restaurant = restaurantDTO;
        this.disadvantagedPersons = disadvantagedPersonDTOS;
        this.donor = donorDTO;
        this.date = date;
    }

    public DonationDTO(UUID id, MenuDTO menuDTO, RestaurantDTO restaurantDTO, Set<DisadvantagedPersonDTO> disadvantagedPersonDTOS, DonorDTO donorDTO, Date date) {
        this.id = id;
        this.menu = menuDTO;
        this.restaurant = restaurantDTO;
        this.disadvantagedPersons = disadvantagedPersonDTOS;
        this.donor = donorDTO;
        this.date = date;
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

    public Set<DisadvantagedPersonDTO> getDisadvantagedPersons() {
        return disadvantagedPersons;
    }

    public void setDisadvantagedPersons(Set<DisadvantagedPersonDTO> disadvantagedPersons) {
        this.disadvantagedPersons = disadvantagedPersons;
    }

    public DonorDTO getDonor() {
        return donor;
    }

    public void setDonor(DonorDTO donor) {
        this.donor = donor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
