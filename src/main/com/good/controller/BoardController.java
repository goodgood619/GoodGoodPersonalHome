package com.good.controller;

import com.good.model.BoardVO;
import com.good.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;

    @Inject
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/getBoardList")
    public String getBoardList(Model model) throws Exception{
        model.addAttribute("boardList", boardService.getBoardList());
        return "board/index";
    }

    @GetMapping("/boardForm")
    public String boardForm(){
        return "board/boardForm";
    }

    @RequestMapping(value="/saveBoard",method = RequestMethod.POST)
    public String saveBoard(@ModelAttribute("BoardVO") BoardVO boardVO, RedirectAttributes redirectAttributes) throws Exception{
        boardVO.setCate_cd("1");
        boardService.insertBoard(boardVO);
        return "redirect:/board/getBoardList";
    }
}
