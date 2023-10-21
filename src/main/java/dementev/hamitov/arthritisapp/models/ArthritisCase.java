package dementev.hamitov.arthritisapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ArthritisCase {

    private int id;
    private String hospitalName;
    private int patientsNumber;
    private LocalDate illnessDate;
    private ArthritisType arthritisType;
    private List<String> treatment;

    public void updateFromOther(ArthritisCase arthritisCaseUpd) {
        this.illnessDate = arthritisCaseUpd.getIllnessDate();
        this.arthritisType = arthritisCaseUpd.getArthritisType();
        this.treatment = new ArrayList<>(arthritisCaseUpd.getTreatment());
    }
}
