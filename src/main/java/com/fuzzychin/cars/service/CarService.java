package com.fuzzychin.cars.service;


import com.fuzzychin.cars.bean.Car;
import com.fuzzychin.cars.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    static private Logger logger = LoggerFactory.getLogger(CarService.class);

    @Autowired
    public CarRepository carRepository;

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public void deleteCar(Car car){
        carRepository.delete(car);
    }

    public Car save(Car car){
        return  carRepository.save(car);
    }

    public Car findOneCar(long carId){
        return carRepository.findOne(carId);
    }


}
