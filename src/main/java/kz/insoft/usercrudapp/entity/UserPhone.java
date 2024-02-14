package kz.insoft.usercrudapp.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_phone")
public class UserPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Override
    public String toString() {
        return "UserPhone{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
