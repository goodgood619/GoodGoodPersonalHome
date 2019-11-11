package com.good.controller;

import com.good.model.ReReplyVO;
import com.good.model.ReplyVO;
import com.good.model.UserVO;
import com.good.service.BoardService;
import org.apache.maven.model.Model;
import org.springframework.security.access.method.P;
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
    public List<ReplyVO> getReplyList(HttpServletRequest request, @RequestParam("bid") int bid) throws Exception {
        // 1이면 해당 아이디인 사람이 쓴것, 0이면 해당 아이디인 사람이 쓴것이 아님
        List<ReplyVO> replyVOList = boardService.getReplyList(bid);
        HttpSession httpSession = request.getSession();
        UserVO userVO = (UserVO) httpSession.getAttribute("member");
        for (int i = 0 ; i < replyVOList.size(); i++) {
            if(replyVOList.get(i).getId() == null) continue;
            List<ReReplyVO> reReplyVOList = boardService.getReplyReplyList(replyVOList.get(i).getRid());
            if(reReplyVOList != null) {
                for (int j = 0; j < reReplyVOList.size(); j++) {
                    if (!reReplyVOList.get(j).getId().equals(userVO.getId())) {
                        reReplyVOList.get(j).setR_readonlyorwrite(0);
                    }
                }
                replyVOList.get(i).setReReplyVOList(reReplyVOList);
                if (replyVOList.get(i).getId().equals(userVO.getId())) continue;
                else {
                    replyVOList.get(i).setReadonlyorwrite(0);
                }
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


    @RequestMapping(value = "/saveReReply",method = RequestMethod.POST)
    public Map<String,Object> saveReReply(HttpSession httpSession, @RequestBody ReReplyVO reReplyVO) throws Exception {
        Map<String,Object> result = new HashMap<>();
        UserVO userVO = (UserVO) httpSession.getAttribute("member");
        reReplyVO.setId(userVO.getId());
        reReplyVO.setR_readonlyorwrite(1);
        try {
            boardService.saveReReply(reReplyVO);
            result.put("status","대댓글 저장 성공");
        }
        catch (Exception e) {
           e.printStackTrace();
           result.put("status","대댓글 저장 실패");
        }
        return result;
    }
    @RequestMapping(value= "/updateReply",method = RequestMethod.POST)
    public Map<String,Object> updateReply(@RequestBody ReplyVO replyVO) throws Exception{
        Map<String,Object> result = new HashMap<>();
        try {
            boardService.updateReply(replyVO);
            result.put("status","업데이트 성공");
        }
        catch (Exception e){
            e.printStackTrace();
            result.put("status","업데이트 실패");
        }
        return result;
    }

    @RequestMapping(value = "/updateReReply",method = RequestMethod.POST)
    public Map<String,Object> updateReReply(@RequestBody ReReplyVO reReplyVO) throws Exception {
        Map<String,Object> result = new HashMap<>();
        try {
            boardService.updateReReply(reReplyVO);
            result.put("status","대댓글 업데이트 성공");
        }
        catch (Exception e) {
            e.printStackTrace();
            result.put("status","대댓글 업데이트 실패");
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
    @RequestMapping(value = "/deleteReReply",method = RequestMethod.POST)
    public Map<String,Object> deleteReReply(@RequestParam("rrid") int rrid) throws Exception {
        Map<String,Object> result = new HashMap<>();
        try{
            boardService.deleteReReply(rrid);
            result.put("status","대댓글 삭제 성공");
        }
        catch (Exception e) {
            e.printStackTrace();
            result.put("status","대댓글 삭제 실패");
        }
        return result;
    }
}
