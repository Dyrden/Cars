package dat3.car.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate rentalDate;

    String username;

    int car_id;




}
