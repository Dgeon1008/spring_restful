package com.app.restful.controller;


import com.app.restful.domain.PostDTO;
import com.app.restful.domain.PostVO;
import com.app.restful.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts/api/*")
@RequiredArgsConstructor
@Slf4j
public class PostAPI {

    private final PostService postService;

//    게시글 전체 조회
    @Operation(summary = "게시글 전체 조회", description = "게시글 정보를 전체 조회 할 수 있는 API")
    @GetMapping("posts")
    public List<PostDTO> getPosts() {
        return postService.getPosts();
    }

//    게시글 단일 조회
//    옵셔널보다 꺼내오는 게 좋음
    @Operation(summary = "게시글 정보 조회", description = "게시글 1개 정보를 조회할 수 있는 API")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type = "number"), // 스키마 타입, 자바 타입X, swagger에서 정의되고 있는 타입
            in = ParameterIn.PATH, // 어디에서 받는지
            required = true
    )
    @GetMapping("post/{id}")
    public PostDTO getPost(@PathVariable Long id){
        log.info("{}", id);
        Optional<PostDTO> foundPost = postService.getPost(id);
        if(foundPost.isPresent()){
            return foundPost.get();
        }
        return new PostDTO();
    }

//    게시글 작성
    @Operation(summary = "게시글 작성", description = "게시글을 작성할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "게시글 작성 성공")
    @PostMapping("write")
//    API의 상태값을 알려준다.
//    fetch로 보냈을 때 body에서 받는다.
//    rest는 페이지를 안건드리기 때문에 redicrect를 못해서 데이터를 넘겨줘야된다.
    public PostDTO write(@RequestBody PostVO postVO){
//        id가 시퀀스라 현재 들어가있지 않다
        postService.write(postVO);
//        작성 후 게시물 가져오기
        Optional<PostDTO> foundPost = postService.getPost(postVO.getId());
        if(foundPost.isPresent()){
            return foundPost.get();
        }
        return new PostDTO();
    }

//    PutMapping : 모든 컬럼 수정 !웬만하면 put 사용
//    PatchMapping : 부분 컬럼 수정
//    게시글 수정(put)
    @Operation(summary = "게시글 수정", description = "게시글 정보를 수정할 수 있는 API")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type="number"),
            in = ParameterIn.PATH,
            required = true
    )
    @PutMapping("post/{id}")
    public PostDTO modify(@PathVariable Long id, @RequestBody PostVO postVO){
        postVO.setId(id);
//        modify할 때 id가 없으니깐 먼저 id를 넣어준다.
        postService.modify(postVO);
        Optional<PostDTO> foundPost = postService.getPost(id);
        if(foundPost.isPresent()){
            return foundPost.get();
        }
        return new PostDTO();
    }


//    게시글 삭제(DELETE)
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "게시글 삭제 성공")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type="number"),
            in = ParameterIn.PATH,
            required = true
    )
    @DeleteMapping("post/{id}")
    public void remove(@PathVariable Long id){
        postService.remove(id);
    }




}














