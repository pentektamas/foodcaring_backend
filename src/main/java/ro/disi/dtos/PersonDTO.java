package ro.disi.dtos;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import ro.disi.utils.Role;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {

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
    private Role role;


    public PersonDTO() {
    }

    public PersonDTO(@NotNull String firstName, @NotNull String lastName, @NotNull String location, @NotNull String phoneNumber, @NotNull String username, @NotNull String password, @NotNull Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public PersonDTO(UUID id,@NotNull String firstName, @NotNull String lastName, @NotNull String location, @NotNull String phoneNumber, @NotNull String username, @NotNull String password, @NotNull Role role) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UUID getId() {
        return this.id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
