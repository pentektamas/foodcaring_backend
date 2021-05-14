package ro.disi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Donor extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private boolean hasDonated;

    public Donor(UUID id, String firstName, String lastName, String location, String phoneNumber, Account account, boolean hasDonated) {
        super(id, firstName, lastName, location, phoneNumber, account);
        this.hasDonated = hasDonated;
    }

    public Donor(String firstName, String lastName, String location, String phoneNumber, Account account, Boolean hasDonated) {
        super(firstName, lastName, location, phoneNumber, account);
        this.hasDonated = hasDonated;
    }

    public Donor() {
    }

    public boolean getHasDonated() {
        return hasDonated;
    }

    public void setHasDonated(boolean hasDonated) {
        this.hasDonated = hasDonated;
    }
}
