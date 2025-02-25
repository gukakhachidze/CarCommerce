package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.model.EngineDTO;
import ge.guka.CarCommerce.cars.model.EngineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/engines")
@RequiredArgsConstructor
public class EngineController {

    private final EngineService engineService;

    @GetMapping
    Page<EngineDTO> getEngines(@RequestParam int page, @RequestParam int pageSize, @RequestParam double capacity){
        return engineService.getEngines(page, pageSize, capacity);
    }

    @GetMapping("{id}")
    EngineDTO findEngine(@PathVariable Long id){
       return engineService.findEngineRR(id);
    }

    @PostMapping
    ResponseEntity<Void> createEngine(@RequestBody EngineRequest request){
        engineService.createEngine(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    EngineDTO updateEngine(@PathVariable Long id, @RequestBody EngineRequest request){
        return engineService.updateEngine(id,request);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteEngine(@PathVariable Long id){
        engineService.deleteEngine(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
