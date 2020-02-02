package ui;

import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.CLecture;
import entity.ELecture;
import ui.SelectionPanel.SelectionMouseHandler;

public class LectureTable extends JTable {
	private static final long serialVersionUID = 1L;
	private  Vector<String> listData;     	// model data
	private Vector<ELecture> eLectures;
	private CLecture cLecture;				//service = control
	private DefaultTableModel tableModel;

	public LectureTable(SelectionMouseHandler mouseHandler) {
		
		String header[] = {"�����̸�","������","����","���½ð�"};
		this.cLecture = new CLecture();
		
		this.tableModel = new DefaultTableModel(header,0) {            //�����Ұ�
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
		};

		this.setModel(tableModel);
		this.getColumn("�����̸�").setPreferredWidth(100);
		this.getColumn("������").setPreferredWidth(10);
		this.getColumn("����").setPreferredWidth(10);
		this.getColumn("���½ð�").setPreferredWidth(200);
		
		this.addMouseListener(mouseHandler);
	}
	public void refresh(String fileName) {
		try {
			if(fileName != null) {
				this.eLectures = new Vector<ELecture>();
				this.eLectures = this.cLecture.getItems("data/"+fileName);
				this.tableModel.setRowCount(0);//�� ������ ����
				for(ELecture eLecture : eLectures) {
					this.listData = new Vector<String>();
					this.listData.add(eLecture.getName());
					this.listData.add(eLecture.getProfessorName());
					this.listData.add(eLecture.getCredit());
					this.listData.add(eLecture.getTime());
					this.tableModel.addRow(listData);
				}
				
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}
	public Vector<ELecture> eLecture(){
		return this.eLectures;
	}
	
	public Vector<ELecture> getSelectedLectures(){
		return null;
	}
}
