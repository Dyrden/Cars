package dat3.car.api;


import dat3.car.DTO.member.MemberRequest;
import dat3.car.DTO.member.MemberResponse;

import dat3.car.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {

    MemberService memberService;


    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //Security -> ADMIN ONLY
    @GetMapping
    List<MemberResponse> getMembers() {
        return memberService.getMembers(false);
    }

    //Security -> ADMIN ONLY + ???
    @GetMapping(path = "/{username}")
    MemberResponse getMemberById(@PathVariable String username) {
        return memberService.getMemberById(username);
    }

    //anonymous
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MemberResponse addMember(@RequestBody MemberRequest body) {
        return memberService.addMember(body);
    }

    //USER
    @PutMapping("/{username}")
    ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username) {
        memberService.updateMember(body,username);
        return ResponseEntity.ok(true);
    }

    //ADMIN
    @PatchMapping("/ranking/{username}/{value}")
    void setRankingForUser(@PathVariable String username, @PathVariable int value) {
        memberService.updateMemberRanking(username,value);
    }

    //ADMIN
    @DeleteMapping("/{username}")
    void deleteMemberByUsername(@PathVariable String username) {
        memberService.deleteMember(username);
    }


}







