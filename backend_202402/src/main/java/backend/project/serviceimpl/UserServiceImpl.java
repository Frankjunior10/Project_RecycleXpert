package backend.project.serviceimpl;

import backend.project.dtos.UserDTO;
import backend.project.entities.Authority;
import backend.project.entities.User;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.UserRepository;
import backend.project.security.SecurityUser;
import backend.project.services.AuthorityService;
import backend.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityService authorityService;


    //Falta inplementar
    @Override
    public User findById(Long id){
        User userFound= userRepository.findById(id).orElse(null);
        if (userFound==null){
            throw new ResourceNotFoundException("User with id: " +  id + " does not exist");
        }
        return userFound;
    }
    @Override
    public User addUser(UserDTO userdto) {
        User user = new User();
        user.setUserName(userdto.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(userdto.getPassword()));
        user.setActive(true);
        user.setRegistrationDate(LocalDateTime.now());
        user.setAuthorities(authoritiesFromString(userdto.getAuthorities()));

        return userRepository.save(user);
    }

    private List<Authority> authoritiesFromString(String authoritiesString){
        List<Authority> authorityList = new ArrayList<>();
        List<String> authorityStringList = List.of(authoritiesString.split(";"));

        for (String authorityName : authorityStringList) {
            Authority authority = authorityService.findByName(authorityName);
            if (authority != null) {
                authorityList.add(authority);
            } else {
                throw new ResourceNotFoundException("Authority with name: " + authorityName + " does not exist");
            }
        }
        return authorityList;
    }

    @Override
    public User findByUsername(String userName) {
        User userFound= userRepository.findByUserName(userName);
        if (userFound==null){
            throw new ResourceNotFoundException("User with username: " +  userName + " does not exist");
        }
        return userFound;
    }

    @Override
    public Boolean isUserVolunteer(Long userId) {
        User user = findById(userId);
        return user != null && user.isVolunteer();
    }

    @Override
    public Boolean isUserOrganization(Long userId) {
        User user = findById(userId);
        return user != null && user.isOrganization();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SecurityUser(this.findByUsername(username));
    }
}
