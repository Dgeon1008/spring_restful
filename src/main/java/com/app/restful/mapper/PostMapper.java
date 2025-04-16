package com.app.restful.mapper;

import com.app.restful.domain.PostDTO;
import com.app.restful.domain.PostVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

//    게시물 전체 조회
    public List<PostDTO> selectAll();

//    게시물 단일 조회
    public Optional<PostDTO> selectById(Long id);

//    게시물 작성
    public void insert(PostVO postVO);

//    게시물 수정
    public void update(PostVO postVO);

//    게시물 삭제
    public void delete(Long id);

//    게시물 전체 삭제
    public void deleteAll(Long memberId);


}
