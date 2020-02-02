package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import controller.CLecture;
import entity.ELecture;

public class VLecture {

	private CLecture cLecture;
	
	public VLecture() {
		this.cLecture = new CLecture();
	}

	public String show(String fileName) {
		// get data from controller
		Vector<ELecture> items;
		
		try {
			items = this.cLecture.getItems(fileName);
			
			//show items
			System.out.println("번호를 선택해 주세요.");
			for(ELecture item: items) {
				System.out.println(item.getNumber() + " " + item.getName()+" "+item.getCredit()+" "+
			item.getProfessorName()+" "+ item.getTime());
			}
			
			//get item number
			System.out.print("선택 : ");
			System.out.println("");
			Scanner scanner = new Scanner(System.in);
			int number = scanner.nextInt();
			int index = 0;
			String result = null;
			boolean found = false;
			for(index = 0; index < items.size() && !found; index++) {
				if(items.get(index).getNumber() == number) {
					found = true;
					result = items.get(index).getName();
				}
			}
			System.out.println(result+" 를 선택하셨습니다. \n책가방에 "+result+" 과목이 추가되었습니다.");
			File file = new File("basket/basket.txt");
			
			try {
				FileWriter fw = new FileWriter(file,true);
				fw.write(items.get(index-1).getNumber()+" "+items.get(index-1).getName()+"\n");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("책가방으로 넘어갑니다.");
			
			return result;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	
}
