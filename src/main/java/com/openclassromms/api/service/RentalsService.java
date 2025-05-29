package com.openclassromms.api.service;

import com.openclassromms.api.config.AppProperties;
import com.openclassromms.api.model.Rental;
import com.openclassromms.api.model.RentalsRequest;
import com.openclassromms.api.repository.RentalsRepository;
import exception.RentalNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class RentalsService {
    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private AppProperties appProperties;


    public List<Rental> getAllRentals() {
        return rentalsRepository.findAll();
    }

    public Rental getRentalById(Long id) {
        return rentalsRepository.findById(id)
                .orElse(null);
    }

    public void createRental(RentalsRequest request) {
        try {
            MultipartFile file = request.getPicture();
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path uploadPath = Paths.get("uploads");
            Files.createDirectories(uploadPath);
            String imageUrl = appProperties.getBaseUrl() + "/uploads/" + fileName;

            Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

            Rental rental = new Rental();
            rental.setName(request.getName());
            rental.setSurface(request.getSurface());
            rental.setPrice(request.getPrice());
            rental.setPicture(imageUrl);
            rental.setDescription(request.getDescription());

            rentalsRepository.save(rental);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a rental", e);
        }
    }

    public String updateRental(Long id, RentalsRequest request) {
        try {
            Rental existingRental = rentalsRepository.findById(id)
                    .orElseThrow(() -> new RentalNotFoundException("Rental not found"));

            MultipartFile file = request.getPicture();
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path uploadPath = Paths.get("uploads");
            Files.createDirectories(uploadPath);
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);


            String imageUrl = appProperties.getBaseUrl() + "/uploads/" + fileName;

            existingRental.setName(request.getName());
            existingRental.setSurface(request.getSurface());
            existingRental.setPrice(request.getPrice());
            existingRental.setPicture(imageUrl);
            existingRental.setDescription(request.getDescription());

            rentalsRepository.save(existingRental);

            return "Rental updated with success";
        } catch (Exception e) {
            throw new RuntimeException("Failed to update rental", e);
        }
    }


}
