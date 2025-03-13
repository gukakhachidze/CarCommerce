package ge.guka.CarCommerce.config;

import ge.guka.CarCommerce.cars.error.NotFoundException;
import ge.guka.CarCommerce.cars.user.persistence.AppUser;
import ge.guka.CarCommerce.cars.user.persistence.Role;
import ge.guka.CarCommerce.cars.user.persistence.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import ge.guka.CarCommerce.cars.user.persistence.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements ApplicationRunner {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (appUserRepository.count() ==0){
            AppUser admin = new AppUser();
            admin.setUsername("guka95");
            admin.setPassword(passwordEncoder.encode("admin123"));

            Role adminRole = roleRepository.findById(1L).orElseThrow( () -> new NotFoundException("Admin not found"));

            admin.setRoles(Set.of(adminRole));
            admin.setBalance(5000.00);
            appUserRepository.save(admin);

            System.out.println("Admin created successfully ..!");

        }
    }
}
