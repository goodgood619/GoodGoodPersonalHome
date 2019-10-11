package com.good.dao;

import com.good.model.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;
import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO{

    @Inject
    private SqlSession sqlSession;

    @Override
    public List<BoardVO> getBoardList() throws Exception {
        return sqlSession.selectList("mapper.boardMapper.getBoardList");
    }

    @Override
    public BoardVO getBoardContent(int bid) throws Exception {
        return sqlSession.selectOne("mapper.boardMapper.getBoardContent",bid);
    }

    @Override
    public int insertBoard(BoardVO boardVO) throws Exception {
        return sqlSession.insert("mapper.boardMapper.insertBoard",boardVO);
    }

    @Override
    public int updateBoard(BoardVO boardVO) throws Exception {
        return sqlSession.update("mapper.boardMapper.updateBoard",boardVO);
    }

    @Override
    public int deleteBoard(int bid) throws Exception {
        return sqlSession.delete("mapper.boardMapper.deleteBoard",bid);
    }

    @Override
    public int updateViewCnt(int bid) throws Exception {
        return sqlSession.update("mapper.boardMapper.updateViewCnt",bid);
    }
}
