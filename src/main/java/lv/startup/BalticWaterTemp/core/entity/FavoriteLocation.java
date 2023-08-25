package lv.startup.BalticWaterTemp.core.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favorite_location")
public class FavoriteLocation {

    @EmbeddedId
    private FavoriteLocationKey id;

    @ManyToOne
    @MapsId("userEmail")
    @JoinColumn(name = "user_email")
    private User user;

    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name = "location_id")
    private Location location;


}
