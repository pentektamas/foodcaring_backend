package ro.disi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class DisadvantagedPerson extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private boolean helped;
    @Column(nullable = false)
    private int priority;

    public DisadvantagedPerson(String firstName, String lastName, String location, String phoneNumber, Account account, boolean helped, int priority) {
        super(firstName, lastName, location, phoneNumber, account);
        this.helped = helped;
        this.priority = priority;
    }

    public DisadvantagedPerson(String firstName, String lastName, String location, String phoneNumber, Account account, boolean helped) {
        super(firstName, lastName, location, phoneNumber, account);
        this.helped = helped;
    }

    public DisadvantagedPerson(String firstName, String lastName, String location, String phoneNumber, Account account, int priority) {
        super(firstName, lastName, location, phoneNumber, account);
        this.priority = priority;
    }

    public DisadvantagedPerson(String firstName, String lastName, String location, String phoneNumber, Account account) {
        super(firstName, lastName, location, phoneNumber, account);
    }


    public DisadvantagedPerson(UUID id, String firstName, String lastName, String location, String phoneNumber, Account account) {
        super(id, firstName, lastName, location, phoneNumber, account);
    }

    public DisadvantagedPerson(UUID id, String firstName, String lastName, String location, String phoneNumber, Account account, int priority) {
        super(id, firstName, lastName, location, phoneNumber, account);
        this.priority = priority;
    }

    public DisadvantagedPerson() {
    }


    public boolean isHelped() {
        return helped;
    }

    public void setHelped(boolean helped) {
        this.helped = helped;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
