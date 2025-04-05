package backend.project.serviceimpl;

import backend.project.entities.Authority;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.AuthorityRepository;
import backend.project.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public Authority findById(Long id) {

        Authority authorityFound = authorityRepository.findById(id).orElse(null);
        if (authorityFound == null) {
            throw new ResourceNotFoundException("Authority with id:" +  id + " does not exist");
        }
        return null;

    }
    @Override
    public Authority addAuthority(Authority authority) {return authorityRepository.save(authority);}

    @Override
    public Authority findByName(String name) {
        Authority authorityFound = authorityRepository.findByName(name);
        if (authorityFound == null) {
            throw new ResourceNotFoundException("Authority with name: " + name + " does not exist");
        }
        return authorityFound;
    }


}

