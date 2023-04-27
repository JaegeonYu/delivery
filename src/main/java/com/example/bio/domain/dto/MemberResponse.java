package com.example.bio.domain.dto;

import com.example.bio.domain.Address;
import com.example.bio.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberResponse {
    private Long id;
    private String name;

    private Address address;

    @Builder
    public MemberResponse(Long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public static MemberResponse toDto(Member member){
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .address(member.getAddress())
                .build();
    }
}
