package service;

import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import dao.ArticleDao;
import dao.MemberDao;

public class MemberService {
	static MemberDao memberdao;
	public MemberService(Connection conn) {
		this.memberdao = new MemberDao(conn);
	}
	public Map<String, Object> findId(String loginid){
		return memberdao.findId(loginid);
    }
    
    public int doJoin(String loginid,String loginpw, String name){
		return memberdao.doJoin(loginid, loginpw, name);
    }
    
    public Map<String, Object> findMember(String loginid,String loginpw){
		return memberdao.findMember(loginid, loginpw);
    }

}
