package com.good.dao;

import com.good.model.BoardSearch;
import com.good.model.BoardVO;
import com.good.model.Pagination;
import com.good.model.ReplyVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO{

    private SqlSession sqlSession;

    @Inject
    public BoardDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<BoardVO> getBoardList(BoardSearch boardSearch) throws Exception {
        return sqlSession.selectList("mapper.boardMapper.getBoardList",boardSearch);
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

    //총 게시글 갯수확인
    @Override
    public int getBoardListCnt(BoardSearch boardSearch) throws Exception {
        return sqlSession.selectOne("mapper.boardMapper.getBoardListCnt",boardSearch);
    }

    @Override
    public List<ReplyVO> getReplyList(int bid) throws Exception {
        return sqlSession.selectList("mapper.boardreplyMapper.getReplyList",bid);
    }

    @Override
    public int saveReply(ReplyVO replyVO) throws Exception {
        return sqlSession.insert("mapper.boardreplyMapper.saveReply",replyVO);
    }

    @Override
    public int updateReply(ReplyVO replyVO) throws Exception {
        return sqlSession.update("mapper.boardreplyMapper.updateReply",replyVO);
    }

    @Override
    public int deleteReply(int rid) throws Exception {
        return sqlSession.delete("mapper.boardreplyMapper.deleteReply",rid);
    }
}
