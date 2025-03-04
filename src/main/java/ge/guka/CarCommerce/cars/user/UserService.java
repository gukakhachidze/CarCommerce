package ge.guka.CarCommerce.cars.user;

import ge.guka.CarCommerce.cars.error.NotFoundException;
import ge.guka.CarCommerce.cars.user.model.UserRequest;
import ge.guka.CarCommerce.cars.user.persistence.AppUser;
import ge.guka.CarCommerce.cars.user.persistence.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public void createUser(UserRequest request){
        AppUser user = new AppUser();
        user.setUsername(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoleIds().stream()
                .map(roleService::getRole)
                .collect(Collectors.toSet()));

        repository.save(user);
    }

    public AppUser getUser(String username){
        return repository.findByUsername(username).orElseThrow(
                () -> new NotFoundException("User not found.")
        );
    }
}
