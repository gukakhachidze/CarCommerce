package ge.guka.CarCommerce.cars.user;

import ge.guka.CarCommerce.cars.error.NotFoundException;
import ge.guka.CarCommerce.cars.user.persistence.Role;
import ge.guka.CarCommerce.cars.user.persistence.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Role getRole(Long id){
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Role with id " + id + " not found")
        );
    }
}
