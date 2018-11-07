package com.codecool.mrguinepig.piggieservice.repository;

import com.codecool.mrguinepig.piggieservice.model.GuineaPig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuineaPigRepository extends JpaRepository<GuineaPig, Long> {

    Optional<GuineaPig> findTopByIdIsNotNullOrderByIdDesc();

}
