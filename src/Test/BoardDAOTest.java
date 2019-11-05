import com.good.dao.BoardDAO;
import com.good.model.BoardVO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml","classpath:spring/appServlet/dispatcher-servlet.xml"})
@WebAppConfiguration
public class BoardDAOTest {

    @Inject
    private BoardDAO boardDAO;


    @Test @Ignore
    public void testGetBoardList() throws Exception {
       // List<BoardVO> boardList = boardDAO.getBoardList();
       // System.out.println("\n Board List \n");
       // if (boardList.size() > 0) {
         //  for (BoardVO list : boardList) {
          //      System.out.println(list.getTitle());
         //   }
       // } else {
        //    System.out.println("데이터가 없습니다");
       // }

    }

    @Test @Ignore
    public void testGetBoardContent() throws Exception {
        BoardVO boardVO = boardDAO.getBoardContent(1);
        System.out.println("\n Board List \n");
        if (boardVO != null) {
            System.out.println("글번호: "+boardVO.getBid());
            System.out.println("글제목: "+boardVO.getTitle());
            System.out.println("글내용 : "+boardVO.getContent());
            System.out.println("글태그 : "+boardVO.getTag());
            System.out.println("조회수 : "+boardVO.getView_cnt());
            System.out.println("작성자 : "+boardVO.getReg_id());
            System.out.println("작성일 : "+boardVO.getReg_gt());
            System.out.println("수정일 : "+boardVO.getEdit_gt());
        } else {
            System.out.println("데이터가 없습니다");
        }
    }

    @Test @Ignore
    public void testInsertBoard() throws Exception {
        BoardVO boardVO = new BoardVO();
       // boardVO.setBid(1);
        boardVO.setCate_cd("1");
      //  boardVO.setTitle("첫번째 게시물 입니다.");
      //  boardVO.setContent("첫번째 게시물입니다.");
        boardVO.setTag("1");
        boardVO.setReg_id("1");
        for(int i = 1 ; i<1234; i++) {
            boardVO.setBid(i);
            boardVO.setTitle(i+"번째 게시물입니다.");
            boardVO.setContent(i+"번째 게시물 내용입니다.");
            int result = boardDAO.insertBoard(boardVO);
            System.out.println("\n Insert Board List \n");
            if (result == 1) {
                System.out.println("\n 게시물 등록성공 ");
            } else {
                System.out.println("\n 게시물 등록실패 ");
            }
        }
    }

    @Test @Ignore
    public void testUpdateBoard() throws Exception {
        BoardVO boardVO = new BoardVO();
        boardVO.setBid(1);
        boardVO.setCate_cd("1");
        boardVO.setTitle("첫번째 게시물 입니다-수정 합니다.");
        boardVO.setContent("첫번째 게시물입니다-수정합니다.");
        boardVO.setTag("1-1");
        int result = boardDAO.updateBoard(boardVO);
        System.out.println("\n Update Board List \n");
        if (result > 0) {
           System.out.println("\n 게시물 수정 성공");
        } else {
            System.out.println("\n 게시물 수정 실패");
        }
    }

    @Test @Ignore
    public void testDeleteBoard() throws Exception {
        int result = boardDAO.deleteBoard(1);
        System.out.println("\n Delete Board List \n");
        if (result > 0) {
            System.out.println("\n 게시물 삭제 성공 ");
        } else {
            System.out.println("\n 게시물 삭제 실패");
        }
    }



    @Test @Ignore
    public void testUpdateViewCnt() throws Exception {
        int result = boardDAO.updateViewCnt(0);
        System.out.println("\n Update View count result \n");
        if (result > 0) {
            System.out.println("\n 게시물 조회수 업데이트 성공 ");
        } else {
            System.out.println("\n 게시물 조회수 업데이트 실패");
        }
    }
}
