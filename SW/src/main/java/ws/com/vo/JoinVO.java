package ws.com.vo;

public class JoinVO {

	private String id; 
	private String pw;
	private String name;
	
	//Ÿ��
	private int	   age;	 
	
	//������ name���� �������� VOŬ�������� �迭������ ������ �� ���� �� �ִ�
	private String[] hobby;
	
	// �ݵ�� setXxx()�Լ��� �־�� �Ѵ�
	// setXxx()�Լ��� �̿��ؼ� �����͸� VOŬ������ �Է��ϱ� ����.
	
	
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
