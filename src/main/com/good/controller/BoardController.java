package com.good.controller;

import com.good.model.BoardSearch;
import com.good.model.BoardVO;
import com.good.model.ReplyVO;
import com.good.service.BoardService;
import com.good.utils.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

@Controller
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;

    @Inject
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    private String uploadPath;

    @GetMapping("/getBoardList")
    public String getBoardList(Model model,@RequestParam(required = false,defaultValue = "1") int page,@RequestParam(required = false,defaultValue = "1") int range,@RequestParam(required = false,defaultValue = "title") String searchType,@RequestParam(required = false) String keyword) throws Exception {
        BoardSearch boardSearch = new BoardSearch();
        boardSearch.setKeyword(keyword);
        boardSearch.setSearchType(searchType);
        //전체 게시글 갯수
        int listCnt = boardService.getBoardListCnt(boardSearch);
        // 객체 생성
        boardSearch.pageinfo(page,range,listCnt);
        model.addAttribute("pagination",boardSearch);
        model.addAttribute("boardList", boardService.getBoardList(boardSearch));
        return "board/index";
    }

    // ModelAttribute를 추가시킨 이유는, Jsp에서 spring form 태그를 썼고, ModelAttribute를 썼기때문에, 안쓰더라도 무조건 선언해야함
    @GetMapping("/boardForm")
    public String boardForm(@ModelAttribute("boardVO") BoardVO boardVO,Model model){
        return "board/boardForm";
    }

    @RequestMapping(value="/saveBoard",method = RequestMethod.POST)
    public String saveBoard(@ModelAttribute("boardVO") BoardVO boardVO, @RequestParam("mode") String mode, RedirectAttributes redirectAttributes, Model model, @RequestParam("file") MultipartFile multipartFile) throws Exception {
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
        boardService.insertBoard(boardVO);

        return "redirect:/board/getBoardList";
    }


    @RequestMapping(value = "/modifyBoard",method = RequestMethod.POST)
    public String modifyBoard(@RequestParam("file") MultipartFile multipartFile, @ModelAttribute("boardVO") BoardVO boardVO, @RequestParam("mode") String mode, HttpServletRequest req) throws Exception {
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

        if(mode.equals("edit")){
            int bid = boardVO.getBid();
            BoardVO boardVO1 = boardService.justgetBoardContent(bid);
            boardVO.setCate_cd(boardVO1.getCate_cd());
            boardVO.setView_cnt(boardVO1.getView_cnt());
            boardVO.setEdit_gt(boardVO1.getEdit_gt());
            boardVO.setReg_gt(boardVO1.getReg_gt());
            // set을 해준이유는 값이 null로 넘어오기때문임, 이해는 잘안감 ㅇㅇ 찾아봐야 할듯
            boardService.updateBoard(boardVO);
        }

        return "redirect:/board/getBoardList";
    }
    @RequestMapping(value = "/getBoardContent",method = RequestMethod.GET)
    public String getBoardContent(Model model,@RequestParam("bid") int bid) throws Exception {
        model.addAttribute("boardContent",boardService.updategetBoardContent(bid));
        model.addAttribute("replyVO",new ReplyVO());
        return "board/boardContent";
    }

    @RequestMapping(value = "/modifyCKEditor",method = RequestMethod.POST)
    public void modifyCKEditor(HttpServletRequest req, HttpServletResponse res,@RequestParam MultipartFile multipartFile) throws Exception {
        UUID uid = UUID.randomUUID();

        OutputStream out = null;
        PrintWriter printWriter = null;

        // res 인코딩
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");

        // 아직 진행중
        try {

        }
        catch (Exception e) {

        }
        return;
    }

    @RequestMapping(value = "/editForm", method = RequestMethod.GET)
    public String editForm(@RequestParam ("bid") int bid, @RequestParam("mode") String mode, Model model) throws Exception{
        BoardVO boardVO = boardService.justgetBoardContent(bid);
        boardVO.setContent(boardVO.eraseStringContent(boardVO.getContent()));
        model.addAttribute("boardContent",boardVO);
        model.addAttribute("mode",mode);
        model.addAttribute("boardVO",new BoardVO());
        return "board/editboardForm";
    }

    @RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
    public String deleteBoard(RedirectAttributes redirectAttributes,@RequestParam("bid") int bid) throws Exception{
        boardService.deleteBoard(bid);
        return "redirect:/board/getBoardList";
    }


}
