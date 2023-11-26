package com.example.sp1.RestController;

import com.example.sp1.Services.IChambreService;
import com.example.sp1.Services.IEtudiantService;
import com.example.sp1.entities.Bloc;
import com.example.sp1.entities.Chambre;
import com.example.sp1.entities.Etudiant;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/etudiant")
public class EtudiantRestController {


        IEtudiantService iEtudiantService;
        @GetMapping("/affichertout")
        public List<Etudiant> retrieveAllEtudiants(){
            return iEtudiantService.retrieveAllEtudiants();
        }
        @PostMapping("/ajouteretudiant")
        public List<Etudiant> addEtudiants(@RequestBody List<Etudiant> etudiants) {
        return iEtudiantService.addEtudiants(etudiants);
        }
        @PutMapping("/modifieretudiant")
        public Etudiant updateEtudiant(@RequestBody Etudiant e){

            return iEtudiantService.updateEtudiant(e);
        }
    @GetMapping("/afficheretudiant/{idEtudiant}")
    public Etudiant retrieveEtudiant(@PathVariable("idEtudiant") long idEtudiant) {
        return iEtudiantService.retrieveEtudiant(idEtudiant);
    }



    @GetMapping("/supprimeretudiant/{idetudiant}")
         public void removeEtudiant(@PathVariable("idetudiant") long idEtudiant) {
        iEtudiantService.removeEtudiant(idEtudiant);
         }

}
