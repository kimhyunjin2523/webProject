package KHJ.Command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KHJ.Dao.MemberDao;
import KHJ.dto.MemberDto;

public class MViewCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		//id 값을 db의 select*from member where id=?
		//1.MemberDao 객체 생성 
		MemberDao dao= new MemberDao();
		//2.View 내용에 들어갈 레코드를 검색하는 메소드 호출
		try {
			MemberDto dto =dao.view(id);
			request.setAttribute("dto", dto);
		}catch (Exception e) {
			// TODO: handle exception
		}
		//3.
	}

}
