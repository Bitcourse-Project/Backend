package com.finance.bitcourse.Jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {  // 클라이언트에 토큰을 보내기 위해
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
