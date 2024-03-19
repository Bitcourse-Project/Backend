package com.finance.bitcourse.Service;

import com.finance.bitcourse.Jwt.JwtToken;
import com.finance.bitcourse.Dto.MemberDto;
import com.finance.bitcourse.Dto.SignUpDto;

public interface MemberService {

    JwtToken login(String username, String password);

    MemberDto signUp(SignUpDto signUpDto);

}
