import java.util.Date;

public class Message {
	private Date dateMessage;
	private int idMessage;
	private int idUser;
	private String message;
	private String user;
	private int score;
	
	public Message(Date dateM,int idM,int idU,String message,String user,int score) {
		this.dateMessage=dateM;
		this.idMessage=idM;
		this.idUser=idU;
		this.message=message;
		this.user=user;
		this.score=score;
	}
	
	public Date getDate() {
		return this.dateMessage;
	}
	
	public int getIdMessage() {
		return this.idMessage;
	}
	
	public int getIdUser() {
		return this.idUser;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score=score;
	}
	
	public String toString() {
		return "Message de "+this.user+" du "+this.dateMessage+" : "+this.idMessage;
	}
	
}
