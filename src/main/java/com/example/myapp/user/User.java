package com.example.myapp.user;

import com.example.myapp.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true,length = 25)
    private String email;

    @Column(nullable = false, length = 15)
    private String name;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(nullable = true)
    private String birthday;

    @OneToMany(mappedBy = "u_id", cascade = CascadeType.ALL)
    private Set<Post> posts;

}
