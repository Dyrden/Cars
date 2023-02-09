package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class DeveloperData implements ApplicationRunner {

    final CarRepository carRepository;
    final MemberRepository memberRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Car> listOfCars = new ArrayList<>();
        listOfCars.add(new Car("Skoda", "A27-B", 299.95));
        listOfCars.add(new Car("Toyota", "99X", 499.95));
        carRepository.saveAll(listOfCars);

        List<Member> listOfMembers = new ArrayList<>();
            Member member1 = new Member(
            "Froscoldt",
            "127",
            "mail@example.com",
            "Mark",
            "Denner",
            "streetname",
            "city",
            "zip");
        Member member2 = new Member(
            "KristianWede",
            "987",
            "endnuenemail@example.com",
            "Kristian",
            "Wede",
            "streetname2",
            "city A",
            "500");

        member1.setFavoriteCarColours(Arrays.asList("Red", "Black"));
        member1.setPhones(Map.of("arbejde","12345678","hjem","87654321"));
        listOfMembers.add(member1);

        member2.setFavoriteCarColours(Arrays.asList("Blue", "Yellow", "Black"));
        member2.setPhones(Map.of("arbejde","67485763","hjem","58386715"));
        listOfMembers.add(member2);


        memberRepository.saveAll(listOfMembers);

    }


}
