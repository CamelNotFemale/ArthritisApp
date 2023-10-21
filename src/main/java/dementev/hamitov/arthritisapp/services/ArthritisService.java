package dementev.hamitov.arthritisapp.services;

import dementev.hamitov.arthritisapp.models.ArthritisCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ArthritisService {
    private List<ArthritisCase> memoryDB= new ArrayList<>();
    private int indexDB = 0;
    private final MessageSource messages;

    public String addArthritisCase(String hospitalName, int patientsNumber, ArthritisCase request, Locale locale) {
        if (checkExistence(hospitalName, patientsNumber)) {
            return messages.getMessage("athrtitis.create.error.message", null, locale);
        }
        request.setId(indexDB);
        request.setHospitalName(hospitalName);
        request.setPatientsNumber(patientsNumber);
        memoryDB.add(request);
        return String.format(messages.getMessage("athrtitis.create.message", null, locale), request);
    }

    private boolean checkExistence(String hospitalName, int patientsNumber) {
        for (ArthritisCase atrCase: memoryDB) {
            if (atrCase.getHospitalName().equals(hospitalName) && atrCase.getPatientsNumber() == patientsNumber) {
                return true;
            }
        }
        return false;
    }

    public ArthritisCase getArthritisCase(String hospitalName, int patientsNumber) {
        for (ArthritisCase arthritisCase: memoryDB) {
            if (arthritisCase.getHospitalName().equals(hospitalName) && arthritisCase.getPatientsNumber() == patientsNumber) {
                return arthritisCase;
            }
        }
        return null;
    }

    public String editArthritisCase(String hospitalName, int patientsNumber, ArthritisCase arthritisCaseUpd, Locale locale) {
        ArthritisCase arthritisCase = getArthritisCase(hospitalName, patientsNumber);
        if (arthritisCase != null) {
            arthritisCase.updateFromOther(arthritisCaseUpd);
            return String.format(messages.getMessage("athrtitis.update.message", null, locale), arthritisCaseUpd);
        } else {
            return messages.getMessage("athrtitis.get.error.message", null, locale);
        }
    }

    public String deleteArthritisCase(String hospitalName, int patientsNumber, Locale locale) {
        ArthritisCase arthritisCase = getArthritisCase(hospitalName, patientsNumber);
        if (arthritisCase != null) {
            memoryDB.remove(arthritisCase);
            return String.format(messages.getMessage("athrtitis.delete.message", null, locale),
                    arthritisCase.getArthritisType().getName(), patientsNumber, hospitalName);
        } else {
            return messages.getMessage("athrtitis.get.error.message", null, locale);
        }
    }
}