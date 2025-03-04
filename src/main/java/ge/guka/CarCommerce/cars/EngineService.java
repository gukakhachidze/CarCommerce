package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.error.NotFoundException;
import ge.guka.CarCommerce.cars.model.EngineDTO;
import ge.guka.CarCommerce.cars.model.EngineRequest;
import ge.guka.CarCommerce.cars.persistence.Engine;
import ge.guka.CarCommerce.cars.persistence.EngineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EngineService {
    private final EngineRepository engineRepository;

    public Page<EngineDTO> getEngines(int page, int pageSize, double capacity){
        return engineRepository.findEngines(capacity, PageRequest.of(page,pageSize));
    }

    public EngineDTO findEngineRR(Long id){
        Engine engine = engineRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Engine with id: " + id + " not found")
        );
        return mapEngine(engine);
    }

    public void createEngine(EngineRequest request){
        Engine engine = new Engine();
        engine.setHorsePower(request.getHorsePower());
        engine.setCapacity(request.getCapacity());

        engineRepository.save(engine);
    }

    public EngineDTO updateEngine(Long id, EngineRequest request){
       Engine updateToEngine = engineRepository.findById(id).get();
       updateToEngine.setHorsePower(request.getHorsePower());
       updateToEngine.setCapacity(request.getCapacity());

       engineRepository.save(updateToEngine);
       return mapEngine(updateToEngine);
    }

    public void deleteEngine(Long id){
        engineRepository.deleteById(id);
    }

    private EngineDTO mapEngine(Engine engine){
        return new EngineDTO(
                engine.getId(),
                engine.getHorsePower(),
                engine.getCapacity()
        );
    }

    public Engine findEngine(Long id){
        return engineRepository.findById(id).get();
    }
}
