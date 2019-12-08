package org.htp.ex.service;

import org.htp.ex.dao.UserDAO;
import org.htp.ex.model.Role;
import org.htp.ex.model.Trip;
import org.htp.ex.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userDAO.findByUsername(username);
    }

    public List<User> findAll () {
        return userDAO.findAll();
    }

    public boolean addUser (User user) {

        User userFromDB = userDAO.findByUsername(user.getUsername());

        if(userFromDB != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userDAO.save(user);
        return true;
    }

    public void userRole(User user, Map<String, Object> model) {
        if(user.getRoles().contains(Role.ADMIN)){
            model.put("isAdmin",true);
        }
    }

    public List<User> findAllUsers () {
        return userDAO.findAll();
    }

    public void saveUserByHisRole (User user, Map<String, String> form) {

        //из Enum в список set (строковый вид)
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userDAO.save(user);
    }

    public void like (User user, Trip trip) {
        user.getFavoriteTrips().add(trip);
        addUser(user);
    }
    public void dislike (User user, Trip trip) {
        user.getFavoriteTrips().remove(trip);
        addUser(user);
    }
}
