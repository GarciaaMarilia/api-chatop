package com.openclassromms.api.controller;

import com.openclassromms.api.model.Rental;
import com.openclassromms.api.model.RentalsDTO;
import com.openclassromms.api.model.RentalsRequest;
import com.openclassromms.api.service.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
    @Autowired
    private RentalsService rentalService;

    @GetMapping
    public RentalsDTO getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return new RentalsDTO(rentals);
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

    @PostMapping()
    public ResponseEntity<Map<String, String>> createRental(
            @ModelAttribute RentalsRequest request
    )
    {
        rentalService.createRental(request);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Rental created !");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public String updateRental(@PathVariable Long id, @RequestBody RentalsRequest request) {
        return rentalService.updateRental(id, request);
    }
}
