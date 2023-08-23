package lv.startup.BalticWaterTemp.core.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelThresholdDTO {

    private String userEmail;
    private String locationId;
    private double temperature = Integer.MAX_VALUE;
    @DecimalMin(value = "-Infinity", message = "Level must be a valid number")
    private double level;

    public LevelThresholdDTO(String userEmail, String locationId, double level) {
        this.userEmail = userEmail;
        this.locationId = locationId;
        this.level = level;
    }
}
