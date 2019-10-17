package com.good.dao;

import com.good.model.BoardSearch;
import com.good.model.BoardVO;
import com.good.model.Pagination;
import com.good.model.ReplyVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;


public interface BoardDAO {
    public List<BoardVO> getBoardList(BoardSearch boardSearch) throws Exception;
    public BoardVO getBoardContent(int bid) throws Exception;
    public int insertBoard(BoardVO boardVO) throws Exception;
    public int updateBoard(BoardVO boardVO) throws Exception;
    public int deleteBoard(int bid) throws Exception;
    public int updateViewCnt(int bid) throws Exception;
    public int getBoardListCnt(BoardSearch boardSearch) throws Exception;
    public List<ReplyVO> getReplyList(int bid) throws Exception;
    public int saveReply(ReplyVO replyVO) throws Exception;
    public int updateReply(ReplyVO replyVO) throws Exception;
    public int deleteReply(int rid) throws Exception;
}
