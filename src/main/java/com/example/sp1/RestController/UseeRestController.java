package com.example.sp1.RestController;

import com.example.sp1.Services.IUserService;
import com.example.sp1.entities.Bloc;
import com.example.sp1.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UseeRestController {
    IUserService iUserService;
    @PostMapping("/ajouteruser")
    public User addUser(@RequestBody User user){
        return iUserService.addUser(user);
    }
}
