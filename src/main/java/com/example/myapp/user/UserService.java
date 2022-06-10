package com.example.myapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService{

    UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void updateOrSaveUser(User object) {
        if (object.getId() != null) {
                Optional<User> user = repository.findById(object.getId());
                User userToSave = user.get();
                userToSave.setName(object.getName());
                userToSave.setEmail(object.getEmail());
                repository.save(userToSave);
            }
            if(object.getId() == null){
                User user1 = new User();
                user1.setName(object.getName());
                user1.setEmail(object.getEmail());
                repository.save(user1);
            }
        }
    }

