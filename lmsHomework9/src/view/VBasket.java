package view;

import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.CBasket;

public class VBasket {

	private CBasket cBasket;
	public VBasket(){
		this.cBasket = new CBasket();

	}
	public void show() {
		System.out.println("\n수강신청 책가방\n");
		Scanner scanner;
		try {
			scanner = this.cBasket.getItems();
			int a = 1;
			while(scanner.hasNextLine()) {
				System.out.print(a+". "+scanner.nextLine()+"\n");
				a++;
			}
				System.out.println("");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

}
