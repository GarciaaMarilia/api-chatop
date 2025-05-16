package com.openclassromms.api.service;

import com.openclassromms.api.model.Rental;
import com.openclassromms.api.model.RentalsRequest;
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
        return rentalsRepository.findById(id)
                .orElse(null);
    }

    public String createRental(RentalsRequest request) {
        try {

            Rental rental = new Rental();
            rental.setSurface(request.getSurface());
            rental.setPrice(request.getPrice());
            rental.setPicture(request.getPicture());
            rental.setDescription(request.getDescription());

            rentalsRepository.save(rental);

            return "Rental created with success";
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a rental", e);
        }
    }

    public String updateRental(Long id, RentalsRequest request) {
        try {
            Rental existingRental = rentalsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Rental not found"));

            existingRental.setSurface(request.getSurface());
            existingRental.setPrice(request.getPrice());
            existingRental.setPicture(request.getPicture());
            existingRental.setDescription(request.getDescription());
            existingRental.setUpdatedAt();

            rentalsRepository.save(existingRental);

            return "Rental updated with success";
        } catch (Exception e) {
            throw new RuntimeException("Failed to update rental", e);
        }
    }

}
