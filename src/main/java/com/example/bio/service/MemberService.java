package com.example.bio.service;

import com.example.bio.domain.Member;
import com.example.bio.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

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

    public Member findById(Long memberId) throws NotFoundException {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundException::new);
        return member;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    private void validateDuplicateMember(Member member) throws RuntimeException {
        if (!memberRepository.findById(member.getId()).isEmpty())
            throw new RuntimeException("이미 존재하는 회원"); // TODO Exception 구현
    }
}
