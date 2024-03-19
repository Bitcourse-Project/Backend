package com.finance.bitcourse.Dto;

import com.finance.bitcourse.Entity.Member;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    private Long id;
    private String username;
    private String email;

    static public MemberDto toDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail()).build();
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .email(email).build();
    }
}