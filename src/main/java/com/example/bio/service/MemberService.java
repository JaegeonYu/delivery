package com.example.bio.service;

import com.example.bio.domain.Member;
import com.example.bio.exception.AlreadyMember;
import com.example.bio.exception.NotFoundMember;
import com.example.bio.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        if (!memberRepository.findByName(member.getName()).isEmpty())
            throw new AlreadyMember();
    }

    public Member findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMember::new);
        return member;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }


}
