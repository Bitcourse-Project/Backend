package com.finance.bitcourse.Controller;

import com.finance.bitcourse.*;
import com.finance.bitcourse.Dto.LoginDto;
import com.finance.bitcourse.Dto.MemberDto;
import com.finance.bitcourse.Dto.SignUpDto;
import com.finance.bitcourse.Jwt.JwtToken;
import com.finance.bitcourse.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public JwtToken login(@RequestBody LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        JwtToken jwtToken = memberService.login(username, password);
        System.out.println(jwtToken);
        log.info("request username = {}, password = {}", username, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }

    @PostMapping("/test")
    public String test() {
//        return "success";
        return SecurityUtil.getCurrentUsername();
    }

//    @PostMapping("/sign-up")
//    public ResponseEntity<MemberDto> signUp(@RequestBody SignUpDto signUpDto) {
//        MemberDto savedMemberDto = memberService.signUp(signUpDto);
//        return ResponseEntity.ok(savedMemberDto);
//    }
}