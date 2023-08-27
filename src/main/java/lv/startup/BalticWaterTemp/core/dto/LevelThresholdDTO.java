package lv.startup.BalticWaterTemp.core.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.startup.BalticWaterTemp.core.entity.NotificationKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelThresholdDTO {

    private String userEmail;
    private String locationId;
    @DecimalMin(value = "-Infinity", message = "Level must be a valid number")
    private double level;

    public NotificationKey toNotificationKey() {
        return new NotificationKey(userEmail, locationId);
    }
}
