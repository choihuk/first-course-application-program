package ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.ELecture;

public class BasketTable extends JTable {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private Vector<ELecture> basket;
	private JOptionPane jOptionPane;
	private int basketRowCount;
	private MouseListener mouseHandler;
	private String userId;
	public BasketTable(MouseListener mouseHandler, String userId) {
		this.userId=userId;
		this.mouseHandler = mouseHandler;
		paint();
	}
	public void paint() {
		try {
			this.setOpaque(false); 
			String header[] = {"강좌이름","교수님","학점","강좌시간"};
			this.model = new DefaultTableModel(header,0) {            //편집불가
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int rowIndex, int mColIndex) {
					return false;
				}
			};

			this.setModel(model);
			this.getColumn("강좌이름").setPreferredWidth(100);
			this.getColumn("교수님").setPreferredWidth(10);
			this.getColumn("학점").setPreferredWidth(10);
			this.getColumn("강좌시간").setPreferredWidth(200);
			this.addMouseListener(mouseHandler);


			this.basket = new Vector<ELecture>();
			Scanner sc = new Scanner(new File("basket/"+userId+"basket.txt"));
			while(sc.hasNext()) {
				ELecture eLecture = new ELecture();
				eLecture.read(sc);
				basket.add(eLecture);
			}
			Vector<String> listData;
			for(ELecture baskets : basket) {
				listData = new Vector<String>();
				listData.add(baskets.getName());
				listData.add(baskets.getProfessorName());
				listData.add(baskets.getCredit());
				listData.add(baskets.getTime());
				this.model.addRow(listData);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.basketRowCount = this.getRowCount();


	}

	public void MouseClickedAction(MouseEvent e) {

		int row = this.getSelectedRow();
		String Name = basket.get(row).getName();
		@SuppressWarnings("static-access")
		int msg1 = jOptionPane.showConfirmDialog(null, Name+" 강좌를 신청하시겠습니까?",
				"선택창", jOptionPane.OK_CANCEL_OPTION );
		if(msg1==0) {
			String ProfessorName = basket.get(row).getProfessorName();
			String Credit = basket.get(row).getCredit();
			String Time = basket.get(row).getTime();
			File file = new File("basket/"+userId+"sincheong.txt");
			try {
				FileWriter fw = new FileWriter(file,true);
				fw.write("0"+" "+Name+" "+ProfessorName+" "+Credit+" "+Time+"\n");
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	public void MouseClickedAction() {
		Vector<Integer> count = new Vector<Integer>();
		for(int a=0;this.getRowCount()>=a;a++) {
			if(this.isRowSelected(a)) {
				count.add(a);
			}
		}
		for(int b=0;count.size()>b;b++) {
			int row=count.get(b);
			String Name = basket.get(row).getName();
			String ProfessorName = basket.get(row).getProfessorName();
			String Credit = basket.get(row).getCredit();
			String Time = basket.get(row).getTime();
			File file = new File("basket/"+userId+"sincheong.txt");
			try {
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(file);
				boolean a=false;
				while(sc.hasNext()) {
					if(sc.next().contains(Name)) {
						a=true;
					}
				}
				if(a) {
					JOptionPane.showConfirmDialog(null,Name+" 강좌가 중복되었습니다.","메시지", JOptionPane.PLAIN_MESSAGE  );
				}else {
					try {
						FileWriter fw = new FileWriter(file,true);
						fw.write("0"+" "+Name+" "+ProfessorName+" "+Credit+" "+Time+"\n");
						fw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public String basketRowCount() {
		String a = Integer.toString(basketRowCount);
		return a;
	}

}
