package KHJ.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import KHJ.dto.MemberDto;

public class MemberDao {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public MemberDao() {
		try {
			Context initContext = new InitialContext();
			Context envContext =(Context) initContext.lookup("java:/comp/env");
			ds=(DataSource)envContext.lookup("jdbc/KHJ");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void close(){
		try {
			if(con!=null) {
				con.close();
				con=null;
			}}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	public ArrayList<MemberDto> list() {
		// TODO Auto-generated method stub
		String sql="select * from member";
		ArrayList<MemberDto> dtos =new ArrayList<MemberDto>();
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setJoinDate(rs.getDate("joinDate"));
				dtos.add(dto);
			}
			rs.close(); pstmt.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return dtos;
	}
	//MemberDto를 DB에 저장하는 insert 메소드 구현
	public void insert(MemberDto dto) throws Exception {
		//1.sql문
		String sql = "insert into member(id,pwd,name,email,JOINDATE) values(?,?,?,?,SYSDATE)";
		//2.Connection pool에서 connection 가져오기
		//try {
		try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getPwd());
		pstmt.setString(3, dto.getName());
		pstmt.setString(4, dto.getEmail());
		
		pstmt.executeUpdate();
		
		//3.connection을 통해서 MemberDto의 데이터를 sql문에 넣기
		pstmt.close();
		}catch (Exception ex) {
			// TODO: handle exception
			System.out.println("SQL insert 오류:"+ ex.getLocalizedMessage());
		}
	}
	//view: 선택된 하나의 레코드를 데이터 베이스에서 
	public MemberDto view(String id) throws SQLException {
		String sql="SELECT PWD,NAME,EMAIL,JOINDATE FROM MEMBER WHERE ID =?";
		
		MemberDto dto=new MemberDto();
		try {con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
	
				dto.setId(id);
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setJoinDate(rs.getDate("joinDate"));
			}
			pstmt.close();
			rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	}
	public void update(MemberDto dto) {
		String sql ="UPDATE MEMBER SET PWD=?,NAME=?,EMAIL=?,JOINDATE=? WHERE ID=?";
		boolean check = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setDate(4, dto.getJoinDate());
			pstmt.setString(5, dto.getId());
			int x= pstmt.executeUpdate();
			
			if(x<1) {
				System.out.println("정상적으로 작동되지 않았습니다.");
			}else {
				check=true;
			}
			
			pstmt.close();
			}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("SQL insert 오류:"+ e.getLocalizedMessage());
		}
	}
	public void delete(String id) {
		String sql ="DELETE FROM MEMBER WHERE ID=?";
		boolean check = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			
			int x= pstmt.executeUpdate();
			
			if(x<1) {
				System.out.println("정상적으로 작동되지 않았습니다.");
			}else {
				check=true;
			}
			
			pstmt.close();
			}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("SQL insert 오류:"+ e.getLocalizedMessage());
		}finally {
			close();
		}
	}
		
	}
	

