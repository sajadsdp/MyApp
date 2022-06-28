package com.example.myapp.user;

import com.example.myapp.ExceptionClasses.PostException;
import com.example.myapp.ExceptionClasses.UserException;
import com.example.myapp.Utils.ValidationValues;
import com.example.myapp.post.Post;
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


    public void updateOrSaveUser(User object) throws Exception {
        if (object.getId() != null) {
//            try {
                Optional<User> user = repository.findById(object.getId());
                User userToSave = user.get();
                if (object.getName().isEmpty() || object.getName().length() <= 3 || object.getName().length() >= 15){
                    throw new Exception(ValidationValues.INVALID_NAME);
                }else {
                    userToSave.setName(object.getName());
                }
                if (!object.getEmail().isEmpty() && object.getEmail().matches("/^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$/")) {
                    userToSave.setEmail(object.getEmail());
                }else {
                    throw new Exception(ValidationValues.INVALID_EMAIL);
                }
                if (!object.getBirthday().isEmpty()) {
                    userToSave.setBirthday(object.getBirthday());
                }else {
                    throw new Exception(ValidationValues.INVALID_BIRTH_DATE);
                }
                repository.save(userToSave);
            }
//            catch (PostException postException) {
//                throw new PostException(postException.getMessage());
//            } catch (UserException userException) {
//                throw new UserException(userException.getMessage());
//            } catch (Exception e) {
//                throw new Exception("nemidunam che kossheri shode");
//            }
//        }
            if(object.getId() == null){
                User user1 = new User();
//                try {
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
//                }
//                catch (Exception e){
//                    throw new Exception("dg bad ridi");
//                }
            }
        }

    }

