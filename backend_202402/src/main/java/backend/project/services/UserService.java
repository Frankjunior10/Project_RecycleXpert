package backend.project.services;

import backend.project.dtos.UserDTO;
import backend.project.entities.User;

public interface UserService {

    public User addUser (UserDTO userdto);
    public User findById (Long id);
    public User findByUsername(String userName);

    Boolean isUserVolunteer(Long userId);

    Boolean isUserOrganization(Long userId);
}
