package dat3.car.service;

import dat3.car.DTO.car.CarRequest;
import dat3.car.DTO.car.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars() {
        return carRepository.findAll().stream().map((c) -> new CarResponse(c, true)).toList();

    }

    public CarResponse addCar(CarRequest body) {
        Car car = CarRequest.getCarEntity(body);
        car = carRepository.save(car);
        return new CarResponse(car, false);
    }

    public CarResponse updateCar(CarRequest body, int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found"));

        Optional.ofNullable(body.getBrand()).ifPresent(car :: setBrand);
        Optional.ofNullable(body.getModel()).ifPresent(car :: setModel);
        Optional.ofNullable(body.getPricePerDay()).ifPresent(car :: setPricePrDay);

        carRepository.save(car);
        return new CarResponse(car, false);
    }

    public CarResponse updateCarDiscount(int id, int value) {
        Car car = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found"));
        car.setBestDiscount(value);
        carRepository.save(car);
        return new CarResponse(car,true);
    }

    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    public CarResponse getCarById(int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find car"));
        return new CarResponse(car, true);
    }
}
