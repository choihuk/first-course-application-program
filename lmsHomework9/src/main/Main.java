package main;
import ui.LoginFrame;
public class Main {
	//components
	private LoginFrame login;
	//constructor
	public Main() {
		this.login = new LoginFrame();
	}
	//�������
	private void run() {

		this.login.setVisible(true);
	} 


	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	//������� ����.


}
