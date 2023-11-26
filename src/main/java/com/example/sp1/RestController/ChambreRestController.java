package com.example.sp1.RestController;

import com.example.sp1.Services.IBlocService;
import com.example.sp1.entities.Bloc;
import com.example.sp1.entities.TypeChambre;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sp1.entities.Chambre;
import com.example.sp1.Services.IChambreService;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/chambre")
public class ChambreRestController {
    IChambreService iChambreService;
    IBlocService iBlocService;

    @GetMapping("/affichertout")
    public List<Chambre> retrieveAllChambres() {
        return iChambreService.retrieveAllChambres();
    }

    @PostMapping("/ajouterchambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        return iChambreService.addChambre(c);
    }

    @PutMapping("/modifierchambre")
    public Chambre updateChambre(@RequestBody Chambre c) {

        return iChambreService.updateChambre(c);
    }

    @GetMapping("/afficherchambre/{idchambre}")
    public Chambre retrieveChambre(@PathVariable("idchambre") long idChambre) {
        return iChambreService.retrieveChambre(idChambre);
    }

    @GetMapping("/chambresparuniversite")
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        return iChambreService.getChambresParNomUniversite(nomUniversite);
    }

    @GetMapping("/typecetbloc")
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return iChambreService.getChambresParBlocEtType(idBloc, typeC);
    }

    @GetMapping("/nonreserve")
    public List<Chambre> getChambresNonReserveParNomFoyerEtTypeChambre(String nomUniversite, TypeChambre typeC) {
        return iChambreService.getChambresNonReserveParNomFoyerEtTypeChambre(nomUniversite,typeC);
    }

}