package org.htp.ex.dao;
//
import org.htp.ex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {

    User findByUsername(String username);

}
