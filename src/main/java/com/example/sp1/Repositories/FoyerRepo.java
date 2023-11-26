package com.example.sp1.Repositories;

import com.example.sp1.entities.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoyerRepo extends JpaRepository<Foyer,Long> {
    Foyer findByNomFoyer(String nomFoyer);
}
