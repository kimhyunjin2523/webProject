package KHJ.Command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KHJ.Dao.MemberDao;
import KHJ.dto.MemberDto;

public class MListCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("MListCommand에 도달했어요.");
		//DB에 목록을 반환하는 메소드를 실행
		MemberDao dao = new MemberDao();
		
		ArrayList<MemberDto> dtos = dao.list();
		
		request.setAttribute("dtos", dtos);
		//반환 받은 목록을 requestScope 저장
	}

}
