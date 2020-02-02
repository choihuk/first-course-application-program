package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.ELecture;

public class SincheongTable extends JTable {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private int SinRowCount;
	private int Credit;
	private String userId;
	
	public SincheongTable(String userId) {
		this.userId = userId;
		paint();
	}
	public void paint() {
		try {
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
			
			Vector<ELecture> basket = new Vector<ELecture>();
			Scanner sc = new Scanner(new File("basket/"+userId+"sincheong.txt"));
			while(sc.hasNext()) {
				ELecture eLecture = new ELecture();
				eLecture.read(sc);
				basket.add(eLecture);
			}
			Vector<String> listData;
			this.Credit = 0;
			for(ELecture baskets : basket) {
				listData = new Vector<String>();
				listData.add(baskets.getName());
				listData.add(baskets.getProfessorName());
				listData.add(baskets.getCredit());
				listData.add(baskets.getTime());
				int a = Integer.parseInt(baskets.getCredit());
				Credit += a;
				this.model.addRow(listData);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		SinRowCount = this.getRowCount();		
		
	}
	public String sinRowCount() {
		String a = Integer.toString(SinRowCount);
		return a;
	}
	public String credit() {
		String a = Integer.toString(Credit);
		return a;
	}

}
