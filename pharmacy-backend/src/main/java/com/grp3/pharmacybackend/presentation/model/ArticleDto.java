package com.grp3.pharmacybackend.presentation.model;

public class ArticleDto {
    private Long id;
    private Long barcode;
    private String name;
    private Integer quantity;
    private Boolean availability;
    
    public Long getId() {
        return this.id;
    }

    public Long getBarcode() {
        return this.barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean isAvailability() {
        if (this.getQuantity() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean getAvailability() {
        return this.availability;
    }

    public ArticleDto() {
    }

    public ArticleDto(Long id, Long barcode, String name, Integer quantity) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", barcode='" + getBarcode() + "'" +
            ", name='" + getName() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", availability='" + isAvailability() + "'" +
            "}";
    }

}
