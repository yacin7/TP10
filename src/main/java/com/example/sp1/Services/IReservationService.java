package com.example.sp1.Services;

import com.example.sp1.Repositories.ChambreRepo;
import com.example.sp1.Repositories.EtudiantRepo;
import com.example.sp1.Repositories.ReservationRepo;
import com.example.sp1.entities.Reservation;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface IReservationService {

    List<Reservation>  getReservationParAnneeUniversitaireEtNomUniversite(LocalDate anneeUniversite, String nomUniversite);

    Reservation ajouterReservation(Reservation reservation, long idChambre, long cin);


    Reservation annulerReservation(long cin);
}
