package com.example.sp1.RestController;

import com.example.sp1.Services.IChambreService;
import com.example.sp1.Services.IUniversiteService;
import com.example.sp1.entities.Chambre;
import com.example.sp1.entities.Universite;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/universite")
public class UniversiteRestController {


    IUniversiteService iUniversiteService;

    @GetMapping("/affichertout")
    public List<Universite> retrieveAllUniversities() {
        return iUniversiteService.retrieveAllUniversities();
    }

    @PostMapping("/ajouteruniversite")
    public Universite addUniversity(@RequestBody Universite u) {
        return iUniversiteService.addUniversity(u);
    }

    @PutMapping("/modifieruniversite")
    public Universite updateUniversity(@RequestBody Universite u) {

        return iUniversiteService.updateUniversity(u);
    }

    @GetMapping("/afficheruniversite/{iduniversite}")
    public Universite retrieveUniversity(@PathVariable("iduniversite") long idUniversite) {
        return iUniversiteService.retrieveUniversity(idUniversite);
    }

    @PostMapping("/affecterFoyer")
    public ResponseEntity<String> affecterFoyerAUniversite(@RequestBody Map<String, Object> request) {
        Long idFoyer = Long.valueOf(request.get("idFoyer").toString());
        String nomUniversite = (String) request.get("nomUniversite");

        String message = iUniversiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);

        if (message.contains("Erreur")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    @PostMapping("/desaffecterFoyer")
    public ResponseEntity<Universite> desaffecterFoyerAUniversite(@RequestBody Map<String, Object> request) {
        Long idFoyer = null;
        Long idUniversite = null;
        if (request.containsKey("idFoyer") && request.containsKey("idUniversite")) {
            idFoyer = Long.valueOf(request.get("idFoyer").toString());
            idUniversite = Long.valueOf(request.get("idUniversite").toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Universite universite = iUniversiteService.desaffecterFoyerAUniversite(idFoyer, idUniversite);
        if (universite != null) {
            return ResponseEntity.status(HttpStatus.OK).body(universite);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}

