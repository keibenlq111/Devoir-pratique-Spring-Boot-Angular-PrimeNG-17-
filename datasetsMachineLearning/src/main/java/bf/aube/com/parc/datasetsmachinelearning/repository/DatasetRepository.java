package bf.aube.com.parc.datasetsmachinelearning.repository;

import bf.aube.com.parc.datasetsmachinelearning.entity.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, Integer> {
}
