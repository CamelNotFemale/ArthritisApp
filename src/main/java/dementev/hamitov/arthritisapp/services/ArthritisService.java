package dementev.hamitov.arthritisapp.services;

import dementev.hamitov.arthritisapp.config.ServiceConfig;
import dementev.hamitov.arthritisapp.models.ArthritisCase;
import dementev.hamitov.arthritisapp.repository.ArthritisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ArthritisService {
    private int indexDB = 0;
    private final MessageSource messages;
    private final ArthritisRepository arthritisRepository;
    private final ServiceConfig config;

    public String addArthritisCase(String hospitalName, int patientsNumber, ArthritisCase request, Locale locale) {
        if (checkExistence(hospitalName, patientsNumber)) {
            return messages.getMessage("athrtitis.create.error.message", null, locale);
        }
        request.setId(indexDB);
        request.setHospitalName(hospitalName);
        request.setPatientsNumber(patientsNumber);
        arthritisRepository.save(request);
        return String.format(messages.getMessage("athrtitis.create.message", null, locale), request);
    }

    private boolean checkExistence(String hospitalName, int patientsNumber) {
        return arthritisRepository.findByHospitalNameAndPatientsNumber(hospitalName, patientsNumber) != null;
    }

    public ArthritisCase getArthritisCase(String hospitalName, int patientsNumber, Locale locale) {
        ArthritisCase arthritisCase = arthritisRepository.findByHospitalNameAndPatientsNumber(hospitalName, patientsNumber);
        if (arthritisCase == null) {
            throw new IllegalArgumentException(
                    String.format(messages.getMessage("athrtitis.search.error.message", null, locale), hospitalName, patientsNumber));
        }
        return arthritisCase;
    }

    public String editArthritisCase(String hospitalName, int patientsNumber, ArthritisCase arthritisCaseUpd, Locale locale) {
        ArthritisCase arthritisCase = arthritisRepository.findByHospitalNameAndPatientsNumber(hospitalName, patientsNumber);
        if (arthritisCase != null) {
            arthritisCase.updateFromOther(arthritisCaseUpd);
            arthritisRepository.save(arthritisCase);
            return String.format(messages.getMessage("athrtitis.update.message", null, locale), arthritisCaseUpd);
        } else {
            return messages.getMessage("athrtitis.get.error.message", null, locale);
        }
    }

    public String deleteArthritisCase(String hospitalName, int patientsNumber, Locale locale) {
        ArthritisCase arthritisCase = getArthritisCase(hospitalName, patientsNumber, locale);
        if (arthritisCase != null) {
            arthritisRepository.delete(arthritisCase);
            return String.format(messages.getMessage("athrtitis.delete.message", null, locale),
                    arthritisCase.getArthritisType().getName(), patientsNumber, hospitalName);
        } else {
            return messages.getMessage("athrtitis.get.error.message", null, locale);
        }
    }
}