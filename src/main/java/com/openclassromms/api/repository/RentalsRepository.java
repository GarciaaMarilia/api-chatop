package com.openclassromms.api.repository;

import com.openclassromms.api.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalsRepository extends JpaRepository<Rental, Long> {

}
