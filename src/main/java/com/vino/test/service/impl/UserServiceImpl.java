package com.vino.test.service.impl;

import com.vino.test.models.Users;
import com.vino.test.repository.UserRepository;
import com.vino.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(s);
        detailsChecker.check(user);
        return user;
    }
}
