package com.example.sp1.Repositories;

import com.example.sp1.entities.Chambre;
import com.example.sp1.entities.Etudiant;
import com.example.sp1.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,String> {
    //chercher les reservations effectuées durant l'année universitaire 23-23
    List<Reservation> findReservationsByAnneeUniversitaireBetween(Date debut, Date fin);
    long countReservationsByAnneeUniversitaireBetween(Date debut, Date fin);

    List<Reservation> findReservationsByAnneeUniversitaire(LocalDate anneeUniversitaire );
//jpql
@Query("SELECT r FROM Chambre c " +
        "JOIN c.reservations r " +
        "JOIN c.bloc b " +
        "JOIN b.foyer f " +
        "JOIN f.universite u " +
        "WHERE r.anneeUniversitaire = :anneeUniversitaire " +
        "AND u.nomUniversite = :nomUniversite")
List<Reservation> getByNomUnAnneeUn(@Param("nomUniversite") String nomUniversite, @Param("anneeUniversitaire") LocalDate anneeUniversitaire);

    @Query("SELECT COUNT(r) FROM Chambre c JOIN c.reservations r WHERE c.idChambre = :chambreId")
    int countReservationsByChambreId(@Param("chambreId") Long chambreId);

    Reservation findByIdReservation(String idReservation);


    Reservation findByEtudiantsContains(Etudiant etudiant);
}

