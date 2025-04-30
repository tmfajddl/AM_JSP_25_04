package dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Member {
	 private int id;
	    private LocalDateTime regDate;
	    private String liginId;
	    private String liginPw;
	    private String Name;

	    public Member(Map<String, Object> memberMap) {
	        this.id = (int) memberMap.get("id");
	        this.regDate = (LocalDateTime) memberMap.get("regDate");
	        this.liginId = (String) memberMap.get("liginId");
	        this.liginPw = (String) memberMap.get("liginPw");
	        this.Name = (String) memberMap.get("name");
	    }

	    public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }
	    public LocalDateTime getRegDate() {
	        return regDate;
	    }
	    public void setRegDate(LocalDateTime regDate) {
	        this.regDate = regDate;
	    }
	    public String getLiginId() {
	        return liginId;
	    }
	    public void setLiginId(String liginId) {
	        this.liginId = liginId;
	    }
	    public String getLiginPw() {
	        return liginPw;
	    }
	    public void setLiginPw(String liginPw) {
	        this.liginPw = liginPw;
	    }
	    public String getName() {
	        return Name;
	    }
	    public void setName(String name) {
	        Name = name;
	    }

}
