package lv.startup.BalticWaterTemp.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FavoriteLocationKey implements Serializable {

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "location_id")
    private String locationId;
}
