package dao;

import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

public class MemberDao {
	static Connection conn;

    public MemberDao(Connection conn) {
        this.conn = conn;
    }
    
    public Map<String, Object> findId(String loginid){
    	SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginid = ?;",loginid);
		return DBUtil.selectRow(conn, sql);
    }
    
    public int doJoin(String loginid,String loginpw, String name){
    	SecSql sql = SecSql.from("INSERT");  
		sql.append("INTO `member`");
		sql.append("SET regDate = NOW(),");
		sql.append("loginid = ?,", loginid);
		sql.append("loginpw = ?,", loginpw);
		sql.append("`name` = ?;", name);
		return DBUtil.insert(conn, sql);
    }
    
    public Map<String, Object> findMember(String loginid,String loginpw){
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginid = ? AND", loginid);
		sql.append("loginpw = ?;", loginpw);
		return DBUtil.selectRow(conn, sql);
    }
}
