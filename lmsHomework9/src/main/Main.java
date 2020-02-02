package main;
import ui.LoginFrame;
public class Main {
	//components
	private LoginFrame login;
	//constructor
	public Main() {
		this.login = new LoginFrame();
	}
	//여기부터
	private void run() {

		this.login.setVisible(true);
	} 


	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	//여기까지 고정.


}
