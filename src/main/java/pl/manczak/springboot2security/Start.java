package pl.manczak.springboot2security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.manczak.springboot2security.entity.AppUser;
import pl.manczak.springboot2security.repo.AppUserRepo;

@Component
public class Start {

    private PasswordEncoder passwordEncoder;
    private AppUserRepo appUserRepo;

    @Autowired
    public Start(PasswordEncoder passwordEncoder, AppUserRepo appUserRepo) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepo = appUserRepo;

        AppUser appUser= new AppUser();
        appUser.setUsername("Przemek");
        appUser.setEnabled(true);
        appUser.setPassword(passwordEncoder.encode("Przemek123"));
        appUserRepo.save(appUser);

    }
}
