package com.good.service;

import com.good.controller.NotFoundException;
import com.good.dao.BoardDAO;
import com.good.model.*;
import com.good.utils.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    private final BoardDAO boardDAO;
    @Autowired
    private String uploadPath;

    @Inject
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public List<BoardVO> getBoardList(BoardSearch boardSearch) throws Exception {
        return boardDAO.getBoardList(boardSearch);
    }

    @Override
    public void insertBoard(BoardVO boardVO, MultipartFile multipartFile) throws Exception {
        String imgUploadPath = uploadPath + File.separator + "imageUpload";
        String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
        String fileName = null;
        if (multipartFile.getOriginalFilename()!=null && !multipartFile.getOriginalFilename().equals("")) {
            fileName = UploadFileUtils.fileUpload(imgUploadPath,multipartFile.getOriginalFilename(), multipartFile.getBytes(),ymdPath);
        }
        else {
            fileName = uploadPath + File.separator + "imageUpload" + File.separator + "none.png";
        }
        boardVO.setBoard_img("imageUpload"+ymdPath+File.separator+ fileName);
        boardVO.setBoardthumb_img("imageUpload" + ymdPath + File.separator + "s"+File.separator+"s_"+ fileName);
        boardVO.setContent(boardVO.eraseStringContent(boardVO.getContent()));
        boardVO.setCate_cd("1");
        boardDAO.insertBoard(boardVO);
    }

    @Transactional
    @Override
    public BoardVO updategetBoardContent(int bid) throws Exception {
        boardDAO.updateViewCnt(bid);
        return boardDAO.getBoardContent(bid);
    }

    @Override
    public void updateBoard(BoardVO boardVO, MultipartFile multipartFile, HttpServletRequest req) throws Exception {
        if(multipartFile.getOriginalFilename() != null && !multipartFile.getOriginalFilename().equals("")){
            new File(uploadPath+req.getParameter("board_img")).delete();
            new File(uploadPath + req.getParameter("boardthumb_img")).delete();

            String imgUploadPath = uploadPath + File.separator + "imageUpload";
            String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
            String fileName = UploadFileUtils.fileUpload(imgUploadPath,multipartFile.getOriginalFilename(), multipartFile.getBytes(),ymdPath);

            boardVO.setBoard_img("imageUpload"+ymdPath+File.separator+ fileName);
            boardVO.setBoardthumb_img("imageUpload" + ymdPath + File.separator + "s"+File.separator+"s_"+ fileName);
        }
        else { //새로운 파일 등록되지 않았음
            boardVO.setBoard_img(req.getParameter("board_img"));
            boardVO.setBoardthumb_img(req.getParameter("boardthumb_img"));
        }
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

    @Override
    public List<ReReplyVO> getReplyReplyList(int rid) {
        return boardDAO.getReplyReplyList(rid);
    }

}
