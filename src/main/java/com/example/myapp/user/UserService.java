package com.example.myapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService{

    UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void updateOrSave(User object) {
        Optional<User> user = repository.findById(object.getId());
        if (object.getId() != null) {
            if (user.isPresent()) {
                User userToSave = user.get();
                userToSave.setName(object.getName());
                userToSave.setEmail(object.getEmail());
                repository.save(userToSave);
            }else {
                User user1 = new User();
                user1.setName(object.getName());
                user1.setEmail(object.getEmail());
                repository.save(user1);
            }
        }
    }

}
