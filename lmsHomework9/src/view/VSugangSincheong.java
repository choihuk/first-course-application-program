package view;

public class VSugangSincheong {
	private VSugangInfo vSugangInfo;
	private VBasket vBasket;
	private VSincheong vSincheong;
	
	public VSugangSincheong() {
		this.vSugangInfo = new VSugangInfo();
		this.vBasket = new VBasket();
		this.vSincheong = new VSincheong();
	}

	public void show(String userId) {
		System.out.println(userId + " �� ȯ���մϴ�.");
		this.vBasket.show();
		this.vSugangInfo.show();
		this.vSincheong.show();
	}

}
