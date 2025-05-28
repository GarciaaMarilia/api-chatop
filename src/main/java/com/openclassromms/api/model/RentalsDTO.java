package com.openclassromms.api.model;

import java.util.List;

public class RentalsDTO {
    private List<Rental> rentals;

    public RentalsDTO(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
