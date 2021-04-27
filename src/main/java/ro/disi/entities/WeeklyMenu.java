package ro.disi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class WeeklyMenu extends Menu {
    @Column()
    private Date startDate;

    @Column()
    private Date endDate;

    @Column()
    private Double discountPercent=0.0;

    public WeeklyMenu() {
    }

    public WeeklyMenu(Date startDate, Date endDate, Double discountPercent) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
    }

    public WeeklyMenu(List<Item> itemList, Date startDate, Date endDate, Double discountPercent) {
        super(itemList);
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
    }

    public WeeklyMenu(String name, List<Item> itemList, Date startDate, Date endDate, Double discountPercent) {
        super(name, itemList);
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
    }

    public WeeklyMenu(UUID id, String name, List<Item> itemList, Date startDate, Date endDate, Double discountPercent) {
        super(id, name, itemList);
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }
}
