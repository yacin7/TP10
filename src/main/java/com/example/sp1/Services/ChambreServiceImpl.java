package com.example.sp1.Services;

import com.example.sp1.Repositories.BlocRepo;
import com.example.sp1.Repositories.ChambreRepo;
import com.example.sp1.entities.Chambre;
import com.example.sp1.entities.TypeChambre;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ChambreServiceImpl implements IChambreService {
    //instance
    ChambreRepo chambreRepo;
    BlocRepo blocRepo;
    @Override
    public List<Chambre> retrieveAllChambres(){
        return chambreRepo.findAll();
    }
    @Override
    public Chambre addChambre(Chambre c){
        //ajouter une description sur l'etat de la fonction
        System.out.println("ajouter"+c.toString());
        log.info("on a ajouté une chambre"+c.toString());
        return chambreRepo.save(c);
    }
    @Override
    public Chambre updateChambre(Chambre c){
        return chambreRepo.save(c);
    }
    @Override
    public Chambre retrieveChambre(long idChambre){
        return chambreRepo.findById(idChambre).orElse(null);
    }

    @Override
    public List<Chambre>  getChambresParNomUniversite(String nomUniversite) {
        return chambreRepo.findChambresByBloc_Foyer_Universite_NomUniversite(nomUniversite);
    }
    @Override
    public List<Chambre> getChambresParBlocEtType (long idBloc, TypeChambre typeC) {
        return chambreRepo.findChambresByBloc_IdBlocAndTypeC(idBloc,typeC);
    }

   @Override
    public List<Chambre> getChambresNonReserveParNomFoyerEtTypeChambre(String nomUniversite, TypeChambre typeC) {
        return chambreRepo.findAllByReservationsIsEmptyAndBloc_Foyer_Universite_NomUniversiteAndTypeC(nomUniversite,typeC);
    }

    /*List<Chambre> getChambreByReservationAnneeUniversitaire(Date debut,Date fin){
        List<Chambre> chambres=null;
        for (Chambre c: chambreRepo.findAll())
            for (Reservation r:c.getReservations())
                if (r.getAnneeUniversitaire().after(debut)&& r.getAnneeUniversitaire().before(fin))
                    chambres.add(c);
        return chambres;
    }*/
}
