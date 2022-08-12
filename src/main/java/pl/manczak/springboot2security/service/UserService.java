package pl.manczak.springboot2security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.manczak.springboot2security.entity.AppUser;
import pl.manczak.springboot2security.entity.VeryficationToken;
import pl.manczak.springboot2security.repo.AppUserRepo;
import pl.manczak.springboot2security.repo.VerificationTokenRepo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class UserService {

    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepo verificationTokenRepo;
    private MailSenderService mailSenderService;


    public UserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder,
                       VerificationTokenRepo verificationTokenRepo,
                       MailSenderService mailSenderService) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepo = verificationTokenRepo;
        this.mailSenderService = mailSenderService;
    }

    public void addNewUser(AppUser user, HttpServletRequest request)  {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepo.save(user);

        String token = UUID.randomUUID().toString();
        VeryficationToken veryficationToken = new VeryficationToken(token, user);
        verificationTokenRepo.save(veryficationToken);

        String url="http://"+request.getServerName()+
                ":"+
                request.getServerPort()+
                request.getContextPath()+
                "/verify-token?token="+token;



      try {
          mailSenderService.sendMail(user.getUsername(),
                  "Verification Token",
                  url,
                  false
          );

      }catch (MessagingException e){
          e.printStackTrace();
      }


    }
}
