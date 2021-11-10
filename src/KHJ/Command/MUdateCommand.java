package KHJ.Command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KHJ.Dao.MemberDao;
import KHJ.dto.MemberDto;

public class MUdateCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//1. 폼에서 입력된 데이터를 받아서 MemberDto로 만들기 
		MemberDto dto=new MemberDto();
		dto.setId(request.getParameter("id"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setJoinDate(Date.valueOf(request.getParameter("joinDate")));
		//2. MemberDao 객체 생성
		MemberDao dao = new MemberDao();
		//3. MemberDao의 객체의 해당 되는 메소드를 실행
		dao.update(dto);
	}

}
