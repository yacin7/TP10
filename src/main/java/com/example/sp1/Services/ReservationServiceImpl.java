package com.example.sp1.Services;

import com.example.sp1.Repositories.ChambreRepo;
import com.example.sp1.Repositories.EtudiantRepo;
import com.example.sp1.Repositories.ReservationRepo;
import com.example.sp1.Repositories.UniversiteRepo;
import com.example.sp1.entities.Chambre;
import com.example.sp1.entities.Etudiant;
import com.example.sp1.entities.Reservation;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class ReservationServiceImpl implements IReservationService {
        ReservationRepo reservationRepo;
        UniversiteRepo universiteRepo;
        ChambreRepo chambreRepo;
        EtudiantRepo etudiantRepo;
        private EntityManager entityManager;

        @Override
        public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(LocalDate anneeUniversite, String nomUniversite) {
                return reservationRepo.getByNomUnAnneeUn(nomUniversite, anneeUniversite);
        }

        @Override

        public Reservation ajouterReservation(Reservation reservation, long idChambre, long cin) {
                Chambre chambre = chambreRepo.findById(idChambre)
                        .orElseThrow(() -> new NotFoundException("Chambre non trouvée avec l'ID : " + idChambre));
                Etudiant etudiant = etudiantRepo.findByCin(cin);

                // Générer l'identifiant de réservation en fonction des détails de la chambre et de l'étudiant
                String idReservation = chambre.getNumeroChambre() + chambre.getBloc().getNomBloc() + etudiant.getCin();

                // Vérifier si une réservation avec le même identifiant existe déjà
                Reservation existingReservation = reservationRepo.findByIdReservation(idReservation);
                if (existingReservation != null) {
                        throw new RuntimeException("Une réservation avec l'identifiant " + idReservation + " existe déjà.");
                }

                // Assigner l'identifiant à la réservation
                reservation.setIdReservation(idReservation);

                // Ajouter l'étudiant à la réservation
                reservation.getEtudiants().add(etudiant);

                // Associer la réservation avec la chambre (many-to-one)
                reservation.setChambre(chambre);

                // Associer la réservation avec l'étudiant (many-to-many)
                etudiant.getReservations().add(reservation);

                // Associer la chambre avec la réservation (one-to-many unidirectionnel)
                chambre.getReservations().add(reservation);

                // Définir la réservation comme non valide
                reservation.setEstValide(true);

                // Clear la session avant de sauvegarder
                try {
                        entityManager.clear();
                        reservationRepo.saveAndFlush(reservation);

                        // Sauvegarder l'entité Etudiant si nécessaire
                        etudiantRepo.save(etudiant);
                        chambreRepo.save(chambre);

                        return reservation;
                } catch (Exception e) {
                        // Gérer toute exception survenue pendant le processus de sauvegarde
                        throw new RuntimeException("Échec de la sauvegarde de la réservation.", e);
                }
        }

        @Override
        public Reservation annulerReservation(long cin) {
                try {
                        // Trouver la réservation associée à l'étudiant
                        Etudiant etudiant = etudiantRepo.findByCin(cin);
                        Reservation reservation = reservationRepo.findByEtudiantsContains(etudiant);

                        if (reservation == null) {
                                throw new NotFoundException("Aucune réservation trouvée pour l'étudiant avec le CIN : " + cin);
                        }

                        // Mettre à jour l'état de la réservation
                        reservation.setEstValide(false);

                        // Désaffecter l'étudiant de la réservation
                        reservation.getEtudiants().remove(etudiant);

                        // Trouver la chambre associée à la réservation
                        Chambre chambre = chambreRepo.findByReservations(reservation);

                        if (chambre == null) {
                                throw new NotFoundException("Aucune chambre trouvée pour la réservation avec l'ID : " + reservation.getIdReservation());
                        }

                        // Désaffecter la chambre de la réservation et mettre à jour sa capacité
                        chambre.getReservations().remove(reservation);
                        chambre.setCapacite(chambre.getCapacite() - 1);

                        // Sauvegarder les modifications
                        reservationRepo.saveAndFlush(reservation);
                        chambreRepo.save(chambre);


                        return reservation;
                } catch (Exception e) {
                        // Gérer toute exception survenue pendant le processus d'annulation
                        throw new RuntimeException("Échec de l'annulation de la réservation.", e);
                }
        }
}












