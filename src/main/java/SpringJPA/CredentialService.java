package SpringJPA;

import SpringJPA.Model.User;
import SpringJPA.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CredentialService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = repository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserCredentials(user);
    }
}
