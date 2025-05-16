package org.example.repository;

// import org.springframework.data.domain.Page;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.example.model.User;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;

// import javax.swing.text.html.Option;
// import java.awt.print.Pageable;
// import java.util.List;
// import java.util.Optional;


// @Repository
// public interface UserRepository extends JpaRepository<User, Long > {

//     User createUser(User user);


//     Optional<User> findByEmail(String email);

//     Optional<User> findByPhone(String phone);

//     List<User> findByNameIgnoreCase(String name);

//     @Query("SELECT u FROM User u WHERE SIZE(u.bookedTickets) > 0")
//     List<User> findUsersWithBookedTickets();

//     User getUserById(long userId);

//     Page<User> findAll(Pageable pageable);

//     Void deleteUser(long userId);

// }



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.example.model.User;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Built-in methods (no need to declare):
    // User save(User user);               // Replaces createUser()
    // Optional<User> findById(Long id);   // Replaces getUserById()
    // void deleteById(Long id);           // Replaces deleteUser()

    // Custom query methods
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    List<User> findByNameIgnoreCase(String name);

    // Custom JPQL query
    // @Query("SELECT u FROM User u WHERE SIZE(u.bookedTickets) > 0")
    // List<User> findUsersWithBookedTickets();

    // Pagination (uses Spring's Pageable)
    Page<User> findAll(Pageable pageable);
}