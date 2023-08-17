package lv.startup.BalticWaterTemp.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User
{

    @Column(name="name")
    private String name;

    @Id
    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

}
