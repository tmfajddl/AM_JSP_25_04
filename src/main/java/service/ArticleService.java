package service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import dao.ArticleDao;

public class ArticleService {
	static ArticleDao articledao;
	public ArticleService(Connection conn) {
		this.articledao = new ArticleDao(conn);
	}
	
	public void doDelete(int id) {
		articledao.doDelete(id);
    }
    
    public Map<String, Object> showDetail(int id) {

		return articledao.showDetail(id);
    }
    
    public List<Map<String, Object>> showlist(int limitFrom, int itemsInAPage) {
		return articledao.showlist(limitFrom,itemsInAPage);
    }
    
    public static int listCount(int limitFrom, int itemsInAPage) {
		return articledao.listCount(limitFrom,itemsInAPage);
    }
    
    public static void doUpdate(String title, String body,int id) {
		articledao.doUpdate(title, body, id);
    }
    
    public static int doWrite(String title, String body,String writer) {
		return articledao.doWrite(title, body, writer);
    }

}
