package com.good.service;

import com.good.model.BoardSearch;
import com.good.model.BoardVO;
import com.good.model.Pagination;
import com.good.model.ReplyVO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Transactional
public interface BoardService {
    List<BoardVO> getBoardList(BoardSearch boardSearch) throws Exception;
    void insertBoard(BoardVO boardVO, MultipartFile multipartFile) throws Exception;
    @Transactional
    BoardVO updategetBoardContent(int bid) throws Exception;
    void updateBoard(BoardVO boardVO, MultipartFile multipartFile, HttpServletRequest httpServletRequest) throws Exception;
    BoardVO justgetBoardContent(int bid) throws Exception;
    void deleteBoard(int bid) throws Exception;
    int getBoardListCnt(BoardSearch boardSearch) throws Exception;
    List<ReplyVO> getReplyList(int bid) throws Exception;
    int saveReply(ReplyVO replyVO) throws Exception;
    int updateReply(ReplyVO replyVO) throws Exception;
    int deleteReply(int rid) throws Exception;
}
