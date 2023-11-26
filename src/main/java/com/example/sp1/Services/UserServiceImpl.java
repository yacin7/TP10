package com.example.sp1.Services;

import com.example.sp1.Repositories.UserRepo;
import com.example.sp1.entities.User;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService{
    public UserRepo userRepo;
    @Override
    public User addUser(User user){
        return userRepo.save(user);
    }
}
