package com.finance.bitcourse.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginDto {
    private String username;
    private String password;
}