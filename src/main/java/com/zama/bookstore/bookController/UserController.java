package com.zama.bookstore.bookController;

import com.zama.bookstore.bookdto.UserDto;
import com.zama.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        String passwordEncrypted = userDto.getPassword();
        userDto.setPassword(bCryptPasswordEncoder.encode(passwordEncrypted));
        UserDto newUser = userService.addUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

}
