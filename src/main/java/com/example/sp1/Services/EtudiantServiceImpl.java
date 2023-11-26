package com.example.sp1.Services;

import com.example.sp1.Repositories.EtudiantRepo;
import com.example.sp1.entities.Etudiant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {
    EtudiantRepo etudiantRepo;
    @Override
    public List<Etudiant> retrieveAllEtudiants(){return etudiantRepo.findAll();}
    @Override
    public List<Etudiant> addEtudiants(List<Etudiant> etudiants) {return etudiantRepo.saveAll(etudiants);}

    @Override
    public Etudiant updateEtudiant(Etudiant e) {return etudiantRepo.save(e);}

    @Override
    public Etudiant retrieveEtudiant(long idEtudiant) {return etudiantRepo.findById(idEtudiant).orElse(null);}
    @Override
    public void removeEtudiant(long idEtudiant) {etudiantRepo.deleteById(idEtudiant);}
}
