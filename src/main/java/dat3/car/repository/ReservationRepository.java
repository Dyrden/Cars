package dat3.car.repository;

import dat3.car.entity.Car;
import dat3.car.entity.Reservation;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    List<Reservation> findByRentalDate(LocalDate rentalDate);
}
