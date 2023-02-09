package dat3.car.DTO.car;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {

    int id; //Remember this is the primary key
    String brand;
    String model;
    Double pricePerDay;
    Integer max_discount;


    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;


    public CarResponse(Car car, boolean includeAll) {
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.created = car.getCreatedDate();
        this.edited = car.getLastEditedDate();
        if (includeAll) {
            this.max_discount = car.getBestDiscount();
        }

    }
}
