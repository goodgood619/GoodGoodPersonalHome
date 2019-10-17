package com.good.service;

import com.good.controller.NotFoundException;
import com.good.dao.BoardDAO;
import com.good.model.BoardSearch;
import com.good.model.BoardVO;
import com.good.model.Pagination;
import com.good.model.ReplyVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    private final BoardDAO boardDAO;

    @Inject
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public List<BoardVO> getBoardList(BoardSearch boardSearch) throws Exception {
        return boardDAO.getBoardList(boardSearch);
    }

    @Override
    public void insertBoard(BoardVO boardVO) throws Exception {
        boardDAO.insertBoard(boardVO);
    }

    @Transactional
    @Override
    public BoardVO updategetBoardContent(int bid) throws Exception {
        boardDAO.updateViewCnt(bid);
        return boardDAO.getBoardContent(bid);
    }

    @Override
    public void updateBoard(BoardVO boardVO) throws Exception {
        boardDAO.updateBoard(boardVO);
    }

    @Override
    public BoardVO justgetBoardContent(int bid) throws Exception {
        return boardDAO.getBoardContent(bid);
    }

    @Override
    public void deleteBoard(int bid) throws Exception {
        boardDAO.deleteBoard(bid);
    }

    @Override
    public int getBoardListCnt(BoardSearch boardSearch) throws Exception {
        return boardDAO.getBoardListCnt(boardSearch);
    }

    @Override
    public List<ReplyVO> getReplyList(int bid) throws Exception {
        return boardDAO.getReplyList(bid);
    }

    @Override
    public int saveReply(ReplyVO replyVO) throws Exception {
        return boardDAO.saveReply(replyVO);
    }

    @Override
    public int updateReply(ReplyVO replyVO) throws Exception {
        return boardDAO.updateReply(replyVO);
    }

    @Override
    public int deleteReply(int rid) throws Exception {
        return boardDAO.deleteReply(rid);
    }

}
