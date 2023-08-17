package lv.startup.BalticWaterTemp;

import lv.startup.BalticWaterTemp.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BalticWaterTempApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebConfiguration.class);
	}

}
