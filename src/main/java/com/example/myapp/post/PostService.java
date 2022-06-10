package com.example.myapp.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PostService {
    PostRepository repository;

    @Autowired
    public PostService(PostRepository repository) {this.repository = repository;}

    public void updateOrSavePost(Post object){
        if (object.getId() != null){
            Optional<Post> post = repository.findById(object.getId());
            Post postToSave = post.get();
            postToSave.setText(object.getText());
            postToSave.setU_id(object.getU_id());
            repository.save(postToSave);
        }
        if (object.getId() == null){
            Post post1 = new Post();
            post1.setText(object.getText());
            post1.setU_id(object.getU_id());
            repository.save(post1);
        }
    }
}
