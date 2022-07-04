package com.example.myapp.user;

import com.example.myapp.Aop.Log;
import com.example.myapp.ExceptionClasses.PostException;
import com.example.myapp.ExceptionClasses.UserException;
import com.example.myapp.Utils.ValidationValues;
import com.example.myapp.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    @Log
    public User updateOrSaveUser(User object) throws Exception {
        if (object.getId() != null) {
            Optional<User> user = repository.findById(object.getId());
            User userToSave = user.get();
            if (object.getName().isEmpty() || object.getName().length() <= 3 || object.getName().length() >= 15) {
                throw new Exception(ValidationValues.INVALID_NAME);
            } else {
                userToSave.setName(object.getName());
            }
            if (!object.getEmail().isEmpty() && object.getEmail().matches("/^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$/")) {
                userToSave.setEmail(object.getEmail());
            } else {
                throw new Exception(ValidationValues.INVALID_EMAIL);
            }
            if (!object.getBirthday().isEmpty()) {
                userToSave.setBirthday(object.getBirthday());
            } else {
                throw new Exception(ValidationValues.INVALID_BIRTH_DATE);
            }
            repository.save(userToSave);
        }

        if (object.getId() == null) {
            User user1 = new User();
            if (object.getName().isEmpty() || object.getName().length() <= 3 || object.getName().length() >= 15) {
                throw new Exception(ValidationValues.INVALID_NAME);
            } else {
                user1.setName(object.getName());
            }
            if (!object.getEmail().isEmpty() && object.getEmail().matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
                user1.setEmail(object.getEmail());
            } else {
                throw new Exception(ValidationValues.INVALID_EMAIL);
            }
            if (!object.getBirthday().isEmpty()) {
                user1.setBirthday(object.getBirthday());
            } else {
                throw new Exception(ValidationValues.INVALID_BIRTH_DATE);
            }
            repository.save(user1);
        }
        return object;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

}

