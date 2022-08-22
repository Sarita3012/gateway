package com.example.rest.webservices.restwebservices.user;

import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Component
public class UserDaoService {
    private static List<User> users=new ArrayList<>();

   static{
       users.add(new User(1, "John", new Date()));
       users.add(new User(2, "Jack", new Date()));
       users.add(new User(3, "Sam", new Date()));
   }

    private static int userCount=3;
   public List<User> findAll(){
       return users;
   }

   public User save(User user){
       if(user.getId()==0){
           user.setId(++userCount);
       }
       users.add(user);
       return user;
   }

   public User findOne(int id){
       for(User user:users){
           if (user.getId()==id) {
               return user;
           }
       }
       return null;
   }

    public User deleteById(int id){
        Iterator<User> iterator= users.iterator();
        while(iterator.hasNext()){
            User user=iterator.next();
            if (user.getId()==id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
