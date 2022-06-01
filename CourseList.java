import java.util.*;
import java.io.*;
class CourseList implements java.io.Serializable{
	ArrayList<Course> arr = new ArrayList<>();
	public CourseList() {

	}
	public CourseList(String FileName){
		File file = new File(FileName);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));

			String line = "";
			br.readLine();
			while ((line = br.readLine()) != null) {

				arr.add(new Course(line.split(",")));
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 2. add course
	public void addCourse(Course c) {
		arr.add(c);
	}

	// 3. edit course
	public void editCourse(String name, int id, String newName, String insName) {
		for (Course ci: arr) {
			if (ci.getCourseName().equalsIgnoreCase(name) && ci.getSectionNum() == id) {
				ci.setCourseName(newName);
				ci.setInstructor(insName);
				return;
			}
		}
		System.out.println("Course not found");
	}

	// 4. display all courses
	public void displayAll() {
		for (Course ci: arr) {
			ci.displayCourseInfo();
		}
	}


	// 5. delete a course
	public void delete(String name, int id) {
		for (int i = 0; i < arr.size(); i++) {
			Course ci = arr.get(i);
			if (ci.getCourseName().equalsIgnoreCase(name) && ci.getSectionNum() == id) {
				arr.remove(i);
				return;
			}
		}
		System.out.println("Course not found");
	}

	public void displayOpen() {
		for (Course c: arr) {
			if (c.isfull() == false) {
				c.displayCourseInfo();
			}
		}
	}
	
	
	
}
