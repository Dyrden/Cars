package dat3.car.service;

import dat3.car.dto.member.MemberRequest;
import dat3.car.dto.member.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// transactional (rollback)
// includes what is necessary for JPA/Hibernate and only that
// uses in-memory database (H2)
@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    public MemberRepository memberRepository;

    MemberService memberService;

    boolean dataIsReady = false;
    @BeforeEach
    void setUp() {
        if(!dataIsReady){
            memberRepository.save(new Member("m1", "test12", "m1@a.dk",  "bb", "Olsen", "xx vej 34", "Lyngby", "2800"));
            memberRepository.save(new Member("m2", "test12", "m2@a.dk", "aa", "hansen", "xx vej 34", "Lyngby", "2800"));
            dataIsReady = true;
            memberService = new MemberService(memberRepository); //Real DB is mocked away with H2
        }
    }


    @Test
    void getMembers() {
        List<MemberResponse> members =  memberService.getMembers(false);

        assertEquals(2,members.size());
    }

    @Test
    void addMember() {
        MemberRequest memberRequest = new MemberRequest(new Member("johndoe123", "password123", "johndoe@example.com", "John", "Doe", "123 Main St", "New York", "10001"));
        memberService.addMember(memberRequest);


        Optional<Member> member =  memberRepository.findById(memberRequest.getUsername());

        assertTrue(member.isPresent());


    }


    @Test
    void getMembersAdmin() {
        List<MemberResponse> members = memberService.getMembers(true);
        assertEquals(2,members.size());
        assertNotNull(members.get(0).getRanking());
    }




}