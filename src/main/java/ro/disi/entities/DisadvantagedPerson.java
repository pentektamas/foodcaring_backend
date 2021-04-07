package ro.disi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class DisadvantagedPerson extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private boolean helped;

    public DisadvantagedPerson(String firstName, String lastName, String location, String phoneNumber, Account account, boolean helped) {
        super(firstName, lastName, location, phoneNumber, account);
        this.helped = helped;
    }

    public DisadvantagedPerson() {
    }

    public boolean isHelped() {
        return helped;
    }

    public void setHelped(boolean helped) {
        this.helped = helped;
    }
}
