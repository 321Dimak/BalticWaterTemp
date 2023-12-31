package lv.startup.BalticWaterTemp.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "location")
public class Location {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "location")
    private LevelHistory levelHistory;
    @OneToOne(mappedBy = "location")
    private TempHistory tempHistory;
    @OneToMany(mappedBy = "location")
    private List<Notification> notifications;
    @OneToMany(mappedBy = "location")
    private Set<FavoriteLocation> favoriteLocations;
}
