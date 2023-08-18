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
@Table(name = "notification")
public class Notification {

    @Id
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "location_id")
    private String locationId;
    @Column(name = "temperature")
    private double temperature;
    @Column(name = "t_lower_alert")
    private boolean tempLowerAlert;
    @Column(name = "t_higher_alert")
    private boolean tempHigherAlert;
    @Column(name = "level")
    private double level;
    @Column(name = "l_lower_alert")
    private boolean levelLowerAlert;
    @Column(name = "l_higher_alert")
    private boolean levelHigherAlert;
}
