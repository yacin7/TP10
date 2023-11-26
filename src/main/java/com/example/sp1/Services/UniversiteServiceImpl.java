package com.example.sp1.Services;

import com.example.sp1.Repositories.FoyerRepo;
import com.example.sp1.Repositories.UniversiteRepo;
import com.example.sp1.entities.Universite;
import com.example.sp1.entities.Foyer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService {
    UniversiteRepo universiteRepo;
    FoyerRepo foyerRepo;

    @Override
    public List<Universite> retrieveAllUniversities() {
        return universiteRepo.findAll();
    }

    @Override
    public Universite addUniversity(Universite u) {
        return universiteRepo.save(u);
    }

    @Override
    public Universite updateUniversity(Universite u) {
        return universiteRepo.save(u);
    }

    @Override
    public Universite retrieveUniversity(long idUniversite) {
        return universiteRepo.findById(idUniversite).orElse(null);
    }
    @Override
    public String affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Optional<Foyer> foyerOptional = foyerRepo.findById(idFoyer);
        Optional<Universite> universiteOptional = Optional.ofNullable(universiteRepo.findByNomUniversite(nomUniversite));

        if (foyerOptional.isPresent() && universiteOptional.isPresent()) {
            Foyer foyer = foyerOptional.get();
            Universite universite = universiteOptional.get();
            universite.setFoyer(foyer);
            universiteRepo.save(universite);

            return "Opération réussie : Foyer affecté à l'université.";
        } else {
            return "Erreur : Foyer ou université non trouvés.";
        }

    }
    @Override
    public Universite desaffecterFoyerAUniversite(long idFoyer, long idUniversite) {
        Universite universite = universiteRepo.findById(idUniversite).orElse(null);
        Foyer foyer = foyerRepo.findById(idFoyer).orElse(null);

        if (universite != null && foyer != null) {
            universite.setFoyer(null);
            universiteRepo.save(universite);
        }

        return universite;
    }
}
