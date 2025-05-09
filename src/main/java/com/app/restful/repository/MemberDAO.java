package com.app.restful.repository;

import com.app.restful.domain.MemberVO;
import com.app.restful.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    private final MemberMapper memberMapper;

//    회원정보 조회
    public Optional<MemberVO> findById(Long id){
        return memberMapper.select(id);
    }

//    회원 가입
    public void save(MemberVO memberVO){
        memberMapper.insert(memberVO);
    }

//    회원 정보 수정
    public void modify(MemberVO memberVO){
        memberMapper.update(memberVO);
    }

//    회원 탈퇴
    public void remove(Long id){
        memberMapper.delete(id);
    }

}
