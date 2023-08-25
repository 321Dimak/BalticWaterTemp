package lv.startup.BalticWaterTemp.core.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.startup.BalticWaterTemp.core.entity.FavoriteLocationKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteLocationDTO {

    @NotNull(message = "User email cannot be null")
    @NotEmpty(message = "User email cannot be empty")
    private String userEmail;
    @NotNull(message = "Location Id cannot be null")
    @NotEmpty(message = "Location Id cannot be empty")
    private String locationId;

    public FavoriteLocationKey toFavoriteLocationId() {
        return new FavoriteLocationKey(userEmail, locationId);
    }
}
