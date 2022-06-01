import java.util.*;
import java.io.*;
class Course implements java.io.Serializable, Comparable<Course>{
	private String courseName, id, instructor, location;
	private int maxStudent, currentStudent, sectionNum;
	ArrayList<String> studentList;
	
	public Course() {
		
	}
	// Course_Name	Course_Id	Maximum_Students	Current_Students	List_Of_Names	Course_Instructor	Course_Section_Number	Course_Location
	public Course(String[] x) {
		this.courseName = x[0];
		this.id = x[1];
		this.instructor = x[5];
		this.location = x[7];
		this.maxStudent = Integer.parseInt(x[2]);
		this.currentStudent = Integer.parseInt(x[3]);
		this.sectionNum = Integer.parseInt(x[6]);
		this.studentList = new ArrayList<String>();
	}
	public Course(String courseName, String courseId, int maxStudent, int currentStudent, String instructor, int sectionNum, String location) {
		this.courseName = courseName;
		this.id = courseId;
		this.maxStudent = maxStudent;
		this.currentStudent = currentStudent;
		this.instructor = instructor;
		this.sectionNum = sectionNum;
		this.location = location;
		this.studentList = new ArrayList<String>();
	}
	
	
	public void displayCourseInfo() {
		
		System.out.printf("%s, %s, %d, %d, %s, %d, %s\n", courseName, id, maxStudent, currentStudent, instructor, sectionNum, location);
	}

	public int getMaxStudent() {
		return maxStudent;
	}

	public int getCurrentStudent() {
		return currentStudent;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getId() {
		return id;
	}

	
	public String getInstructor() {
		return instructor;
	}

	public String getLocation() {
		return location;
	}

	public int getSectionNum() {
		return sectionNum;
	}

	public ArrayList<String> getStudent() {
		return studentList;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}

	public void setStudent(ArrayList<String> student) {
		this.studentList = student;
	}
	
	public void setMaxStudent(int maxStudent) {
		this.maxStudent = maxStudent;
	}

	public void setCurrentStudent(int currentStudent) {
		this.currentStudent = currentStudent;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public boolean isfull() {
		return currentStudent == maxStudent;
			
	}
	@Override
	public int compareTo(Course c) {
		int compareage = ((Course)c).getCurrentStudent();
		return this.currentStudent - compareage;
	}
	
}
