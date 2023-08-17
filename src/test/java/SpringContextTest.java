import lombok.RequiredArgsConstructor;
import lv.startup.BalticWaterTemp.config.SpringCoreConfiguration;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringCoreConfiguration.class)
@RequiredArgsConstructor
public class SpringContextTest {


    private final ApplicationContext context;

    @Test
    public void start() {
        assertNotNull(context);
    }
}
