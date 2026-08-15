package bf.aube.com.parc.datasetsmachinelearning.repository;

import bf.aube.com.parc.datasetsmachinelearning.entity.ModeleML;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeleMLRepository extends JpaRepository<ModeleML, Integer> {
}
