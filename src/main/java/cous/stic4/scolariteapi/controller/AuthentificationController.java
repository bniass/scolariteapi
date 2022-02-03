package cous.stic4.scolariteapi.controller;

import cous.stic4.scolariteapi.dao.IUtilisateur;
import cous.stic4.scolariteapi.model.JwtRequest;
import cous.stic4.scolariteapi.model.JwtResponse;
import cous.stic4.scolariteapi.model.User;
import cous.stic4.scolariteapi.security.JwtTokenUtil;
import cous.stic4.scolariteapi.service.CustumUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthentificationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CustumUserDetailsService custumUserDetailsService;
    @Autowired
    private IUtilisateur iUtilisateur;

    private void authenticate(String username, String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED",e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS",e);
        }
    }
    @PostMapping("/login")
    public @ResponseBody JwtResponse Logon(@RequestBody JwtRequest jwtRequest) throws  Exception{
        authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        final UserDetails userDetails=custumUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token=jwtTokenUtil.generateToken(userDetails);
        if (userDetails!=null){
            User u=iUtilisateur.findByUsername(jwtRequest.getUsername());
            List<String> roles=u.getRoles().stream().map(role->role.getName()).collect(Collectors.toList());
            return new JwtResponse(u.getUsername(),u.getName(),token,roles);
        }
        return  new JwtResponse();
    }
}
