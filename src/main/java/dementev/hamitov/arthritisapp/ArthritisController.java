package dementev.hamitov.arthritisapp;

import dementev.hamitov.arthritisapp.models.ArthritisCase;
import dementev.hamitov.arthritisapp.services.ArthritisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping(value="hospitals/{hospitalName}/arthritis")
@RequiredArgsConstructor
public class ArthritisController {

    private final ArthritisService arthritisService;

    @PostMapping(value = "/{patientsNumber}")
    public ResponseEntity<String> addArthritisCase(@PathVariable("hospitalName") String hospitalName,
                                           @PathVariable("patientsNumber") int patientsNumber,
                                           @RequestBody ArthritisCase request,
                                           @RequestHeader(value = "Accept-Language",required = false)
                                           Locale locale) {
        return ResponseEntity.ok(arthritisService.addArthritisCase(hospitalName, patientsNumber, request, locale));
    }

    @GetMapping(value = "/{patientsNumber}")
    public ResponseEntity<ArthritisCase> getArthritisCases(@PathVariable("hospitalName") String hospitalName,
                                                           @PathVariable("patientsNumber") int patientsNumber,
                                                           @RequestHeader(value = "Accept-Language",required = false)
                                                                       Locale locale) {
        ArthritisCase arthritisCase = arthritisService.getArthritisCase(hospitalName, patientsNumber);
        if (arthritisCase != null) {
            return ResponseEntity.ok(arthritisCase);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping(value = "/{patientsNumber}")
    public ResponseEntity<String> editArthritisCase(@PathVariable("hospitalName") String hospitalName,
                                            @PathVariable("patientsNumber") int patientsNumber,
                                            @RequestBody ArthritisCase request,
                                            @RequestHeader(value = "Accept-Language",required = false)
                                                Locale locale) {
        return ResponseEntity.ok(arthritisService.editArthritisCase(hospitalName, patientsNumber, request, locale));
    }

    @DeleteMapping(value = "/{patientsNumber}")
    public ResponseEntity<String> deleteArthritisCase(@PathVariable("hospitalName") String hospitalName,
                                                             @PathVariable("patientsNumber") int patientsNumber,
                                                             @RequestHeader(value = "Accept-Language",required = false)
                                                                 Locale locale) {
        return ResponseEntity.ok(arthritisService.deleteArthritisCase(hospitalName, patientsNumber, locale));
    }
}