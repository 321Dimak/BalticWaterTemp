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
@Table(name="temp_history")
public class TempHistory {

    @Id
    @Column(name = "location_id")
    private String locationId;
    @Column(name = "1d_ego")
    private double oneDayAgo;
    @Column(name = "2d_ego")
    private double twoDayAgo;
    @Column(name = "3d_ego")
    private double threeDayAgo;
    @Column(name = "4d_ego")
    private double fourDayAgo;
    @Column(name = "5d_ego")
    private double fiveDayAgo;
    @Column(name = "6d_ego")
    private double sixDayAgo;
    @Column(name = "7d_ego")
    private double sevenDayAgo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "location_id")
    private Location location;

}
