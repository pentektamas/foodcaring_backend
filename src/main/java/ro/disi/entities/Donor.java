package ro.disi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Donor extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private boolean hasDonated;

    public Donor(String firstName, String lastName, String location, String phoneNumber, Account account, boolean hasDonated) {
        super(firstName, lastName, location, phoneNumber, account);
        this.hasDonated = hasDonated;
    }

    public Donor() {
    }

    public boolean isHasDonated() {
        return hasDonated;
    }

    public void setHasDonated(boolean hasDonated) {
        this.hasDonated = hasDonated;
    }
}
