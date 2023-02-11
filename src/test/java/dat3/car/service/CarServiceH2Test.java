package dat3.car.service;

import dat3.car.DTO.car.CarRequest;
import dat3.car.DTO.car.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class CarServiceH2Test {

    @Autowired
    CarRepository carRepository;


    CarService carService;

    boolean dataIsReady;
    int listSize = 6;




    @BeforeEach
    void setUp() {
        if (!dataIsReady) {
            carRepository.save(new Car("Toyota", "Camry", 50.0));
            carRepository.save(new Car("Honda", "Civic", 45.0));
            carRepository.save(new Car("Mazda", "3", 40.0));
            carRepository.save(new Car("Ford", "Mustang", 60.0));
            carRepository.save(new Car("Nissan", "Altima", 55.0));
            carRepository.save(new Car("BMW", "3-Series", 60.0));
            dataIsReady = true;
            carService = new CarService(carRepository);
        }

        for (CarResponse carResponse : carService.getCars()) {
            System.out.println(carResponse.getId());
        }


    }

    @Test
    void getCars() {
        List<CarResponse> cars = carService.getCars();

        assertEquals(listSize, cars.size());



    }

    @Test
    void addCar() {
        CarRequest carRequest = new CarRequest("Peugeot", "308", 40.0);

        CarResponse carResponse = carService.addCar(carRequest);

        Optional<Car> addedCar = carRepository.findById(carResponse.getId());
        assertTrue(addedCar.isPresent());
        assertEquals(carRequest.getBrand(),addedCar.get().getBrand());



    }

    @Test
    void updateCar() {

        Car car = carRepository.findAll().get(0);

        CarRequest carRequest = new CarRequest("Subaru","Impreza",40.0);
        int id = car.getId();

        CarResponse carResponse = carService.updateCar(carRequest,id);
        assertEquals("Subaru", carResponse.getBrand());
        assertEquals(id, carResponse.getId());





    }

    @Test
    void updateCarDiscount() {

        Car car = carRepository.findAll().get(0);

        int id = car.getId();
        int oldValue = car.getBestDiscount();
        int newValue = 20;

        CarResponse carResponse = carService.updateCarDiscount(id,newValue);

        assertEquals(newValue, carResponse.getMax_discount());
        assertNotEquals(oldValue,carResponse.getMax_discount());

    }

    @Test
    void deleteCar() {

        Car car = carRepository.findAll().get(0);
        assertNotNull(car);

        int id = car.getId();


        carService.deleteCar(id);

        Optional<Car> deletedCar = carRepository.findById(id);

        assertFalse(deletedCar.isPresent());

        assertEquals(5, carService.getCars().size());

    }

    @Test
    void getCarById() {
    }
}