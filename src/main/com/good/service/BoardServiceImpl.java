package com.good.service;

import com.good.dao.BoardDAO;
import com.good.model.BoardVO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardDAO boardDAO;

    @Inject
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public List<BoardVO> getBoardList() throws Exception {
        return boardDAO.getBoardList();
    }

    @Override
    public void insertBoard(BoardVO boardVO) throws Exception {
        boardDAO.insertBoard(boardVO);
    }

}
