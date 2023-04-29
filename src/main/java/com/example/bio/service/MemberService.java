package com.example.bio.service;

import com.example.bio.domain.Member;
import com.example.bio.exception.AlreadyMember;
import com.example.bio.exception.NotFoundMember;
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

    private void validateDuplicateMember(Member member) throws RuntimeException {
        if (!memberRepository.findByName(member.getName()).isEmpty())
            throw new AlreadyMember(); // TODO Exception 구현
    }

    public Member findById(Long memberId) throws NotFoundException {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMember::new);
        return member;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }


}
