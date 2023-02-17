package dat3.car.DTO.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ReservationResponse {

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime rentalDate;

    String user_username;

    String car_brand;
    String car_model;




}
