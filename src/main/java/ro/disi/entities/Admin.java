package ro.disi.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Admin extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    public Admin(String firstName, String lastName, String location, String phoneNumber, Account account) {
        super(firstName, lastName, location, phoneNumber, account);
    }

    public Admin() {
    }


}
