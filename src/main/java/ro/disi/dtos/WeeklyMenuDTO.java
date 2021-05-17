package ro.disi.dtos;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WeeklyMenuDTO extends MenuDTO {
    private Date startDate;
    private Date endDate;
    private Double discountPercent;
    private Double price;

    public WeeklyMenuDTO(Date startDate, Date endDate, Double discount, Double price) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discount;
        this.price = price;
    }

    public WeeklyMenuDTO(@NotNull String name, @NotNull List<ItemDTO> itemList, Date startDate, Date endDate, Double discount, Double price) {
        super(name, itemList);
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discount;
        this.price = price;
    }

    public WeeklyMenuDTO(UUID id, @NotNull String name, List<ItemDTO> itemList, Date startDate, Date endDate, Double discount, Double price) {
        super(id, name, itemList);
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discount;
        this.price = price;
    }

    public WeeklyMenuDTO(UUID id, @NotNull String name, List<ItemDTO> itemList, Date startDate, Date endDate, Double discount) {
        super(id, name, itemList);
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discount;
    }

    public WeeklyMenuDTO() {
    }

    public WeeklyMenuDTO(@NotNull String name, @NotNull List<ItemDTO> itemList) {
        super(name, itemList);
    }

    public WeeklyMenuDTO(UUID id, @NotNull String name, List<ItemDTO> itemList) {
        super(id, name, itemList);
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

    public void setDiscountPercent(Double discount) {
        this.discountPercent = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
