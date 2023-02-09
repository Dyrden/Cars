package dat3.car.DTO.car;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.car.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CarRequest {

    int id;
    String brand;
    String model;
    Double pricePerDay;
    Integer max_discount;


    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;

    public static Car getCarEntity(CarRequest c) {
        return new Car(c.brand, c.model, c.pricePerDay);
    }

    public CarRequest(String brand, String model, Double pricePerDay) {
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
    }
}