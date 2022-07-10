package pl.manczak.springboot2security;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {

    @GetMapping ("/forAll")
    public String forAll(){
        return "forAll";

    }
    @GetMapping ("/forUser")
    public String forUser() {
        return "forUser";
    }
    @GetMapping ("/forAdmin")
    public String forAdmin() {
        return "forAdmin";
    }
}
