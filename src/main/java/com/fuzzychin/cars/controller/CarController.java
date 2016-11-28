package com.fuzzychin.cars.controller;


import com.fuzzychin.cars.bean.Car;
import com.fuzzychin.cars.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car")
public class CarController {

    static private Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    public CarService carService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> queryCar() {
        return ResponseEntity.ok(carService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.save(car));
    }

    @RequestMapping(path = "/{carId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCar(@PathVariable("carId") Long carId, @RequestBody Car updatedCar) {
        Car car = carService.findOneCar(carId);

        if (car != null) {
            if (!car.getMake().equals(updatedCar.getMake())){
                car.setMake(updatedCar.getMake());
            }
            if (!car.getModel().equals(updatedCar.getModel())){
                car.setModel(updatedCar.getModel());
            }
            if (!car.getYear().equals(updatedCar.getYear())){
                car.setYear(updatedCar.getYear());
            }
            if (!car.getColor().equals(updatedCar.getColor())){
                car.setColor(updatedCar.getColor());
            }
            carService.save(car);
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(path = "/{carId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCar(@PathVariable("carId") Long carId) {
        Car car = carService.findOneCar(carId);

        if(car != null){
            carService.deleteCar(car);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
