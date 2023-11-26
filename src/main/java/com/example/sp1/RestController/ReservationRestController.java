package com.example.sp1.RestController;

import com.example.sp1.Services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.sp1.entities.Reservation;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationRestController {
    private IReservationService iReservationService;

    @GetMapping("/anneeUnNombloc")
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(@RequestParam("anneeUniversite") LocalDate anneeUniversitaire, @RequestParam("nomUniversite") String nomUniversite) {
        return iReservationService.getReservationParAnneeUniversitaireEtNomUniversite(anneeUniversitaire, nomUniversite);
    }

    @PostMapping("/ajouterReservation/{idChambre}/{cin}")
    public Reservation ajouterReservation(@RequestBody Reservation reservation, @PathVariable long idChambre, @PathVariable long cin) {
        return iReservationService.ajouterReservation(reservation, idChambre, cin);
    }

    @DeleteMapping("/annulerReservation/{cin}")
    public Reservation annulerReservation(@PathVariable long cin) {
        return iReservationService.annulerReservation(cin);
    }
}






