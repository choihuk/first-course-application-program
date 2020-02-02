package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private SelectionPanel selectionPannel;
	private String userId;
	private String password;
	private boolean a = true;
	private MyInfoPanel myInfoPanel;
	private MainFrame mainFrame;
	private String studentName = null;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private LeftPanel leftPanel;
	private TimePanel timePanel;
	
	public MainFrame(String userId, String password) {
		this.userId=userId;
		this.password = password;
		this.mainFrame =this;
		//attributes 
		this.setSize(800, 600);
		Dimension screen =Toolkit.getDefaultToolkit().getScreenSize();
		double w = screen.getWidth();
		double h = screen.getHeight();

		this.setLocation((int) (w/2)-(800/2),(int) (h/2)-(600/2));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		paint();
	}
	private void paint() {

		ActionHandler actionHandler = new ActionHandler();

		//components
		this.p1 = new JPanel();
		this.p2 = new JPanel();
		this.p3 = new JPanel();
		this.p4 = new JPanel();
		
		JMenu menu = new JMenu("menu");
		JMenuBar menuBar = new JMenuBar();
		JMenuItem menuItem = new JMenuItem("logout");
		JMenuItem menuItem1 = new JMenuItem("exit");
		JMenuItem menuItem2 = new JMenuItem("homepage");
		
		menuItem.addActionListener(actionHandler);
		menuItem.setActionCommand("logout");
		menuItem1.addActionListener(actionHandler);
		menuItem1.setActionCommand("exit");
		menuItem2.addActionListener(actionHandler);
		menuItem2.setActionCommand("homepage");
		
		menuBar.add(menu);
		menu.add(menuItem);
		menu.add(menuItem1);
		menu.add(menuItem2);
		this.setJMenuBar(menuBar);
		
		
		JLabel title = new JLabel("명지대학교 수강신청");
		title.setFont(new Font("문체부 쓰기 정체", Font.BOLD, 40));
		p1.add(title);
		
		String name = null;
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(new File("data/student"));
			while(sc.hasNext()) {
				if(sc.next().equals(userId)) {
					name = sc.next();
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		this.leftPanel = new LeftPanel(actionHandler,name);

		JButton bt = new JButton();
		bt.addActionListener(actionHandler);
		bt.setActionCommand("picture");
		bt.setBounds(25,62, 100, 100);
		bt.setContentAreaFilled(false);
		this.add(bt);
		
		String studentName = null;
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(new File("data/studentNumber"));
			while(sc.hasNext()) {
				if(sc.next().equals(userId)) {
					studentName = sc.next();
				}
			}
			this.studentName = studentName;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		p2.setPreferredSize(new Dimension(150,500));
		p2.add(leftPanel);
		
		this.timePanel = new TimePanel();
		p2.add(timePanel);
		
		
		JButton time = new JButton("새로고침");
		time.setActionCommand("time");
		time.addActionListener(actionHandler);
		p2.add(time);

		
		this.setLayout(new BorderLayout());
		if(a) {
			this.selectionPannel = new SelectionPanel(userId);
			this.add(this.selectionPannel,"Center");
		}else {
			this.myInfoPanel = new MyInfoPanel(userId,password,name,studentName,actionHandler);
			this.add(myInfoPanel,"Center");
		}
		p1.setBackground(new Color(255,255,255));
		p2.setBackground(new Color(255,255,255));
		p3.setBackground(new Color(255,255,255));
		p4.setBackground(new Color(255,255,255));
		leftPanel.setBackground(new Color(255,255,255));
		this.add(p1,"North");
		this.add(p2,"West");
		this.add(p3,"East");
		this.add(p4,"South");


	}
	private class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("mybt")) {
				a=false;
				getContentPane().remove(selectionPannel);
				getContentPane().remove(p1);
				getContentPane().remove(p2);
				getContentPane().remove(p3);
				getContentPane().remove(p4);
				paint();
				myInfoPanel.updateUI();
			}else if(e.getActionCommand().equals("homebt")) {
				a=true;
				getContentPane().remove(myInfoPanel);
				getContentPane().remove(p1);
				getContentPane().remove(p2);
				getContentPane().remove(p3);
				getContentPane().remove(p4);
				paint();
				selectionPannel.updateUI();
			}else if(e.getActionCommand().equals("logout")) {
				mainFrame.dispose();
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
			}else if(e.getActionCommand().equals("bt1")) {
				myInfoPanel.activation();
			}else if(e.getActionCommand().equals("bt2")){
				reFile();
				reName();
				reStudentNunber();
				myInfoPanel.disactivation();
			}else if(e.getActionCommand().equals("exit")) {
				mainFrame.dispose();
			}else if(e.getActionCommand().equals("picture")) {
				MyPicture2 myPicture2 = new MyPicture2();
				myPicture2.setVisible(true);
			}else if(e.getActionCommand().equals("time")) {
				getContentPane().remove(selectionPannel);
				getContentPane().remove(p1);
				getContentPane().remove(p2);
				getContentPane().remove(p3);
				getContentPane().remove(p4);
				paint();
				selectionPannel.updateUI();
			}else if(e.getActionCommand().equals("homepage")) {
				try {
					Desktop.getDesktop().browse(new URI("http://www.mju.ac.kr/"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}

	}
	private void reFile() {
		String reId = myInfoPanel.getTfId();
		String rePw = myInfoPanel.getTfPw();
		try {
			String msg;
			BufferedReader br = new BufferedReader(new FileReader(new File("data/login")));
			Vector<String> listdata = new Vector<String>();
			while((msg=br.readLine())!=null) {
				if(!msg.contains(userId)) {
					listdata.add(msg);
				}
			}
			br.close();
			FileWriter fw = new FileWriter(new File("data/login"),false);
			for(int b=0;listdata.size()>b;b++){
				fw.write(listdata.get(b)+"\n");
			}
			fw.write(reId+" "+rePw+"\n");
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void reName() {
		String reName = myInfoPanel.getTfName();
		try {
			String msg;
			BufferedReader br = new BufferedReader(new FileReader(new File("data/student")));
			Vector<String> listdata = new Vector<String>();
			while((msg=br.readLine())!=null) {
				if(!msg.contains(userId)) {
					listdata.add(msg);
				}
			}
			br.close();
			FileWriter fw = new FileWriter(new File("data/student"),false);
			for(int b=0;listdata.size()>b;b++){
				fw.write(listdata.get(b)+"\n");
			}
			fw.write(userId+" "+reName+"\n");
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void reStudentNunber() {
		String reSN = myInfoPanel.getTfSN();
		try {
			String msg;
			BufferedReader br = new BufferedReader(new FileReader(new File("data/studentNumber")));
			Vector<String> listdata = new Vector<String>();
			while((msg=br.readLine())!=null) {
				if(!msg.contains(studentName)) {
					listdata.add(msg);
				}
			}
			br.close();
			FileWriter fw = new FileWriter(new File("data/studentNumber"),false);
			for(int b=0;listdata.size()>b;b++){
				fw.write(listdata.get(b)+"\n");
			}
			fw.write(userId+" "+reSN+"\n");
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JOptionPane.showConfirmDialog(null,"저장이 완료되었습니다.","메시지", JOptionPane.PLAIN_MESSAGE  );
	}
}
