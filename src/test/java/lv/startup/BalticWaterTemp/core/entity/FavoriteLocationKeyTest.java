package lv.startup.BalticWaterTemp.core.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteLocationKeyTest {

    @Test
    void testGetterSetterAndEquals() {
        FavoriteLocationKey key1 = new FavoriteLocationKey();
        FavoriteLocationKey key2 = new FavoriteLocationKey();

        key1.setUserEmail("test@email.com");
        key1.setLocationId("location123");

        key2.setUserEmail("test@email.com");
        key2.setLocationId("location123");

        assertEquals("test@email.com", key1.getUserEmail());
        assertEquals("location123", key1.getLocationId());

        assertEquals(key1, key2);
    }

}
