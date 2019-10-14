package com.good.service;

import com.good.model.BoardVO;

import java.util.List;
import java.util.Map;

public interface BoardService {
    public List<BoardVO> getBoardList() throws Exception;
    public void insertBoard(BoardVO boardVO) throws Exception;
    public BoardVO updategetBoardContent(int bid) throws Exception;
    public void updateBoard(BoardVO boardVO) throws Exception;
    public BoardVO justgetBoardContent(int bid) throws Exception;
    public void deleteBoard(int bid) throws Exception;
}
