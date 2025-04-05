package backend.project.controllers;

import backend.project.dtos.DTOToken;
import backend.project.dtos.UserDTO;
import backend.project.entities.User;
import backend.project.security.JwtUtilService;
import backend.project.security.SecurityUser;
import backend.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
// "http://localhost:8080/api"
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtilService jwtUtilService;

    @Autowired
    UserService userService;

    //HTTP: GET, POST, PUT, DELETE
    @GetMapping("/users/{userId}/isVolunteer")
    public ResponseEntity<Boolean> isVolunteer(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.isUserVolunteer(userId));
    }

    @GetMapping("/users/{userId}/isOrganization")
    public ResponseEntity<Boolean> isOrganization(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.isUserOrganization(userId));
    }

    @PostMapping("/users/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userdto ) {
        User newUser = userService.addUser(userdto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> listAllUser(@PathVariable Long id) {
        User userFound = userService.findById(id);
        if (userFound != null) {
            System.out.println("Usuario encontrado: " + userFound.getUserName());
        } else {
            System.out.println("Usuario no encontrado");
        }
        return new ResponseEntity<>(userFound, HttpStatus.OK);
    }
    @PostMapping("/users/login")
    public ResponseEntity<DTOToken> login(@RequestBody UserDTO userdto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userdto.getUserName(), userdto.getPassword()));
        SecurityUser securityUser = new SecurityUser(userService.findByUsername(userdto.getUserName()));
        String jwt = jwtUtilService.generateToken(securityUser);
        Long user_id = securityUser.getUser().getId();

        String authString = securityUser.getUser().getAuthorities().stream().map(a->a.getName()).collect(Collectors.joining(";"));

        return new ResponseEntity<>(new DTOToken(jwt,user_id,authString ),HttpStatus.OK);

    }
}




