package dementev.hamitov.arthritisapp.services;

import dementev.hamitov.arthritisapp.models.ArthritisCase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArthritisService {
    private List<ArthritisCase> memoryDB= new ArrayList<>();
    private int indexDB = 0;

    public int addArthritisCase(String hospitalName, int patientsNumber, ArthritisCase request) {
        if (checkExistence(hospitalName, patientsNumber)) {
            return -1;
        }
        request.setId(indexDB);
        request.setHospitalName(hospitalName);
        request.setPatientsNumber(patientsNumber);
        memoryDB.add(request);
        return indexDB++;
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

    public boolean editArthritisCase(String hospitalName, int patientsNumber, ArthritisCase arthritisCaseUpd) {
        ArthritisCase arthritisCase = getArthritisCase(hospitalName, patientsNumber);
        if (arthritisCase != null) {
            arthritisCase.updateFromOther(arthritisCaseUpd);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteArthritisCase(String hospitalName, int patientsNumber) {
        ArthritisCase arthritisCase = getArthritisCase(hospitalName, patientsNumber);
        if (arthritisCase != null) {
            return memoryDB.remove(arthritisCase);
        } else {
            return false;
        }
    }
}