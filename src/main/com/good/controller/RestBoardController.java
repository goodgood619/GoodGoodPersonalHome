package com.good.controller;

import com.good.model.ReplyVO;
import com.good.model.UserVO;
import com.good.service.BoardService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public List<ReplyVO> getReplyList(HttpServletRequest request,@RequestParam("bid") int bid) throws Exception {
        // 1이면 해당 아이디인 사람이 쓴것, 0이면 해당 아이디인 사람이 쓴것이 아님
        List<ReplyVO> replyVOList = boardService.getReplyList(bid);
        HttpSession httpSession = request.getSession();
        UserVO userVO = (UserVO) httpSession.getAttribute("member");
        for (int i = 0 ; i < replyVOList.size(); i++) {
            if(replyVOList.get(i).getId() == null) continue;
            if (replyVOList.get(i).getId().equals(userVO.getId())) continue;
            else {
                replyVOList.get(i).setReadonlyorwrite(0);
            }
        }

        return replyVOList;
    }

    @RequestMapping(value="/saveReply",method = RequestMethod.POST)
    public Map<String,Object> saveReply(HttpServletRequest request,@RequestBody ReplyVO replyVO) throws Exception {
        Map<String,Object> result = new HashMap<>();
        HttpSession httpSession = request.getSession();
        UserVO userVO = (UserVO) httpSession.getAttribute("member");
        replyVO.setId(userVO.getId());
        replyVO.setReadonlyorwrite(1);
        try {
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
