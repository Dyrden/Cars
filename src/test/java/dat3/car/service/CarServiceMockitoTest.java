package dat3.car.service;

import dat3.car.dto.car.CarRequest;
import dat3.car.dto.car.CarResponse;
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
        Car car1 = Car.builder().brand("Skoda").model("XLB_825").pricePrDay(355).build();
        Car car2 = Car.builder().brand("Toyota").model("HO_91-5").pricePrDay(355).build();
        car1.setCreatedDate(LocalDateTime.now());
        car2.setCreatedDate(LocalDateTime.now());
        Mockito.when(carRepository.findAll()).thenReturn(List.of(car1,car2));

        int carsInList = carRepository.findAll().size();

        assertEquals(2, carsInList);
    }

    @Test
    void addCar() {
        Car car1 = Car.builder().brand("Skoda").model("XLB_825").pricePrDay(355).build();
        car1.setCreatedDate(LocalDateTime.now());
        Mockito.when(carRepository.save(any(Car.class))).thenReturn(car1);

        CarRequest request = new CarRequest(car1);
        CarResponse carResponse = carService.addCar(request);

        assertEquals("XLB_825", carResponse.getModel());


    }

    @Test
    void updateCar() {
        Car car1 = Car.builder().brand("Skoda").model("XLB_825").pricePrDay(355).build();
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
        Car car1 = Car.builder().brand("Skoda").model("XLB_825").pricePrDay(355).build();
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
        Car car = Car.builder().brand("Skoda").model("XLB_825").pricePrDay(355).build();
        car.setCreatedDate(LocalDateTime.now());

        Mockito.when(carRepository.findById(1)).thenReturn(Optional.of(car));

        CarResponse carResponse = carService.getCarById(id);


        assertEquals(355, carResponse.getPricePerDay());
        assertNotNull(carResponse.getCreated());





    }
}
