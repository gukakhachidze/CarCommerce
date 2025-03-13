package ge.guka.CarCommerce.cars.persistence;

import ge.guka.CarCommerce.cars.model.EngineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {

    @Query("SELECT NEW ge.guka.CarCommerce.cars.model.EngineDTO(e.id, e.horsePower, e.capacity)" +
            "FROM Engine e WHERE e.capacity = :capacity")
    Page<EngineDTO> findEngines(double capacity, Pageable pageable);

    @Query("SELECT NEW ge.guka.CarCommerce.cars.model.EngineDTO(e.id, e.horsePower, e.capacity) FROM Engine e")
    Page<EngineDTO> findEnginesWithoutCapacity(Pageable pageable);

}
