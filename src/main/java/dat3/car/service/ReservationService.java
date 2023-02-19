package dat3.car.service;


import dat3.car.DTO.reservation.ReservationRequest;
import dat3.car.DTO.reservation.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;

    MemberRepository memberRepository;

    CarRepository carRepository;

    public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
    }

    public ReservationResponse makeReservation(ReservationRequest reservationRequest) {


        LocalDate today = LocalDate.now();

        if (reservationRequest.getRentalDate().isBefore(today)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rental date is in the past");
        }

        Member member = memberRepository.findMemberByUsername(reservationRequest.getUsername());

        if (member == null) {
            throw new EntityNotFoundException("No such member in the system");
        }

        Optional<Car> carOptional = carRepository.findById(reservationRequest.getCar_id());

        if(carOptional.isEmpty()) {
            throw new EntityNotFoundException("No such car in the system");
        }

        Car car = carOptional.get();

        List<Reservation> reservations = reservationRepository.findByRentalDate(reservationRequest.getRentalDate());

        for (Reservation reservation : reservations) {
            if (reservation.getCar().equals(car)) {
                throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Car is already reserved");
            }
        }


        if (reservationRequest.getRentalDate().isBefore(LocalDateTime.now().toLocalDate())) {
            throw new IllegalArgumentException("Tried to reserve a date in the past");
        }

        Reservation reservation = Reservation.builder()
            .rentalDate(reservationRequest.getRentalDate())
            .member(member)
            .car(car)
            .build();

        return new ReservationResponse(reservationRepository.save(reservation));

    }


}
