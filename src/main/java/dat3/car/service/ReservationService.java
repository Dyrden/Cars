package dat3.car.service;


import dat3.car.DTO.member.MemberResponse;
import dat3.car.DTO.reservation.ReservationRequest;
import dat3.car.DTO.reservation.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    //ReservationDTO giving info?
    @Transactional
    public ReservationResponse addReservation(ReservationRequest reservationRequest) {

        boolean reservationFound = reservationRepository.findReservationByCar_IdAndRentalDate(reservationRequest.getCar_id(), reservationRequest.getRentalDate());

        if (!reservationFound) {


            Reservation reservation = Reservation.builder().rentalDate(reservationRequest.getRentalDate()).build();

            Car car = carRepository.findById(reservationRequest.getCar_id()).orElseThrow(() -> new EntityNotFoundException("Car not found"));
            car.addReservation(reservation);

            Member member = memberRepository.findById(reservationRequest.getUser_username()).orElseThrow(() -> new EntityNotFoundException("Member not found"));
            member.addReservation(reservation);

            reservationRepository.save(reservation);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already reserved for given date");
        }

        return new ReservationResponse();

    }


}
