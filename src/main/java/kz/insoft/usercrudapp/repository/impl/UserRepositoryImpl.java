package kz.insoft.usercrudapp.repository.impl;

import kz.insoft.usercrudapp.entity.User;
import kz.insoft.usercrudapp.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1L, "User1Name", "User1Surname", "user1@mail.com", "User1Address", LocalDate.of(2001, Month.JANUARY, 1)));
        users.add(new User(2L, "User2Name", "User2Surname", "user2@mail.com", "User2Address", LocalDate.of(2002, Month.MARCH, 2)));
        users.add(new User(3L, "User3Name", "User3Surname", "user3@mail.com", "User3Address", LocalDate.of(2003, Month.FEBRUARY, 3)));
        users.add(new User(4L, "User4Name", "User4Surname", "user4@mail.com", "User4Address", LocalDate.of(2004, Month.MAY, 4)));
        users.add(new User(5L, "User5Name", "User5Surname", "user5@mail.com", "User5Address", LocalDate.of(2005, Month.DECEMBER, 5)));
    }


    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(new User());
    }

    @Override
    public boolean create(User user) {
        Long id = getNextId();
        if (user != null) {
            user.setId(id);
            return users.add(user);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        User user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(new User());
        if (user.getId() != null) {
            users.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(User user, Long id) {
        User oldUser = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(new User());
        int index = users.indexOf(oldUser);
        user.setId(oldUser.getId());

        users.set(index, user);

        return true;
    }

    private Long getNextId() {
        return Long.valueOf(users.size() + 1);
    }
}
