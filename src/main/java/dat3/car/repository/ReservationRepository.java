package dat3.car.repository;

import dat3.car.entity.Car;
import dat3.car.entity.Reservation;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    boolean findReservationByCar_IdAndRentalDate(int car_id, LocalDateTime rentalDate);

}
