package KHJ.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KHJ.Command.MCommand;
import KHJ.Command.MDeleteCommand;
import KHJ.Command.MInsertCommand;
import KHJ.Command.MListCommand;
import KHJ.Command.MUdateCommand;
import KHJ.Command.MViewCommand;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String viewPage=null;
		MCommand command =null;
		String uri = request.getRequestURI();
		System.out.println("uri:"+uri);
		
		String com = uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf(".do"));
		System.out.println(com);
		//System.out.println("com:"+com);
		if(com != null && com.trim().equals("list")) {
		    command= new MListCommand();
			command.execute(request, response);
			viewPage="/WEB-INF/view/mList.jsp";
			//1.모델부분의 서비스를 실행
			//2.해당되는 뷰로 이동
		}else if(com != null&&com.trim().equals("insertForm")) {
			viewPage="/WEB-INF/view/mInsertForm.jsp";
		}else if(com != null&&com.trim().equals("insert")) {
			//1.MInsertCommand 생성
			 command= new MInsertCommand();
				
			//2.MInsertCommand의 execute 메소드를 실행
			 command.execute(request, response);
			
			viewPage="/WEB-INF/view/list.do";
		}else if(com != null&&com.trim().equals("view")) {
			//1.MViewCommand 생성
			command= new MViewCommand();
			//2.execute 메소드 실행
			command.execute(request, response);
			//3. 해당되는 뷰페이지를 설정
			viewPage="/WEB-INF/view/mView.jsp";
		}else if(com != null&&com.trim().equals("update")) {
			//1.MUdateCommand 생성
			command= new MUdateCommand();
			//2.execute 메소드 실행
			command.execute(request, response);
			//3. 해당되는 뷰페이지를 설정
			viewPage="/WEB-INF/view/list.do";
		}else if(com != null&&com.trim().equals("delete")) {
			command= new MDeleteCommand();
			command.execute(request, response);
			viewPage="/WEB-INF/view/list.do";
		}
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
