package lv.startup.BalticWaterTemp.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "name", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;
    @OneToMany(mappedBy = "user")
    private List<FavoriteLocation> favoriteLocations;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
