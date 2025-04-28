package com.openclassromms.api.controller;

import com.openclassromms.api.model.Rental;
import com.openclassromms.api.service.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
@Autowired
    private RentalsService rentalService;

@GetMapping
    public List<Rental> getAllRentals(){
    return rentalService.getAllRentals();
}

public Rental getRentalById(Long id){
    return rentalService.getRentalById(id);
}
}
