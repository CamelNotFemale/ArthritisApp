package dementev.hamitov.arthritisapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="arthritis_db")
public class ArthritisCase extends RepresentationModel<ArthritisCase> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String hospitalName;
    private int patientsNumber;
    private LocalDate illnessDate;
    private ArthritisType arthritisType;
    @ElementCollection
    @CollectionTable(name="treatments", joinColumns=@JoinColumn(name="id"))
    @Column(name="treatment")
    private List<String> treatment;

    public void updateFromOther(ArthritisCase arthritisCaseUpd) {
        this.illnessDate = arthritisCaseUpd.getIllnessDate();
        this.arthritisType = arthritisCaseUpd.getArthritisType();
        this.treatment = new ArrayList<>(arthritisCaseUpd.getTreatment());
    }
}
