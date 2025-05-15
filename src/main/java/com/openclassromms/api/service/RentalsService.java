package com.openclassromms.api.service;

import com.openclassromms.api.model.Rental;
import com.openclassromms.api.repository.RentalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalsService {
    @Autowired
    private RentalsRepository rentalsRepository;

    public List<Rental> getAllRentals() {
        return rentalsRepository.findAll();
    }

    public Rental getRentalById(Long id) {
        return rentalsRepository.findById(id).orElse(null);
    }

    public Rental createRental(Rental rental) {
        return rentalsRepository.save(rental);
    }

    public Rental updateRental(Long id, Rental rental) {
        Rental existingRental = rentalsRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));

        existingRental.setSurface(rental.getSurface());
        existingRental.setPrice(rental.getPrice());
        existingRental.setPicture(rental.getPicture());
        existingRental.setDescription(rental.getDescription());

        return rentalsRepository.save(existingRental);

    }
}
