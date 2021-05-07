package ro.disi.dtos;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class DonationDTO {

    private UUID id;

    @NotNull
    private MenuDTO meniu;

    private RestaurantDTO restaurant;

    private List<DisadvantagedPersonDTO> disadvantagedPersons;

    public DonationDTO(UUID id, MenuDTO menuDTO, RestaurantDTO restaurantDTO, List<DisadvantagedPersonDTO> disadvantagedPersonDTOS) {
        this.id = id;
        this.meniu = menuDTO;
        this.restaurant = restaurantDTO;
        this.disadvantagedPersons = disadvantagedPersonDTOS;
    }

    public DonationDTO(MenuDTO menuDTO, RestaurantDTO restaurantDTO, List<DisadvantagedPersonDTO> disadvantagedPersonDTOS) {
        this.meniu = menuDTO;
        this.restaurant = restaurantDTO;
        this.disadvantagedPersons = disadvantagedPersonDTOS;
    }

    public DonationDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MenuDTO getMenuDTO() {
        return meniu;
    }

    public void setMenuDTO(MenuDTO menuDTO) {
        this.meniu = menuDTO;
    }

    public RestaurantDTO getRestaurantDTO() {
        return restaurant;
    }

    public void setRestaurantDTO(RestaurantDTO restaurantDTO) {
        this.restaurant = restaurantDTO;
    }

    public List<DisadvantagedPersonDTO> getDisadvantagedPersonDTOS() {
        return disadvantagedPersons;
    }

    public void setDisadvantagedPersonDTOS(List<DisadvantagedPersonDTO> disadvantagedPersonDTOS) {
        this.disadvantagedPersons = disadvantagedPersonDTOS;
    }

    @Override
    public String toString() {
        return "DonationDTO{" +
                "id=" + id +
                ", menu=" + meniu +
                ", restaurant=" + restaurant +
                ", disadvantagedPersons=" + disadvantagedPersons +
                '}';
    }
}
