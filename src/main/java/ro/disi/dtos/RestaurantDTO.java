package ro.disi.dtos;

import io.micrometer.core.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

public class RestaurantDTO {

    private UUID id;

    @NotNull
    private String name;

    @NonNull
    private String location;

    @NonNull
    private Set<MenuDTO> menus;

    public RestaurantDTO() {

    }

    public RestaurantDTO(UUID id, @NotNull String name, @NonNull String location, @NonNull Set<MenuDTO> menus) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.menus = menus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getLocation() {
        return location;
    }

    public void setLocation(@NonNull String location) {
        this.location = location;
    }

    @NonNull
    public Set<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(@NonNull Set<MenuDTO> menus) {
        this.menus = menus;
    }
}
