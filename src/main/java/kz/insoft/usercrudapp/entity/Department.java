package kz.insoft.usercrudapp.entity;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
@EqualsAndHashCode(exclude = {"users"})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "user_department",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        if (users == null) {
            users = new HashSet<>();
        }
        users.add(user);
        user.getDepartments().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getDepartments().remove(this);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
