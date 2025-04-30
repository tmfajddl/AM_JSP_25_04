package dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Article {
	 private int id;
	 private LocalDateTime regDate;
	 private LocalDateTime updateDate;
	    private String title;
	    private String body;
	    private String writer;
	    private static String search;

	    public Article(Map<String, Object> articleRow) {
	        this.id = (int) articleRow.get("id");
	        this.regDate = (LocalDateTime) articleRow.get("regDate");
	        this.updateDate = (LocalDateTime) articleRow.get("updateDate");
	        this.title = (String) articleRow.get("title");
	        this.body = (String) articleRow.get("body");
	        this.writer = (String) articleRow.get("writer");
	    }

	    public LocalDateTime getRegDate() {
	        return regDate;
	    }

	    public void setRegDate(LocalDateTime regDate) {
	        this.regDate = regDate;
	    }

	    public LocalDateTime getUpdateDate() {
	        return updateDate;
	    }

	    public void setUpdateDate(LocalDateTime updateDate) {
	        this.updateDate = updateDate;
	    }


	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getBody() {
	        return body;
	    }

	    public void setBody(String body) {
	        this.body = body;
	    }
	    public String getWriter() {
	        return writer;
	    }
	    public void setWriter(String writer) {
	        this.writer = writer;
	    }
	    public static String getSearch() {
	        return search;
	    }
	    public static void setSearch(String search) {
	        Article.search = search;
	    }
}
