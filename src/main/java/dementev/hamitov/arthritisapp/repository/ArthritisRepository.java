package dementev.hamitov.arthritisapp.repository;

import dementev.hamitov.arthritisapp.models.ArthritisCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArthritisRepository extends JpaRepository<ArthritisCase, Integer> {
    List<ArthritisCase> findByHospitalName(String hospitalName);
    ArthritisCase findByHospitalNameAndPatientsNumber(String hospitalName, int patientsNumber);
}
