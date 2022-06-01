import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
interface adm{
	void creatCourse(String courseName, String courseId, int maxStudent, int currentStudent, String instructor, int sectionNum, String location, ArrayList<String> namesList);
	void deleteCourse(String courseName, int sectionNum);

	void displayCourseInfoById(String id);
	void registerStudent(String username1, String password1, String firstN1, String lastN1);
	void viewAll();
	void viewFull();
	void writeToFileFull();
	void viewStudentsInCourse(String courseName, int sec);
	void viewStudentCourse(String firstN, String lastN);
}
class Admin extends User implements adm, java.io.Serializable{

	ArrayList<Student> st;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String username, String password, String firstN, String lastN) {
		super(username, password, firstN, lastN);
		this.st = new ArrayList<Student>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void creatCourse(String courseName, String courseId, int maxStudent, 
			int currentStudent, String instructor, int sectionNum, String location, ArrayList<String> namesList) {
		// TODO Auto-generated method stub
		
		Course newCourse = new Course(courseName, courseId, maxStudent, currentStudent, instructor, sectionNum, location);
		newCourse.setStudent(namesList);
		cl.addCourse(newCourse);
		//setCl(cl);
	}

	@Override
	public void deleteCourse(String courseName, int sectionNum) {
		// TODO Auto-generated method stub
		for (int i = 0; i < cl.arr.size(); i++) {
			if (cl.arr.get(i).getCourseName().replaceAll("\\s", "").equalsIgnoreCase(courseName.replaceAll("\\s","")) && cl.arr.get(i).getSectionNum() == sectionNum) {
			cl.arr.remove(i);
				System.out.println("Successfully Deleted!");
				return;
			}
		}
		System.out.println("Course not found!");
	}

	
	public void displayCourseInfoById(String id) {

		for (Course c: cl.arr) {
			if (c.getId().equalsIgnoreCase(id)) {
				c.displayCourseInfo();
				return;
			}
		}
		System.out.println("Course not found!");
	}

	@Override
	public void registerStudent(String username1, String password1, String firstN1, String lastN1) {
		// TODO Auto-generated method stub
		

		Student s = new Student(username1, password1, firstN1, lastN1);
		st.add(s);
	}


	@Override
	public void viewFull() {
		// TODO Auto-generated method stub
		for (Course c: cl.arr) {
			if (c.isfull() == true) {
				c.displayCourseInfo();
			}
		}
	}


	@Override
	public void writeToFileFull() {
		// TODO Auto-generated method stub
		try {
			FileWriter fw = new FileWriter(new File("FullCourses.csv"));
			PrintWriter pw = new PrintWriter(fw);
			String header = "Course_Name,Course_Id,Maximum_Students,Current_Students,List_Of_Names,Course_Instructor,Course_Section_Number,Course_Location";
			pw.println(header);
			for (Course ci: cl.arr) {
				if (ci.isfull() == true) {
					String info = ci.getCourseName()+ ", "+ci.getId()+ ", "+ String.valueOf(ci.getMaxStudent())+ ", "+ String.valueOf(ci.getCurrentStudent())+ ", "+ String.join("; ", ci.getStudent()) + "," + ci.getInstructor() + "," + String.valueOf(ci.getSectionNum()) + "," + ci.getLocation();
					//pw.printf("%s, %s, %s, %s, %s\n",ci.getCouseName(), ci.getInstructor(), String.valueOf(ci.getSectionNum()), String.valueOf(ci.getNumStudents()), ci.getLocation());
					//pw.println(info[0] + ", "+info[1]+", " + info[2] + ", " + info[3] + ", " + info[4]);
					pw.println(info);
				}
			}
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void viewStudentsInCourse(String courseName, int sec) {
		// TODO Auto-generated method stub
		for (Course c: cl.arr) {
			if (c.getCourseName().replaceAll("\\s", "").equalsIgnoreCase(courseName.replaceAll("\\s","")) 
					&& c.getSectionNum() == sec) {
				for (String s: c.getStudent()) {
					System.out.println(s);
				}
				return;
			}
		}

		System.out.println("Course not found!");

	}

	@Override
	public void viewStudentCourse(String firstN, String lastN) {
		// TODO Auto-generated method stub
		String name = firstN + " " + lastN;
		for (Course c: cl.arr) {
			if (c.studentList.contains(name)) {
				System.out.println(c.getCourseName());
			}
		}
		
	}
	
	public void sortByStudentNum() {
		Collections.sort(cl.arr);
	}
	
	public void setCl(CourseList cl) {
		super.setCl(cl);
	}
}
