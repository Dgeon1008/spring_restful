package com.app.restful.mapper;

import com.app.restful.domain.MemberVO;
import com.app.restful.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTests {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void selectTest(){
        log.info("{}", memberService.getMemberInfo(1L));
    }

    @Test
    public void insertTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test3@test3.com");
        memberVO.setMemberPassword("9876");
        memberVO.setMemberName("test3");

        memberMapper.insert(memberVO);
    }

    @Test
    public void updateTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setId(3L);
        memberVO.setMemberEmail("test4@test4.com");
        memberVO.setMemberPassword("0000");
        memberVO.setMemberName("김상어");
        memberMapper.update(memberVO);
    }

    @Test
    public void deleteTest(){
        memberMapper.delete(4L);
    }




}
