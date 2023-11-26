package com.example.sp1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation implements Serializable {
    @Id
    private String idReservation;

    private LocalDate anneeUniversitaire;
    private boolean estvalide;

    @ManyToMany(mappedBy = "reservations")
    private Set<Etudiant> etudiants = new HashSet<>();



    public void setEstValide(boolean b) {
    }

    public void setChambre(Chambre chambre) {
    }

    public void setEtudiant(Etudiant etudiant) {
    }

    public Set<Etudiant> getEtudiants() {
        return etudiants;
    }

    public boolean isEstValide() {
        return this.estvalide;
    }
}
