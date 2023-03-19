package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class DeveloperData implements ApplicationRunner {

    final CarRepository carRepository;
    final MemberRepository memberRepository;
    final ReservationRepository reservationRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository, UserWithRolesRepository userWithRolesRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
        this.userWithRolesRepository = userWithRolesRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member member1 = new Member(
            "Froscoldt",
            "127",
            "mail@example.com",
            "Mark",
            "Denner",
            "streetname",
            "city",
            "zip");
        member1.setFavoriteCarColours(Arrays.asList("Red", "Black"));
        member1.setPhones(Map.of("arbejde", "12345678", "hjem", "87654321"));

        Member member2 = new Member(
            "KristianWede",
            "987",
            "endnuenemail@example.com",
            "Kristian",
            "Wede",
            "streetname2",
            "city A",
            "500");
        member2.setFavoriteCarColours(Arrays.asList("Blue", "Yellow", "Black"));
        member2.setPhones(Map.of("arbejde", "67485763", "hjem", "58386715"));


        List<Car> cars = new ArrayList<>();

        Car car1 = Car.builder().model("Skoda").brand("A27-B").pricePrDay(299.95).build();
        Car car2 = Car.builder().model("Toyota").brand("99X").pricePrDay(499.95).build();
        cars.add(Car.builder().model("Camry").brand("Toyota").pricePrDay(599.99).build());
        cars.add(Car.builder().model("Corolla").brand("Toyota").pricePrDay(499.95).build());
        cars.add(Car.builder().model("Accord").brand("Honda").pricePrDay(699.50).build());
        cars.add(Car.builder().model("Civic").brand("Honda").pricePrDay(549.99).build());
        cars.add(Car.builder().model("Altima").brand("Nissan").pricePrDay(649.99).build());
        cars.add(Car.builder().model("Sentra").brand("Nissan").pricePrDay(499.99).build());
        cars.add(Car.builder().model("Fusion").brand("Ford").pricePrDay(599.95).build());
        cars.add(Car.builder().model("Focus").brand("Ford").pricePrDay(499.99).build());
        cars.add(Car.builder().model("Sonata").brand("Hyundai").pricePrDay(599.99).build());
        cars.add(Car.builder().model("Elantra").brand("Hyundai").pricePrDay(499.95).build());
        cars.add(Car.builder().model("Impreza").brand("Subaru").pricePrDay(699.50).build());
        cars.add(Car.builder().model("Legacy").brand("Subaru").pricePrDay(599.99).build());
        cars.add(Car.builder().model("3-Series").brand("BMW").pricePrDay(899.99).build());
        cars.add(Car.builder().model("5-Series").brand("BMW").pricePrDay(999.95).build());
        cars.add(Car.builder().model("C-Class").brand("Mercedes-Benz").pricePrDay(899.99).build());
        cars.add(Car.builder().model("Toyota").brand("99X").pricePrDay(499.95).build());





        carRepository.save(car1);
        carRepository.save(car2);

        carRepository.saveAll(cars);

        memberRepository.save(member1);
        memberRepository.save(member2);


        Reservation reservation = Reservation.builder().reservationDate(LocalDateTime.now()).rentalDate(LocalDate.now()).build();
        member1.addReservation(reservation);
        car1.addReservation(reservation);
        reservationRepository.save(reservation);


        setupUserWithRoleUsers();







    }

    UserWithRolesRepository userWithRolesRepository;
    final String passwordUsedByAll = "test12";

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {

        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }



}
