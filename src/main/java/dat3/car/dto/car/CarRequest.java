package dat3.car.dto.car;

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
    Integer bestDiscount;


    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;

    public static Car getCarEntity(CarRequest c) {
        return Car.builder().brand(c.brand).model(c.model).pricePrDay(c.pricePerDay).bestDiscount(c.bestDiscount).build();
    }

    public CarRequest(String brand, String model, Double pricePerDay) {
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
    }

    public CarRequest(String brand, String model, Double pricePerDay, Integer bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.bestDiscount = bestDiscount;
    }

    public CarRequest(Car car) {
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePerDay = car.getPricePrDay();

    }
}
