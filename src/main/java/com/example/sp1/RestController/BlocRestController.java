package com.example.sp1.RestController;

import com.example.sp1.Repositories.BlocRepo;
import com.example.sp1.Services.IBlocService;
import com.example.sp1.Services.IChambreService;
import com.example.sp1.Services.IFoyerService;
import com.example.sp1.entities.Bloc;
import com.example.sp1.entities.Chambre;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLEngineResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("bloc")
public class BlocRestController {
        IBlocService iBlocService;
        IFoyerService iFoyerService;;
        @GetMapping("/affichertout")
        public List<Bloc> retrieveBlocs(){
            return iBlocService.retrieveBlocs();
        }
        @PostMapping("/ajouterbloc")
        public Bloc addBloc(@RequestBody Bloc bloc){
            return iBlocService.addBloc(bloc);
        }
        @PutMapping("/modifierbloc")
        public Bloc updateBloc(@RequestBody Bloc bloc){

            return iBlocService.updateBloc(bloc);
        }
    @GetMapping("/afficherbloc/{idBloc}")
    public Bloc retrieveBloc(@PathVariable("idBloc") long idBloc) {
        return iBlocService.retrieveBloc(idBloc);
    }

    @DeleteMapping("/supprimerbloc/{idbloc}")
    public void removeBloc(@PathVariable("idbloc") long idBloc) {
        iBlocService.removeBloc(idBloc);
    }



    @PostMapping("/affecterBlocAFoyer")
    public ResponseEntity<Bloc> affecterBlocAFoyer(@RequestBody Map<String, Object> requete) {
        try {
            String nomBloc = (String) requete.get("nomBloc");
            String nomFoyer =(String) requete.get("nomFoyer") ;

            Bloc bloc = iBlocService.affecterBlocAFoyer(nomBloc, nomFoyer);

            if (bloc != null) {

                return ResponseEntity.status(HttpStatus.OK).body(bloc);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/affecterChambresABloc/{nomBloc}")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numeroChambre, @PathVariable String nomBloc) {
        return iBlocService.affecterChambresABloc(numeroChambre, nomBloc);
    }

}

