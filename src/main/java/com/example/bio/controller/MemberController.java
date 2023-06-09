package com.example.bio.controller;

import com.example.bio.domain.dto.MemberRequest;
import com.example.bio.domain.dto.MemberResponse;
import com.example.bio.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static com.example.bio.domain.dto.MemberRequest.toEntity;
import static com.example.bio.domain.dto.MemberResponse.toDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<Long> postMember(@RequestBody @Valid MemberRequest memberRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.join(toEntity(memberRequest)));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long memberId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toDto(memberService.findById(memberId)));
    }

    @GetMapping()
    public ResponseEntity<List<MemberResponse>> getMembers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberService.findAll().stream()
                        .map(MemberResponse::toDto)
                        .collect(Collectors.toList()));
    }
}
