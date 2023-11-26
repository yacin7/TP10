package com.example.sp1.Repositories;

import com.example.sp1.entities.Chambre;
import com.example.sp1.entities.Reservation;
import com.example.sp1.entities.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.sp1.entities.Bloc;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface ChambreRepo extends JpaRepository<Chambre,Long> {
    //recuperer les chambres reservées durant une année universitaire donnée
    //
    //List<Chambre> findChambreByReservations
    // recuperer les chambres definis par leur nom
    // List<Chambre> findChambreByBloc_NomBloc(String nom);

    Set<Chambre> findByNumeroChambreIn(List<Long> numeroChambre);

    List<Chambre> findByBloc(Bloc nomBloc);

    Chambre findChambreByNumeroChambre(long num);

    List<Chambre> findChambreByBloc_NomBloc(String nomBloc);

    int countChambreByTypeCAndBloc_IdBloc(TypeChambre typeChambre, long id);

    List<Chambre> findChambresByBloc_Foyer_Universite_NomUniversite(String nomUniversite);

    List<Chambre> findChambresByBloc_IdBlocAndTypeC(Long idBloc, TypeChambre typeC);

    @Query("select r FROM Chambre c " +
            "JOIN c.reservations r " +
            "JOIN c.bloc b " +
            "JOIN b.foyer f " +
            "WHERE f.nomFoyer = :nomFoyer " +
            "AND c.typeC = :typeC " +
            "AND r.anneeUniversitaire = :anneeUniversitaire AND r.estvalide = true")
    List<Chambre> getChambresNonReserveParNomFoyerEtTypeChambre(@Param("nomFoyer") String nomFoyer, @Param("typeC") TypeChambre typeC, @Param("anneeUniversitaire") LocalDate anneeUniversitaire);

    List<Chambre> findAllByReservationsIsEmptyAndBloc_Foyer_Universite_NomUniversiteAndTypeC(String nomUniversite, TypeChambre typeC);

    Chambre findByReservations(Reservation reservation);

    Chambre findByReservationsIdReservation(String idReservation);

}
