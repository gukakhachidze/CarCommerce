package ge.guka.CarCommerce.cars.persistence;

import ge.guka.CarCommerce.cars.model.CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(
            "SELECT NEW ge.guka.CarCommerce.cars.model.CarDTO" +
                    "(c.id, c.model, c.year, c.driveable, " +
                    "new ge.guka.CarCommerce.cars.model.EngineDTO(e.id, e.horsePower, e.capacity))" +
                    " FROM Car c JOIN c.engine e"
    )
    Page<CarDTO> findCars(Pageable pageable);
}
