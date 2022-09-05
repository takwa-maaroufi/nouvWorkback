package work365.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import work365.work.model.JwtRequest;
import work365.work.model.JwtResponse;
import work365.work.service.JwtService;

import javax.annotation.PostConstruct;

@CrossOrigin(origins = "**", maxAge = 3600)
@RestController
public class JwtController {
    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
    return jwtService.createJwtToken(jwtRequest);
    }
}
