package lv.startup.BalticWaterTemp.core.security;

import java.util.Collections;

import lombok.RequiredArgsConstructor;
import lv.startup.BalticWaterTemp.core.database.JpaUserRepository;
import lv.startup.BalticWaterTemp.core.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final JpaUserRepository JpaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = JpaUserRepository.findByEmail(email);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    Collections.emptyList());
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}
