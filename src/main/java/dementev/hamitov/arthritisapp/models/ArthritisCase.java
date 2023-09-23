package dementev.hamitov.arthritisapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ArthritisCase {

    private int id;
    private String hospitalName;
    private int patientsNumber;
    private LocalDate illnessDate;
    private ArthritisType arthritisType;

    public void updateFromOther(ArthritisCase arthritisCaseUpd) {
        this.illnessDate = arthritisCaseUpd.getIllnessDate();
        this.arthritisType = arthritisCaseUpd.getArthritisType();
    }
}
