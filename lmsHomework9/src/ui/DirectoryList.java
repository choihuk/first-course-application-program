package ui;

import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import controller.CDirectory;
import entity.EDirectory;

public class DirectoryList extends JList<String> {
	private static final long serialVersionUID = 1L;

	private CDirectory directory;
	private Vector<EDirectory> edirectories;
	public DirectoryList(ListSelectionListener listSelectionHandler,String fileName) {
		try {
			this.directory = new CDirectory();
			this.edirectories = this.directory.getItems("data/"+fileName);
			
			
			Vector<String> listData = new Vector<String>();
			for(EDirectory eDirectory : edirectories) {
				listData.add(eDirectory.getName());
			}
			this.setListData(listData);
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			this.addListSelectionListener(listSelectionHandler);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public String getSelectedFileName() {
		int selectedIndex = this.getSelectedIndex();
		if(selectedIndex != -1) {
			return this.edirectories.get(selectedIndex).getHyperLink();
		}
		return this.edirectories.get(0).getHyperLink();
	}
	public String refresh(String fileName) throws FileNotFoundException {
		this.edirectories = this.directory.getItems("data/"+fileName);
		
		
		Vector<String> listData = new Vector<String>();
		for(EDirectory eDirectory : edirectories) {
			listData.add(eDirectory.getName());
		}
		this.setListData(listData);
		this.setSelectedIndex(0);
		return this.edirectories.get(0).getHyperLink();
	}


}
