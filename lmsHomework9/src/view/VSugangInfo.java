package view;

public class VSugangInfo {

	private VDirectory vCampus;
	private VDirectory vCollege;
	private VDirectory vDepartment;
	private VLecture vLecture;
	
	public VSugangInfo() {
		vCampus = new VDirectory();
		vDepartment = new VDirectory();
		vCollege = new VDirectory();
		vLecture = new VLecture();
	}
	
	public void show() {
		String fileName = this.vCampus.show("data/root");
		fileName = this.vCollege.show("data/" + fileName);
		fileName = this.vDepartment.show("data/" + fileName);
		String eLecture = this.vLecture.show("data/" + fileName);
	}
	
}
