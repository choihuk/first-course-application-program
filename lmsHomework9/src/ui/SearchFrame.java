package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private ActionHandler actionHandler;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;

	public SearchFrame(){

		this.actionHandler = new ActionHandler();
		this.setSize(50, 270);
		Dimension screen =Toolkit.getDefaultToolkit().getScreenSize();
		double w = screen.getWidth();
		double h = screen.getHeight();
		this.setLocation((int) (w/2)+(588/2),(int) (h/2));
		
		this.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255,255,255));

		JLabel lb = new JLabel("--------------Id 찾기------------- ");
		panel.add(lb);

		JLabel lb1 = new JLabel("이름");
		panel.add(lb1);
		this.tf1 = new JTextField(10);
		panel.add(tf1);

		JLabel lb2 = new JLabel("학번");
		panel.add(lb2);
		this.tf2 = new JTextField(10);
		panel.add(tf2);

		JButton bt1 = new JButton("Id 찾기");
		bt1.addActionListener(actionHandler);
		bt1.setActionCommand("bt1");
		panel.add(bt1);

		JLabel lb3 = new JLabel("--------------Pw 찾기------------- ");
		panel.add(lb3);

		JLabel lb4 = new JLabel("아이디");
		panel.add(lb4);
		this.tf3 = new JTextField(9);
		panel.add(tf3);

		JLabel lb5 = new JLabel("학번");
		panel.add(lb5);
		this.tf4 = new JTextField(10);
		panel.add(tf4);

		JButton bt2 = new JButton("Pw 찾기");
		bt2.addActionListener(actionHandler);
		bt2.setActionCommand("bt2");
		panel.add(bt2);
		this.add(panel);
	}
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("bt1")) {
				String name = tf1.getText();
				String studentNumber = tf2.getText();
				try {
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(new File("data/student"));
					Vector<String> listdata = new Vector<String>();
					String Id1="_";
					for(int a=0;sc.hasNext();a++) {
						listdata.add(sc.next());
						if(listdata.get(a).equals(name)) {
							Id1=listdata.get(a-1);
						}
					}
					sc = new Scanner(new File("data/studentNumber"));
					listdata = new Vector<String>();
					String Id2="-";
					for(int a=0;sc.hasNext();a++) {
						listdata.add(sc.next());
						if(listdata.get(a).equals(studentNumber)) {
							Id2=listdata.get(a-1);
						}
					}
					if(Id1.equals(Id2)) {
						JOptionPane.showConfirmDialog(null,name+"님의 아이디는 "+Id1+" 입니다.","메시지", JOptionPane.PLAIN_MESSAGE  );
					}else if(Id1.equals(null)||Id2.equals(null)){
						JOptionPane.showConfirmDialog(null,"회원정보가 없거나 잘못 입력하셨습니다.","메시지", JOptionPane.PLAIN_MESSAGE  );
					}else {
						JOptionPane.showConfirmDialog(null,"회원정보가 없거나 잘못 입력하셨습니다.","메시지", JOptionPane.PLAIN_MESSAGE  );
					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}else if(e.getActionCommand().equals("bt2")) {
				String id = tf3.getText();
				String studentNumber = tf4.getText();
				try {
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(new File("data/studentNumber"));
					String pw=null;
					while(sc.hasNextLine()){
						if(sc.nextLine().equals(id+" "+studentNumber)) {
							sc = new Scanner(new File("data/login"));
							Vector<String> listdata = new Vector<String>();
							
							for(int b=0;sc.hasNext();b++){
								listdata.add(sc.next());
								if(listdata.get(b).equals(id)) {
									listdata.add(sc.next());
									pw = listdata.get(b+1);
								}
							}
						}
					}
					if(!pw.equals(null)) {
						JOptionPane.showConfirmDialog(null,id+"의 비밀번호는 "+pw+" 입니다.","메시지", JOptionPane.PLAIN_MESSAGE  );
					}else {
						JOptionPane.showConfirmDialog(null,"회원정보가 없거나 잘못 입력하셨습니다.","메시지", JOptionPane.PLAIN_MESSAGE  );
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
