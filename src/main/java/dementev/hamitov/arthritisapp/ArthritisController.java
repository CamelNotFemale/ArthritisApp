package dementev.hamitov.arthritisapp;

import dementev.hamitov.arthritisapp.models.ArthritisCase;
import dementev.hamitov.arthritisapp.services.ArthritisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="hospitals/{hospitalName}/arthritis")
@RequiredArgsConstructor
public class ArthritisController {

    private final ArthritisService arthritisService;

    @PostMapping(value = "/{patientsNumber}")
    public ResponseEntity addArthritisCase(@PathVariable("hospitalName") String hospitalName,
                                           @PathVariable("patientsNumber") int patientsNumber,
                                           @RequestBody ArthritisCase request) {
        int caseId = arthritisService.addArthritisCase(hospitalName, patientsNumber, request);
        return ResponseEntity.status(HttpStatus.OK).body(caseId);
    }

    @GetMapping(value = "/{patientsNumber}")
    public ResponseEntity<ArthritisCase> getArthritisCases(@PathVariable("hospitalName") String hospitalName,
                                                           @PathVariable("patientsNumber") int patientsNumber) {
        ArthritisCase arthritisCase = arthritisService.getArthritisCases(hospitalName, patientsNumber);
        if (arthritisCase != null) {
            return ResponseEntity.ok(arthritisCase);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping(value = "/{patientsNumber}")
    public ResponseEntity editArthritisCase(@PathVariable("hospitalName") String hospitalName,
                                                           @PathVariable("patientsNumber") int patientsNumber,
                                                           @RequestBody ArthritisCase request) {
        boolean res = arthritisService.editArthritisCase(hospitalName, patientsNumber, request);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @DeleteMapping(value = "/{patientsNumber}")
    public ResponseEntity<ArthritisCase> deleteArthritisCase(@PathVariable("hospitalName") String hospitalName,
                                                           @PathVariable("patientsNumber") int patientsNumber) {
        boolean res = arthritisService.deleteArthritisCase(hospitalName, patientsNumber);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}