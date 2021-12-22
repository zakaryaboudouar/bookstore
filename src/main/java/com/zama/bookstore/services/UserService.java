package com.zama.bookstore.services;

import com.zama.bookstore.bookdto.UserDto;
import com.zama.bookstore.bookrepository.UserRepository;
import com.zama.bookstore.entities.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto addUser(UserDto userDto){
        User checkUser = userRepository.findByEmail(userDto.getEmail());
        if (checkUser != null) throw  new RuntimeException("User Already Exists");
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
    }
}
