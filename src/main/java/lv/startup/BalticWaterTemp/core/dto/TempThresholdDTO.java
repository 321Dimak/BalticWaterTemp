package lv.startup.BalticWaterTemp.core.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.startup.BalticWaterTemp.core.entity.FavoriteLocationKey;
import lv.startup.BalticWaterTemp.core.entity.NotificationKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempThresholdDTO {

    private String userEmail;
    private String locationId;
    @DecimalMin(value = "-Infinity", message = "Temperature must be a valid number")
    private double temperature;

    public NotificationKey toNotificationKey() {
        return new NotificationKey(userEmail, locationId);
    }
}
