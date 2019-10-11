package com.good.dao;

import com.good.model.BoardVO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BoardDAO {
    public List<BoardVO> getBoardList() throws Exception;
    public BoardVO getBoardContent(int bid) throws Exception;
    public int insertBoard(BoardVO boardVO) throws Exception;
    public int updateBoard(BoardVO boardVO) throws Exception;
    public int deleteBoard(int bid) throws Exception;
    public int updateViewCnt(int bid) throws Exception;
}
