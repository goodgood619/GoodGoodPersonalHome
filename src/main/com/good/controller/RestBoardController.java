package com.good.controller;

import com.good.model.ReplyVO;
import com.good.service.BoardService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restBoard")
public class RestBoardController {
    private BoardService boardService;

    @Inject
    public RestBoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(value="/getReplyList",method = RequestMethod.POST)
    public List<ReplyVO> getReplyList(@RequestParam("bid") int bid) throws Exception{
        return boardService.getReplyList(bid);
    }

    @RequestMapping(value="/saveReply",method = RequestMethod.POST)
    public Map<String,Object> saveReply(@RequestBody ReplyVO replyVO) throws Exception {
        Map<String,Object> result = new HashMap<>();
        try{
            boardService.saveReply(replyVO);
            result.put("status","저장 성공");
        }
        catch (Exception e) {
            e.printStackTrace();
            result.put("status","저장 실패");
        }
        return result;
    }

    @RequestMapping(value= "/updateReply",method = RequestMethod.POST)
    public Map<String,Object> updateReply(@RequestBody ReplyVO replyVO) throws Exception{
        Map<String,Object> result = new HashMap<>();
        try{
            boardService.updateReply(replyVO);
            result.put("status","업데이트 성공");
        }
        catch (Exception e){
            e.printStackTrace();
            result.put("status","업데이트 실패");
        }
        return result;
    }

    @RequestMapping(value = "/deleteReply",method = RequestMethod.POST)
    public Map<String,Object> deleteReply(@RequestParam("rid") int rid) throws Exception{
        Map<String,Object> result = new HashMap<>();
        try {
            boardService.deleteReply(rid);
            result.put("status","삭제 성공");
        }
        catch (Exception e) {
            e.printStackTrace();
            result.put("status","삭제 실패");
        }
        return result;
    }
}
