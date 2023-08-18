package lv.startup.BalticWaterTemp.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favorite_location")
public class FavoriteLocation {

    @Id
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "location_id")
    private String locationId;
}
