package com.example.sp1.Services;

import com.example.sp1.Repositories.BlocRepo;
import com.example.sp1.Repositories.ChambreRepo;
import com.example.sp1.Repositories.FoyerRepo;
import com.example.sp1.entities.Bloc;
import com.example.sp1.entities.Chambre;
import com.example.sp1.entities.Foyer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@AllArgsConstructor

public class BlocServiceImpl implements IBlocService {
    BlocRepo blocRepo;
    FoyerRepo foyerRepo;
    ChambreRepo chambreRepo;

    @Override
    public List<Bloc> retrieveBlocs() {
        return blocRepo.findAll();
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        return blocRepo.save(bloc);
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepo.save(bloc);
    }

    @Override
    public Bloc retrieveBloc(long idBloc) {
        return blocRepo.findById(idBloc).orElse(null);

    }

    @Override
    public void removeBloc(long idBloc) {
        blocRepo.deleteById(idBloc);
    }
    @Override
    public Bloc affecterChambresABloc(List<Long> numeroChambre, String nomBloc) {
        Bloc bloc = blocRepo.findByNomBloc(nomBloc);
        Chambre chambre;
        for (long num : numeroChambre) {
            chambre = chambreRepo.findChambreByNumeroChambre(num);
            chambre.setBloc(bloc);
            chambreRepo.save(chambre);
        }
        return blocRepo.findByNomBloc(nomBloc);
}
    @Override
    public Bloc affecterBlocAFoyer(String nomBloc, String nomFoyer) {
        Bloc bloc = blocRepo.findByNomBloc(nomBloc);
        Foyer foyer = foyerRepo.findByNomFoyer(nomFoyer);

        if (bloc != null && foyer != null) {

            bloc.setFoyer(foyer);
            blocRepo.save(bloc);

            return bloc;
        } else {
            return null;
        }
    }

}