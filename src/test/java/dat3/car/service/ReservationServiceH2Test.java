package dat3.car.service;

import dat3.car.dto.reservation.ReservationRequest;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class ReservationServiceH2Test {


    @Autowired
    public CarRepository carRepository;
    @Autowired
    public MemberRepository memberRepository;
    @Autowired
    public ReservationRepository reservationRepository;


    private int car1_id;
    private LocalDate now = LocalDate.now();

    ReservationService reservationService;

    boolean dataIsReady = false;
    @BeforeEach
    void setUp() {
        if(!dataIsReady){
            Car car = Car.builder().brand("Toyota").model("X-95").pricePrDay(295.99).build();
            Member member = new Member("m2", "test12", "m2@a.dk", "aa", "hansen", "xx vej 34", "Lyngby", "2800");
            Reservation reservation = Reservation.builder().rentalDate(now).build();


            car1_id = carRepository.save(car).getId();
            memberRepository.save(member);

            car.addReservation(reservation);
            member.addReservation(reservation);

            reservationRepository.save(reservation);
            dataIsReady = true;
            reservationService = new ReservationService(reservationRepository,memberRepository,carRepository);

        }
    }




    @Test
    void addReservation_dateAlreadyReserved() {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setCar_id(car1_id);
        reservationRequest.setUsername("m2");
        reservationRequest.setRentalDate(now);


        assertThrows(ResponseStatusException.class, () -> reservationService.makeReservation(reservationRequest));



    }
}