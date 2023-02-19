package dat3.car.DTO.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    Integer id;
    Integer carId;
    String car_brand;
    String car_model;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate rentalDate;


    public ReservationResponse (Reservation r) {
        this.id = r.getId();
        this.rentalDate = r.getRentalDate();
        this.car_brand = r.getCar().getBrand();
        this.car_model = r.getCar().getModel();


    }



}
