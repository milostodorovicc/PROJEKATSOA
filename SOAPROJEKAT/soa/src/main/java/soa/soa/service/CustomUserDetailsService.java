package soa.soa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import soa.soa.entity.Registrovanikorisnik;
import soa.soa.repository.RegistrovanikorisnikRepository;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private RegistrovanikorisnikRepository registrovanikorisnikRepository;

    // Funkcija koja na osnovu username-a iz baze vraca objekat User-a
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Registrovanikorisnik user =  registrovanikorisnikRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }
}
