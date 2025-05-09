package com.app.restful.service;

import com.app.restful.domain.MemberVO;
import com.app.restful.repository.MemberDAO;
import com.app.restful.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    private final PostDAO postDAO;

    @Override
    public Optional<MemberVO> getMemberInfo(Long id) {
        return memberDAO.findById(id);
    }

    public void join(MemberVO memberVO) {
        memberDAO.save(memberVO);
    }

    public void modify(MemberVO memberVO) {
        memberDAO.modify(memberVO);
    }

    public void remove(Long id) {
        postDAO.deleteAll(id);
        memberDAO.remove(id);
    }

}
