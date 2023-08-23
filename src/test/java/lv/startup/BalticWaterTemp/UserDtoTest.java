package lv.startup.BalticWaterTemp;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lv.startup.BalticWaterTemp.core.security.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class UserDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidationSuccess() {
        UserDto userDto = new UserDto("TestUser", "test@email.com", "password123");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testEmptyNameValidation() {
        UserDto userDto = new UserDto("", "test@email.com", "password123");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
        assertEquals("Name should not be empty", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidEmailValidation() {
        UserDto userDto = new UserDto("TestUser", "invalidemail", "password123");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty());
        assertEquals("must be a well-formed email address", violations.iterator().next().getMessage());
    }

}
