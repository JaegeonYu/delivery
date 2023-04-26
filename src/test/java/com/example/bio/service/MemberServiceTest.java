package com.example.bio.service;

import com.example.bio.domain.Address;
import com.example.bio.domain.Member;
import com.example.bio.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {
    private final MemberService memberService;

    private final MemberRepository memberRepository;
    @Autowired
    public MemberServiceTest(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @Test
    @DisplayName("회원가입 ")
    public void joinTest(){
        Member member = Member.builder()
                .name("bebe")
                .address(Address.builder()
                        .city("대전")
                        .street("도로명")
                        .zipcode("33333")
                        .build()).build();

        Long savedId = memberService.join(member);

        Assertions.assertEquals(member, memberRepository.findById(savedId).get());
    }
}