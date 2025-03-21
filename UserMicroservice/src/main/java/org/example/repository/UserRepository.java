package org.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    User createUser(User user);


    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    List<User> findByNameIgnoreCase(String name);

    @Query("SELECT u FROM User u WHERE SIZE(u.bookedTickets) > 0")
    List<User> findUsersWithBookedTickets();

    User getUserById(long userId);

    Page<User> findAll(Pageable pageable);

    Void deleteUser(long userId);

}

