package ro.disi.dtos;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DonorDTO {

    private UUID id;
    
    @NotNull
    private String firstName;
    
    @NotNull
    private String lastName;
   
    @NotNull
    private String location;
    
    @NotNull
    private String phoneNumber;
    
    @NotNull
    private String username;
    
    @NotNull
    private String password;
    
    @NotNull
    private Boolean hasDonated;

    public DonorDTO() {

    }

    public DonorDTO(@NotNull String firstName, @NotNull String lastName, @NotNull String location, @NotNull String phoneNumber, @NotNull String username, @NotNull String password, @NotNull Boolean hasDonated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.hasDonated = hasDonated;
    }

    public DonorDTO(UUID id, @NotNull String firstName, @NotNull String lastName, @NotNull String location, @NotNull String phoneNumber, @NotNull String username, @NotNull String password, @NotNull Boolean hasDonated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.hasDonated = hasDonated;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getHasDonated() {
        return hasDonated;
    }

    public void setHasDonated(Boolean hasDonated) {
        this.hasDonated = hasDonated;
    }
}
