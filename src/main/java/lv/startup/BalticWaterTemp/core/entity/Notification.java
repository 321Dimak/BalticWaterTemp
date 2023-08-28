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
@Table(name = "notification")
public class Notification {

    @EmbeddedId
    private NotificationKey id;
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

    @ManyToOne
    @MapsId("userEmail")
    @JoinColumn(name = "user_email")
    private User user;

    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name = "location_id")
    private Location location;

    public Notification(NotificationKey id, double temperature, double level, User user, Location location) {
        this.id = id;
        this.temperature = temperature;
        this.level = level;
        this.user = user;
        this.location = location;
        this.tempLowerAlert = false;
        this.tempHigherAlert = false;
        this.levelLowerAlert = false;
        this.levelHigherAlert = false;
    }

    public Notification(NotificationKey id) {
        this.id = id;
    }
}
