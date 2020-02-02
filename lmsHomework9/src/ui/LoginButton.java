package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import view.VLogin;

public class LoginButton extends JButton{

	private static final long serialVersionUID = 1L;
	Color basicColor = new Color(193,222,249);
	Color changeColor= new Color(230,240,250);
	Color textColor= new Color(0,0,0);
	private MainFrame mainFrame;
	private LoginFrame loginFrame; 
	public LoginButton(String Name, LoginFrame loginFrame) {
		LoginMouseHandler mouseHandler = new LoginMouseHandler();
		LoginActionHandler actionHandler = new LoginActionHandler();
		this.addMouseListener(mouseHandler);
		this.setBackground(basicColor);
		this.setForeground(textColor);
		this.setActionCommand(Name);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.addActionListener(actionHandler);
		this.setText("·Î±×ÀÎ");
		this.setFont(new Font("",Font.PLAIN,30));
		this.loginFrame = loginFrame;
	}
	class LoginMouseHandler implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			MouseEnterAction();
		}
		@Override
		public void mouseExited(MouseEvent e) {
			MouseExitAction();
		}
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
	}
	private void MouseEnterAction() {
		this.setBackground(changeColor);
		}
	private void MouseExitAction() {
		this.setBackground(basicColor);
		}
	public void refresh(ActionEvent e) {
		JTextField lb1 = LoginPanel.lb1();
		JPasswordField lb2 = LoginPanel.lb2();
		String userIdText = lb1.getText();
		@SuppressWarnings("deprecation")
		String passwordText = lb2.getText();
			
			VLogin vLogin = new VLogin();
			String userId = vLogin.authenticate(userIdText, passwordText);
			if(userId != null) {
				this.mainFrame = new MainFrame(userId,passwordText);
				mainFrame.setVisible(true);
				loginFrame.dispose();
			}

	}
	public void refreshKey() {
		JTextField lb1 = LoginPanel.lb1();
		JPasswordField lb2 = LoginPanel.lb2();
		String userIdText = lb1.getText();
		@SuppressWarnings("deprecation")
		String passwordText = lb2.getText();
		
		VLogin vLogin = new VLogin();
		String userId = vLogin.authenticate(userIdText, passwordText);
		if(userId != null) {
			this.mainFrame = new MainFrame(userId,passwordText);
			mainFrame.setVisible(true);
			loginFrame.dispose();
		}

}
	public class LoginActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			refresh(e);
		}
	}
}
