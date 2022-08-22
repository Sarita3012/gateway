package com.example.rest.webservices.restwebservices.user;

import com.example.rest.webservices.restwebservices.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    @Autowired
    private UserDaoService service;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    // NOTE :Get Users----retrieve all users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){

        return userRepository.findAll();
    }

    //NOTE : Get users/{id} -----retrieve specific user detail
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveOneUser(@PathVariable int id){
        Optional<User> user= userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException("Id-"+id);
        }
        // This is used for provide a link to back to all users
        EntityModel<User> model=EntityModel.of(user.get());
        WebMvcLinkBuilder linkToUsers=linkTo(methodOn(getClass()).retrieveAllUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
        ///users
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    //NOTE : This is for status code
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser= userRepository.save(user);

        //CREATED
        ///Users/{id}  savedUser.getId()

        // NOTE : This method is created for the user to know the location where new record is created
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUsers(@PathVariable int id){
            Optional<User> userOptional=userRepository.findById(id);
            if (!userOptional.isPresent())
            {
                throw new UserNotFoundException("Id-"+id);
            }

        return userOptional.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> userOptional= userRepository.findById(id);

        if (!userOptional.isPresent())
        {
            throw new UserNotFoundException("Id-"+id);
        }

        User user=userOptional.get();

        post.setUser(user);

        postRepository.save(post);

        //CREATED
        ///Users/{id}  savedUser.getId()

        // NOTE : This method is created for the user to know the location where new record is created
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
