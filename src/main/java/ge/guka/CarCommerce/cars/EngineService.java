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

    public Page<EngineDTO> getEnginesWithoutCapacity(int page, int pageSize){
        return engineRepository.findEnginesWithoutCapacity(PageRequest.of(page,pageSize));
    }

    public EngineDTO findEngine(Long id){
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
       Engine engineToUpdate = engineRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("Engine with " + id + " not found"));
       engineToUpdate.setHorsePower(request.getHorsePower());
       engineToUpdate.setCapacity(request.getCapacity());

       engineRepository.save(engineToUpdate);
       return mapEngine(engineToUpdate);
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

    public Engine findEngineById(Long id){
        return engineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Engine with " + id + " not found"));
    }
}
