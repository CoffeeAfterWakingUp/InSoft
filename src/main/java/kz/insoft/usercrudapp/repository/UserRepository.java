package kz.insoft.usercrudapp.repository;

import kz.insoft.usercrudapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByAddressIn(List<String> addressList);

    @Query(value = "select count(u) from User u", nativeQuery = false)
    int findUsersCount();

    User findByBirthDateAfter(LocalDate date);

    User findByBirthDateBefore(LocalDate date);

    @Modifying
    @Transactional
    @Query("delete from User u where u.email=:email")
    void deleteByEmail(@Param(value = "email") String email);
}
