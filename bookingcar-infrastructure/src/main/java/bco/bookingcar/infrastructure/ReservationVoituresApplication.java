package bco.bookingcar.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@ComponentScan(basePackages = { "bco.bookingcar" })
@EntityScan(basePackageClasses = { ReservationVoituresApplication.class, Jsr310JpaConverters.class })

public class ReservationVoituresApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationVoituresApplication.class, args);
	}

}
