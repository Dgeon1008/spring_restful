package com.app.restful.controller;


import com.app.restful.domain.MemberVO;
import com.app.restful.service.MemberService;
import com.app.restful.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// JSON으로 리턴
@RestController
@RequestMapping("/members/api/*")
@RequiredArgsConstructor
public class MemberAPI {

    private final MemberService memberService;
    private final PostService postService;

//    회원 단일 조회
//    url 파라미터 {} : 모든 컨트롤러에서 사용이 가능하지만 보통 rest에서 사용된다.
    @Operation(summary = "회원 단일 조회", description = "회원 1명의 정보를 조회할 수 있습니다.")
    @ApiResponse(responseCode = "200", description = "회원 조회 성공")
    @GetMapping("member/{id}")
    public MemberVO getMember(@PathVariable Long id) {
        Optional<MemberVO> foundMember = memberService.getMemberInfo(id);
        if (foundMember.isPresent()) {
            return foundMember.get();
        }
//        null이면 프론트에서 요청하기전까지 nullpointException이 뜬다
//        null로 리턴하지 않고 빈 객체로 리턴한다.
//        대부분 Optional로 안보낼때가 많지만 상세하게 전달할 때에는 Optional로 전달한다.
        return new MemberVO();
    }

//    회원 가입
    @Operation(summary = "회원 가입", description = "회원 가입을 할 수 있습니다.")
    @ApiResponse(responseCode = "200", description = "회원 가입 성공")
    @PostMapping("join")
    public MemberVO join(@RequestBody MemberVO memberVO) {
        memberService.join(memberVO);
//        성공 or 실패
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "로그인 성공");
//        return response
        Optional<MemberVO> foundMember = memberService.getMemberInfo(memberVO.getId());
        if (foundMember.isPresent()) {
            return foundMember.get();
        }
        return new MemberVO();
    }

//    회원 수정
    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정 할 수 있습니다.")
    @ApiResponse(responseCode = "200", description = "회원정보 수정 성공")
    @Parameter(
            name = "id",
            description = "회원 번호",
            schema = @Schema(type="number"),
            in = ParameterIn.PATH,
            required = true
    )
//    @PutMapping("member/{id}")
    @PutMapping("modify")
    public void modify(@RequestBody MemberVO memberVO) {
        memberService.modify(memberVO);
//        로그인된 상태니깐 아이디를 찾지않는다.
//        Optional<MemberVO> foundMember = memberService.getMemberInfo(id);
//        if (foundMember.isPresent()) {
//            return foundMember.get();
//        }
//        return new MemberVO();
    }


//    회원 삭제
    @Operation(summary = "회원 탈퇴", description = "회원탈퇴를 할 수 있습니다.")
    @ApiResponse(responseCode = "200", description = "회원탈퇴 성공")
    @Parameter(
            name = "id",
            description = "회원 번호",
            schema = @Schema(type="number"),
            in = ParameterIn.PATH,
            required = true
    )
    @DeleteMapping("remove/{id}")
    public void remove(@PathVariable Long id) {
//        매개 변수를 원래 받지않는다.
//        세션에 저장된 회원의 id을 삭제
        memberService.remove(id);
    }


}
