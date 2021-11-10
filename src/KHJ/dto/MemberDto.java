package KHJ.dto;

import java.sql.Date;

public class MemberDto {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
//	
//	id varchar2(10),
//    pwd varchar2(10),
//    name varchar2(50),
//    email varchar2(50),
//    joinDate date
//    
}
