package lv.startup.BalticWaterTemp.core.dto;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempThresholdDTO {

    private String userEmail;
    private String locationId;
    @DecimalMin(value = "-Infinity", message = "Temperature must be a valid number")
    private double temperature;
    private double level = Integer.MAX_VALUE;
}
