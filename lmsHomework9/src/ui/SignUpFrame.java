package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUpFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private ActionHandler actionHandler;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	private SignUpFrame signUpFrame;
	public SignUpFrame() {
		this.signUpFrame = this;
		this.actionHandler = new ActionHandler();
		this.setSize(50, 200);
		Dimension screen =Toolkit.getDefaultToolkit().getScreenSize();
		double w = screen.getWidth();
		double h = screen.getHeight();
		this.setLocation((int) (w/2)+(588/2),(int) (h/2)-(584/2));
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255,255,255));

		JLabel lb1 = new JLabel("�̸�");
		panel.add(lb1);
		this.tf1 = new JTextField(11);
		panel.add(tf1);

		JLabel lb2 = new JLabel("���̵�");
		panel.add(lb2);
		this.tf2 = new JTextField(10);
		panel.add(tf2);

		JLabel lb3 = new JLabel("��й�ȣ");
		panel.add(lb3);
		this.tf3 = new JTextField(8);
		panel.add(tf3);
		
		JLabel lb4 = new JLabel("�й�");
		panel.add(lb4);
		this.tf4 = new JTextField(11);
		panel.add(tf4);

		JButton bt1 = new JButton("ȸ������");
		bt1.addActionListener(actionHandler);
		bt1.setActionCommand("bt1");
		panel.add(bt1);

		this.add(panel);
	}
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			run(e);
		}

	}
	@SuppressWarnings("resource")
	private void run(ActionEvent e) {
		if(e.getActionCommand().equals("bt1")) {

			String newName = tf1.getText();
			String newId = tf2.getText();
			String newPw = tf3.getText();
			String newSN = tf4.getText();
			if(!newName.equals("")&&!newId.equals("")&&!newPw.equals("")&&!newSN.equals("")) {
				try {
					BufferedWriter bw;
					bw = new BufferedWriter(new FileWriter("basket/"+newId+"basket.txt"));
					bw = new BufferedWriter(new FileWriter("basket/"+newId+"sincheong.txt"));
					bw.close();
					FileWriter fw = new FileWriter(new File("data/login"),true);
					fw.write(newId+" "+newPw);
					fw.close();
					fw = new FileWriter(new File("data/student"),true);
					fw.write(newId+" "+newName);
					fw.close();
					fw = new FileWriter(new File("data/studentNumber"),true);
					fw.write(newId+" "+newSN);
					fw.close();
					JOptionPane.showConfirmDialog(null,"ȸ�������� �Ϸ�Ǿ����ϴ�.","�޽���", JOptionPane.PLAIN_MESSAGE  );
					signUpFrame.dispose();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else {
				JOptionPane.showConfirmDialog(null,"��ĭ�� ������ �Է����ֽñ� �ٶ��ϴ�.","�޽���", JOptionPane.PLAIN_MESSAGE  );
			}
		}
	}
}
