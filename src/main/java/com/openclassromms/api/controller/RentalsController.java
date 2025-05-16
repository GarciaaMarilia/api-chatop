package com.openclassromms.api.controller;

import com.openclassromms.api.model.Rental;
import com.openclassromms.api.model.RentalsRequest;
import com.openclassromms.api.service.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
    @Autowired
    private RentalsService rentalService;

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRentalById(@PathVariable Long id) {
        Rental rental = rentalService.getRentalById(id);
        if (rental == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rental not found with id: " + id);
        }
        return ResponseEntity.ok(rental);
    }

    @PostMapping("/create")
    public String createRental(@RequestBody RentalsRequest request) {
        return rentalService.createRental(request);
    }

    @PutMapping("/{id}")
    public String updateRental(@PathVariable Long id, @RequestBody RentalsRequest request) {
        return rentalService.updateRental(id, request);
    }
}
