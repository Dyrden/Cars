package dat3.car.service;

import dat3.car.DTO.car.CarRequest;
import dat3.car.DTO.car.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {

    @Mock
    CarRepository carRepository;

    CarService carService;


    @BeforeEach
    void setUp() {
        carService = new CarService(carRepository);
    }

    @Test
    void getCars() {
        Car car1 = new Car("Skoda", "XLB_825", 355);
        Car car2 = new Car("Toyota", "HO_91-5", 650);
        car1.setCreatedDate(LocalDateTime.now());
        car2.setCreatedDate(LocalDateTime.now());
        Mockito.when(carRepository.findAll()).thenReturn(List.of(car1,car2));

        int carsInList = carRepository.findAll().size();

        assertEquals(2, carsInList);
    }

    @Test
    void addCar() {
        Car car1 = new Car("Skoda", "XLB_825", 355);
        car1.setCreatedDate(LocalDateTime.now());
        Mockito.when(carRepository.save(any(Car.class))).thenReturn(car1);

        CarRequest request = new CarRequest(car1);
        CarResponse carResponse = carService.addCar(request);

        assertEquals("XLB_825", carResponse.getModel());


    }

    @Test
    void updateCar() {
        Car car1 = new Car("Skoda", "XLB_825", 355);
        car1.setId(1);
        car1.setCreatedDate(LocalDateTime.now());

        CarRequest carRequest = new CarRequest("Skoda", "XLB_955",375.0);
        Mockito.when(carRepository.findById(1)).thenReturn(java.util.Optional.of(car1));

        CarResponse carResponse = carService.updateCar(carRequest,1);

        assertEquals("XLB_955", carResponse.getModel());
        assertEquals(375, carResponse.getPricePerDay());
        assertNotNull(carResponse.getBrand());

    }

    @Test
    void updateCarDiscount() {
        Car car1 = new Car("Skoda", "XLB_825", 355);
        car1.setBestDiscount(0);
        car1.setCreatedDate(LocalDateTime.now());

        int newBestDiscount = 5;
        Mockito.when(carRepository.findById(1)).thenReturn(java.util.Optional.of(car1));

        CarResponse carResponse =  carService.updateCarDiscount(1,newBestDiscount);

        assertEquals(5,carResponse.getMax_discount());

    }

    @Test
    void deleteMember() {

        int id = 1;
        Mockito.when(carRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Car> expected = Optional.empty();

        carService.deleteCar(id);
        Mockito.verify(carRepository).deleteById(id);
        Optional<Car> actual = carRepository.findById(id);

        assertEquals(expected,actual);

    }

    @Test
    void getCarById() {
        int id = 1;
        Car car = new Car("Skoda", "XLB_825", 355);
        car.setCreatedDate(LocalDateTime.now());

        Mockito.when(carRepository.findById(1)).thenReturn(Optional.of(car));

        CarResponse carResponse = carService.getCarById(id);


        assertEquals(355, carResponse.getPricePerDay());
        assertNotNull(carResponse.getCreated());





    }
}
