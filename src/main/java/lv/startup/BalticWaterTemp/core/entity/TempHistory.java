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
    @Column(name = "1d_ago")
    private double oneDayAgo;
    @Column(name = "2d_ago")
    private double twoDayAgo;
    @Column(name = "3d_ago")
    private double threeDayAgo;
    @Column(name = "4d_ago")
    private double fourDayAgo;
    @Column(name = "5d_ago")
    private double fiveDayAgo;
    @Column(name = "6d_ago")
    private double sixDayAgo;
    @Column(name = "7d_ago")
    private double sevenDayAgo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "location_id")
    private Location location;
}
