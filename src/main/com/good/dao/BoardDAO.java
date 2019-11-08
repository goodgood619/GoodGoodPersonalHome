package com.good.dao;

import com.good.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;


public interface BoardDAO {
    List<BoardVO> getBoardList(BoardSearch boardSearch) throws Exception;
    BoardVO getBoardContent(int bid) throws Exception;
    int insertBoard(BoardVO boardVO) throws Exception;
    int updateBoard(BoardVO boardVO) throws Exception;
    int deleteBoard(int bid) throws Exception;
    int updateViewCnt(int bid) throws Exception;
    int getBoardListCnt(BoardSearch boardSearch) throws Exception;
    List<ReplyVO> getReplyList(int bid) throws Exception;
    int saveReply(ReplyVO replyVO) throws Exception;
    int updateReply(ReplyVO replyVO) throws Exception;
    int deleteReply(int rid) throws Exception;
    List<ReReplyVO> getReplyReplyList(int rid);
    void saveReReply(ReReplyVO reReplyVO);
    void updateReReply(ReReplyVO reReplyVO);
    void deleteReReply(int rrid);
}
