package com.example.bio.domain.dto;

import com.example.bio.domain.Address;
import com.example.bio.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberRequest {

    @NotNull
    private String name;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String zipcode;

    public static Member toEntity(MemberRequest memberRequest){
        return Member.builder()
                .name(memberRequest.name)
                .address(Address.builder()
                        .city(memberRequest.city)
                        .street(memberRequest.street)
                        .zipcode(memberRequest.zipcode)
                        .build())
                .build();
    }
}
