import lv.startup.BalticWaterTemp.config.SpringCoreConfiguration;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringCoreConfiguration.class)
public class SpringContextTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void start() {
        assertNotNull(context);
    }
}
