package com.example.sp1.Services;

import com.example.sp1.entities.Bloc;
import com.example.sp1.entities.Chambre;

import java.util.List;

public interface IBlocService {
    List<Bloc> retrieveBlocs();

    Bloc updateBloc(Bloc bloc);

    Bloc addBloc(Bloc bloc);

    Bloc retrieveBloc(long idBloc);

    void removeBloc(long idBloc);


    Bloc affecterChambresABloc(List<Long> numeroChambre, String nomBloc);

    Bloc affecterBlocAFoyer(String nomBloc, String nomFoyer);


   
}
