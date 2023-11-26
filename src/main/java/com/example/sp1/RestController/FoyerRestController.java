package com.example.sp1.RestController;

import com.example.sp1.Services.IBlocService;
import com.example.sp1.Services.IFoyerService;
import com.example.sp1.entities.Bloc;
import com.example.sp1.entities.Foyer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/foyer")
public class FoyerRestController {


        IFoyerService iFoyerService;
        @GetMapping("/affichertout")
        public List<Foyer> retrieveAllFoyers(){
            return iFoyerService.retrieveAllFoyers();
        }
        @PostMapping("/ajouterfoyer")
        public Foyer addFoyer(@RequestBody Foyer f){
            return iFoyerService.addFoyer(f);
        }
        @PutMapping("/modifierfoyer")
        public Foyer updateFoyer(@RequestBody Foyer f){

            return iFoyerService.updateFoyer(f);
        }
        @GetMapping("/afficherfoyer/{idFoyer}")
        public Foyer retrieveFoyer(@PathVariable("idFoyer") long idFoyer) {
            return iFoyerService.retrieveFoyer(idFoyer);
        }

        @DeleteMapping("/supprimerfoyer/{idFoyer}")
        public void removeFoyer(@PathVariable("idFoyer") long idFoyer) {
            iFoyerService.removeFoyer(idFoyer);
        }

    }


