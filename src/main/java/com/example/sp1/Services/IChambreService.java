package com.example.sp1.Services;

import com.example.sp1.entities.Bloc;
import com.example.sp1.entities.Chambre;
import com.example.sp1.entities.TypeChambre;

import java.util.List;

public interface IChambreService {

    List<Chambre> retrieveAllChambres();

    Chambre addChambre(Chambre c);

    Chambre updateChambre(Chambre c);

    Chambre retrieveChambre(long idChambre);


    List<Chambre>  getChambresParNomUniversite(String nomUniversite);

    List<Chambre> getChambresParBlocEtType (long idBloc, TypeChambre typeC);

    List<Chambre> getChambresNonReserveParNomFoyerEtTypeChambre(String nomUniversite, TypeChambre typeC);
}
