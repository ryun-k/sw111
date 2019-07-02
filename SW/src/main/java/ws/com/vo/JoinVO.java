package ws.com.vo;

public class JoinVO {

	private String id; 
	private String pw;
	private String name;
	
	//타입
	private int	   age;	 
	
	//동일한 name값이 여러개라도 VO클래스에서 배열변수로 받으면 다 받을 수 있다
	private String[] hobby;
	
	// 반드시 setXxx()함수가 있어야 한다
	// setXxx()함수를 이용해서 데이터를 VO클래스에 입력하기 때문.
	
	
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
