import java.io.*;
import java.util.*;
interface students{
	void viewAll();
	void viewOpen();
	void register(String courseN, int sec);
	void viewMyCourse();
}
class Student extends User implements students{
	//String username,  password, firstN, lastN;
	ArrayList<Course> myCourses;
	public Student() {
		super();
		
	}
	
	public Student(String username, String password, String firstN, String lastN) {
		super(username, password, firstN, lastN);
		this.myCourses = new ArrayList<Course>();
	}
	


	public void viewOpen() {
		// TODO Auto-generated method stub
		cl.displayOpen();
	}

	@Override
	public void register(String courseN, int sec) {
		// TODO Auto-generated method stub
		for (Course c: cl.arr) {
			if (c.getCourseName().replaceAll("\\s", "").equalsIgnoreCase(courseN.replaceAll("\\s","")) && c.getSectionNum() == sec) {
				
				myCourses.add(c);
				c.setCurrentStudent(c.getCurrentStudent()+1);
				c.studentList.add(firstN + " " + lastN);
				System.out.println("Successfully Registered!");
				return;
			}
		}
		System.out.println("Course not found!");
	}
	
	public void withdraw(String courseN, String fullName) {
		for (Course c: cl.arr) {
			if (c.getCourseName().equalsIgnoreCase(courseN)) {
				c.studentList.remove(fullName);
				myCourses.remove(c);
				c.setCurrentStudent(c.getCurrentStudent()-1);
				c.studentList.remove(firstN + " " + lastN);
				System.out.println("Successfully withdrawn!");
			}
		}
		System.out.println("You are not registered in this class!");
	}

	@Override
	public void viewMyCourse() {
		// TODO Auto-generated method stub
		for (Course c: myCourses) {
			c.displayCourseInfo();
		}
	}
	
	public void setCl(CourseList cl) {
		super.setCl(cl);
	}
	


}
