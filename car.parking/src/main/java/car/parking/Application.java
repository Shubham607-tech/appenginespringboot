package car.parking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "car.parking", "car.parking.Application", "car.parking.controller",
		"car.parking.service.ParkingLotService", "car.parking.serviceImpl" })
public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOG.info("Start");
		SpringApplication.run(Application.class, args);
		LOG.info("End");
	}

}
