package backend.project.services;

import backend.project.entities.Authority;
import backend.project.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface AuthorityService {

    public Authority findById(Long id);
    public Authority addAuthority(Authority authority);
    public Authority findByName(String name);

}
