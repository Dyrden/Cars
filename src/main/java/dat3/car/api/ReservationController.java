package dat3.car.api;


import dat3.car.dto.reservation.ReservationRequest;
import dat3.car.dto.reservation.ReservationResponse;
import dat3.car.service.ReservationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/reservation")
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationResponse makeReservation(@RequestBody ReservationRequest reservationRequest) {


        ReservationResponse reservationResponse = reservationService.makeReservation(reservationRequest);
        return reservationResponse;
    }
}
