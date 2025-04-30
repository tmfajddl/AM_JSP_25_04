package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import dto.Article;

public class ArticleDao {
    static Connection conn;

    public ArticleDao(Connection conn) {
        this.conn = conn;
    }
    public void doDelete(int id) {
		SecSql sql = SecSql.from("DELETE");
		sql.append("FROM article");
		sql.append("WHERE id = ?;", id);

		DBUtil.delete(conn, sql);
		return;
    }
    
    public Map<String, Object> showDetail(int id) {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?;", id);

		return DBUtil.selectRow(conn, sql);
    }
    
    public List<Article> showlist(int limitFrom, int itemsInAPage) {
    	SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT ?, ?;", limitFrom, itemsInAPage);

        List<Map<String,Object>> articleRows = DBUtil.selectRows(conn, sql);
		
		List<Article> articles = new ArrayList<>();
		
		for(Map<String,Object> articleMap : articleRows) {
			articles.add(new Article(articleMap));
		}
		
		return articles;
    }
    
    public static int listCount(int limitFrom, int itemsInAPage) {
		SecSql sql = SecSql.from("SELECT COUNT(*)");
		sql.append("FROM article;");

		return DBUtil.selectRowIntValue(conn, sql);
    }
    
    public static void doUpdate(String title, String body,int id) {
    	SecSql sql = SecSql.from("UPDATE article");
		sql.append("SET updateDate = NOW(),");
		sql.append("title = ?,",title);
		sql.append("`body` = ?",body);
		 sql.append("WHERE id = ?;", id);

		DBUtil.update(conn, sql);

		return;
    }
    
    public static int doWrite(String title, String body,String writer) {
		SecSql sql = SecSql.from("INSERT");
		sql.append("INTO article");
		sql.append("SET regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("`body` = ?,", body);
		sql.append("writer = ?;", writer);

		return DBUtil.insert(conn, sql);
    }
    public List<Article> getForPrintArticles(int limitFrom, int itemsInAPage) {
		SecSql sql = SecSql.from("SELECT A.*, M.name");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("ORDER BY A.id DESC");
		sql.append("LIMIT ?, ?;", limitFrom, itemsInAPage);
		
		List<Map<String,Object>> articleRows = DBUtil.selectRows(conn, sql);
		
		List<Article> articles = new ArrayList<>();
		
		for(Map<String,Object> articleMap : articleRows) {
			articles.add(new Article(articleMap));
		}
		
		return articles;
	}

	public int getTotalCnt() {
		SecSql sql = SecSql.from("SELECT COUNT(*)");
		sql.append("FROM article;");
		return DBUtil.selectRowIntValue(conn, sql);
	}
   
}
