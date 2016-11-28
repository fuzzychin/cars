package com.fuzzychin.cars;

import com.fuzzychin.cars.bean.Car;
import com.fuzzychin.cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarsApplication.class, args);
	}
}

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class seedCar implements CommandLineRunner {

	@Autowired
	private CarRepository carRepository;

	@Override
	public void run(String... args) throws Exception {

		List<Car> carList = new ArrayList<>();
		carList.add(new Car("2015", "Ford", "Mustang", "Blue"));
		carList.add(new Car("2013", "Ford", "Focus", "Yellow"));
		carList.add(new Car("1996", "Geo", "Metro", "White"));

		for (int i=0; i<carList.size(); i++) {
			Car car = new Car();
			car.setYear(carList.get(i).getYear());
			car.setMake(carList.get(i).getMake());
			car.setModel(carList.get(i).getModel());
			car.setColor(carList.get(i).getColor());
			carRepository.save(car);
		}

	}

}
