package dat3.car.service;


import dat3.car.DTO.member.MemberRequest;
import dat3.car.DTO.member.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(m -> new MemberResponse(m,includeAll)).toList();
    }

    public MemberResponse addMember(MemberRequest memberRequest){
        if(memberRepository.existsById(memberRequest.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this username already exist");
        }
        if(memberRepository.existsByEmail(memberRequest.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this Email already exist");
        }

        //Later you should add error checks --> Missing arguments, email taken etc.
        Member newMember = MemberRequest.getMemberEntity(memberRequest);
        newMember = memberRepository.save(newMember);

        return new MemberResponse(newMember, false);
    }

    public MemberResponse findMemberByUsername(String username) {
        Member m = memberRepository.findById(username).orElseThrow( () -> new EntityNotFoundException("Username doesn't exist"));
        return new MemberResponse(m,true);
    }

    public void updateMember(MemberRequest body, String username) {
        Member member = memberRepository.findById(username).orElseThrow( () ->
            new EntityNotFoundException("Member with given ID does not exist"));

        Optional.ofNullable(body.getPassword()).ifPresent(member::setPassword);
        Optional.ofNullable(body.getEmail()).ifPresent(member::setEmail);

        Optional.ofNullable(body.getFirstName()).ifPresent(member::setFirstName);
        Optional.ofNullable(body.getLastName()).ifPresent(member::setFirstName);

        Optional.ofNullable(body.getCity()).ifPresent(member::setFirstName);
        Optional.ofNullable(body.getStreet()).ifPresent(member::setFirstName);
        Optional.ofNullable(body.getZip()).ifPresent(member::setFirstName);

        memberRepository.save(member);
    }

    public void updateMemberRanking(String username, int value) {
        Member member = findMemberById(username);
        member.setRanking(value);
        memberRepository.save(member);
    }



    private Member findMemberById(String username) {
        return memberRepository.findById(username).
            orElseThrow( () -> new EntityNotFoundException("Member not found"));
    }

    public void deleteMember(String username) {
        memberRepository.deleteById(username);
    }

    public MemberResponse getMemberById(String username) {
        Member member = findMemberById(username);
        return new MemberResponse(member, true);
    }


}
