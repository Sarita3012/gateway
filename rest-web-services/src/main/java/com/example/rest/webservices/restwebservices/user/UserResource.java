package com.example.rest.webservices.restwebservices.user;

import com.example.rest.webservices.restwebservices.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    // NOTE :Get Users----retrieve all users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){

        return service.findAll();
    }

    //NOTE : Get users/{id} -----retrieve specific user detail
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveOneUser(@PathVariable int id){
        User user= service.findOne(id);

        if(user==null){
            throw new UserNotFoundException("Id-"+id);
        }
        // This is used for provide a link to back to all users
        EntityModel<User> model=EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers=linkTo(methodOn(getClass()).retrieveAllUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
        ///users
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user= service.deleteById(id);

        if(user==null){
            throw new UserNotFoundException("Id-"+id);
        }

    }

    //NOTE : This is for status code
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser= service.save(user);

        //CREATED
        ///Users/{id}  savedUser.getId()

        // NOTE : This method is created for the user to know the location where new record is created
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();


    }
}
