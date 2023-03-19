package dat3.car.api;

import dat3.car.dto.car.CarRequest;
import dat3.car.dto.car.CarResponse;
import dat3.car.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // anonymous
    @GetMapping
    List<CarResponse> getCars() {
        return carService.getCars();
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable int id) {
        return carService.getCarById(id);
    }

    //ADMIN
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CarResponse addCar(@RequestBody CarRequest body) {
        return carService.addCar(body);
    }

    //ADMIN
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id) {
        carService.updateCar(body, id);
        return ResponseEntity.ok(true);
    }

    //ADMIN
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/bestdiscount/{id}/{value}")
    CarResponse setBestDiscountForCar(@PathVariable int id, @PathVariable int value) {
        return carService.updateCarDiscount(id, value);
    }

    //ADMIN
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteCarById(@PathVariable int id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
