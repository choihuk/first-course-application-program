package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class LeftPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private ActionListener actionHandler;
	private String name;
	public LeftPanel(ActionListener actionHandler, String name) {
		this.actionHandler =actionHandler;
		this.name = name;
		paint();
	}
	public void paint() {
		this.setPreferredSize(new Dimension(140,200));
		this.setBorder(new TitledBorder(new LineBorder(Color.gray,1)));
		MyPicture myPicture = new MyPicture();
		myPicture.setActionMap(null);
		this.add(myPicture);
		JLabel lb1 = new JLabel(name+" �� ȯ���մϴ�.");
		this.add(lb1);
		JButton mybt = new JButton("ȸ������");
		mybt.setToolTipText("ȸ������ ������ ���ų� ������ �� �ֽ��ϴ�.");
		mybt.addActionListener(actionHandler);
		mybt.setActionCommand("mybt");
		this.add(mybt);
		JButton logout = new JButton("�α׾ƿ�");
		logout.setToolTipText("�α��� ȭ������ ��ȯ�˴ϴ�.");
		logout.addActionListener(actionHandler);
		logout.setActionCommand("logout");
		this.add(logout);
		

	}

}
