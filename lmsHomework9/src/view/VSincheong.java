package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import controller.CSincheong;
import entity.ELecture;
import entity.ESincheong;

public class VSincheong {

	private CSincheong cSincheong;
	public VSincheong() {
		this.cSincheong = new CSincheong();
	}

	public String show() {
		System.out.println("\n������ûȭ��\n");
		// get data from controller
		Vector<ESincheong> items;

		try {
			items = this.cSincheong.getItems();

			//show items
			System.out.println("������û�� ������ ��ȣ�� �Է��� �ּ���.");
			for(ESincheong item: items) {
				System.out.println(item.getNumber() + " " + item.getName());
			}

			//get item number
			System.out.print("���� : ");
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
			System.out.println(result+" ���� ��û�� �Ϸ�Ǿ����ϴ�.");
			File file = new File("data/sincheong");

			try {
				FileWriter fw = new FileWriter(file,true);
				fw.write(items.get(index-1).getNumber()+" "+items.get(index-1).getName()+"\n");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return result;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}








