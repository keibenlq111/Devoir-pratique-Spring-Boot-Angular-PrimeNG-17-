package bf.aube.com.parc.datasetsmachinelearning.repository;

import bf.aube.com.parc.datasetsmachinelearning.entity.Experimentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperimentationRepository extends JpaRepository<Experimentation, Integer> {
}
