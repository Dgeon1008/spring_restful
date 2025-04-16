package com.app.restful.mapper;

import com.app.restful.domain.MemberVO;
import com.app.restful.domain.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PostMapperTests {

    @Autowired
    private PostMapper postMapper;

    @Test
    public void selectAll() {
        log.info("{}", postMapper.selectAll());
    }

    @Test
    public void selectById() {
        log.info("{}", postMapper.selectById(1L));
    }

    @Test
    public void insertTest() {
        PostVO postVO = new PostVO();
        postVO.setPostTitle("글 제목2");
        postVO.setPostContent("글 내용2");
        postVO.setMemberId(4L);
        postMapper.insert(postVO);
    }

    @Test
    public void updateTest() {
        PostVO postVO = new PostVO();
        postVO.setId(1L);
        postVO.setPostTitle("수정 제목1");
        postVO.setPostContent("수정 내용1");
        postMapper.update(postVO);
    }

    @Test
    public void deleteTest() {
        postMapper.delete(52L);
    }

    @Test
    public void deleteAllTest() {
        postMapper.deleteAll(2L);
    }




}
