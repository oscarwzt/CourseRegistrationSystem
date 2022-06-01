
class User implements java.io.Serializable{
	String username, password, firstN, lastN;
	protected CourseList cl;//= new CourseList("/Users/a/Desktop/NYU/DSCS/DataStructures/MyUniversityCoursesFile.csv");
	public User() {
		
	}

	public User(String username, String password, String firstN, String lastN) {
		this.username = username;
		this.password = password;
		this.firstN = firstN;
		this.lastN = lastN;
		//this.cl = new CourseList("/Users/a/Desktop/NYU/DSCS/DataStructures/MyUniversityCoursesFile.csv");
	}
	
	public void setCl(CourseList cl) {
		this.cl = cl;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void viewAll() {
		// TODO Auto-generated method stub
		cl.displayAll();
	}
	
}
