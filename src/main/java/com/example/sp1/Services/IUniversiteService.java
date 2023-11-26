package com.example.sp1.Services;

import com.example.sp1.entities.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversities();

    Universite addUniversity(Universite u);

    Universite updateUniversity(Universite u);

    Universite retrieveUniversity(long idUniversite);

    String affecterFoyerAUniversite(long idFoyer, String nomUniversite);


    Universite desaffecterFoyerAUniversite(long idFoyer, long idUniversite);
}
