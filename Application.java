
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Application {
	static Student me;
	static Admin ad;
	static CourseList cl;
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {


		String studentFileName = "Student.ser";
		String adminFileName = "Admin.ser";
		String courseListFileName = "CourseList.ser";
		
		// Default student username: abcd
		// password: zxcv

		print("Are you: 1. Student or 2. Admin?");
		int temp = sc.nextInt();
		if (temp == 1) {
			int choice = 0;
			loadStudentObject(studentFileName);
			
			Studentlogin();
			loadCourseList(courseListFileName);
			me.setCl(cl);
			// Student System
			do {
				print(	"1. View all courses\n"
						+ "2. View all courses that are not FULL\n"
						+ "3. Register on a course (enter the course name, section, and student full name\n"
						+ "4. Withdraw from a course (course name, full name) \n"
						+ "5. View all courses that the current student is being registered in\n"
						+ "6. Exit");
				print("Please choose from the above menu: ");
				choice = sc.nextInt();

				switch(choice) {
				case 1:
					me.viewAll();
					break;

				case 2:
					me.viewOpen();
					break;

				case 3:
					print("Please enter the course name: ");
					sc.nextLine();
					String courseN = sc.nextLine();
					print("Please enter the section number: ");
					int sec = sc.nextInt();
					me.register(courseN, sec);
					break;

				case 4:
					print("Enter the course name: ");
					sc.nextLine();
					String courseN1 = sc.nextLine();

					print("Enter your first and last name seperated by a white space");
					String name = sc.nextLine();
					me.withdraw(courseN1, name);
					break;

				case 5:
					me.viewMyCourse();

				}
			}while (choice!=6);
			try {
				//FileOutput Stream writes data to a file

				FileOutputStream fos1 = new FileOutputStream("CourseList.ser");

				//ObjectOutputStream writes objects to a stream (A sequence of data)
				ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
				//Writes the specific object to the OOS
				oos1.writeObject(cl);
				//Close both streams

				oos1.close();
				fos1.close();
				System.out.println("CourseList Serialization complete");
			} 
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			try {
				//FileOutput Stream writes data to a file
				FileOutputStream fos = new FileOutputStream("Student.ser");

				//ObjectOutputStream writes objects to a stream (A sequence of data)
				ObjectOutputStream oos = new ObjectOutputStream(fos);


				//Writes the specific object to the OOS
				oos.writeObject(me);
				//Close both streams
				oos.close();
				fos.close();

				System.out.println("Student Serialization complete");
			} 
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		if (temp == 2) {
			int cont = 1;
			loadAdminObject(adminFileName);
			Adminlogin();
			loadCourseList(courseListFileName);
			ad.setCl(cl);
			while (cont != 2) {
				print("Please select one of the options: \n1. Course Management\n" + "2. Reports");
				int adminChoice = sc.nextInt();
				if (adminChoice == 1) {
					int choice = 0;
					do {
						print("1.Create a new course\n"
								+ "2.Delete a course\n"
								+ "3.Edit a course\n"
								+ "4.Display information for a given course (by course ID)\n"
								+ "5.Register a student\n"
								+ "6.Exit\n"
								);
						choice = sc.nextInt();
						switch (choice) {
						case 1:
							sc.nextLine();
							print("Enter course name: ");
							String courseName = sc.nextLine();
							print("Enter course ID: ");
							String courseId = sc.next();
							print("Enter maximum amount of student: ");
							int maxStudent = sc.nextInt();
							print("Enter current amount of student: ");
							int currentStudent = sc.nextInt();
							sc.nextLine();
							print("Enter course instructor: ");
							String instructor = sc.nextLine();
							print("Enter section number: ");
							int sectionNum = sc.nextInt();
							sc.nextLine();
							print("Enter course location: ");
							String location = sc.nextLine();

							ArrayList<String> namesList = new ArrayList<String>();
							System.out.println("Enter student full names seperated by a white space. Enter \"Done\" when your are finished");
							while (true) {
								String name = sc.nextLine();
								if (name.equalsIgnoreCase("done")) {
									break;
								}else {
									namesList.add(name);
								}
							}
							ad.creatCourse(courseName, courseId, maxStudent, currentStudent, instructor, sectionNum, location, namesList);
							break;

						case 2:
							sc.nextLine();
							print("Enter course name: ");
							String courseName1 = sc.nextLine();
							print("Enter section number: ");
							int sec1 = sc.nextInt();

							ad.deleteCourse(courseName1, sec1);
							break;

						case 3:
							/*sc.nextLine();
						print("Enter course name: ");
						String courseName2 = sc.nextLine();
						print("Enter section number: ");
						int sec2 = sc.nextInt();

						ad.editCourse(courseName2, sec2);
							 */
							sc.nextLine();
							System.out.println("Enter the course name");
							String cn = sc.nextLine();
							System.out.println("Enter the section number");
							int sec = sc.nextInt();
							for (Course c: ad.cl.arr) {
								if (c.getCourseName().replaceAll("\\s", "").equalsIgnoreCase(cn.replaceAll("\\s","")) && c.getSectionNum() == sec) {
									int editChoice = 0;
									do {
										System.out.print("What would you like to edit?\n"
												+"1. Maximum students\n"
												+"2. Current students\n"
												+"3. List of student"
												+ "4. Course Instructor\n"
												+ "5. Section number\n"
												+ "6. Location\n"
												+ "7. Finished\n");
										editChoice = sc.nextInt();
										switch(editChoice) {
										case 1:
											System.out.println("Enter new Maximum student: ");
											c.setMaxStudent(sc.nextInt());
											break;

										case 2:
											System.out.println("Enter new current student");
											c.setCurrentStudent(sc.nextInt());
											break;

										case 3:
											System.out.println("Enter student names. Enter \"Done\" when your are finished");

											ArrayList<String> names = new ArrayList<String>();
											while (true) {
												sc.nextLine();
												String name = sc.nextLine();
												if (name.equalsIgnoreCase("done")) {
													break;
												}else {
													names.add(name);
												}
											}
											c.setStudent(names);
											break;

										case 4:

											System.out.println("Enter new instructor name: ");
											sc.nextLine();
											String newIns = sc.nextLine();
											c.setInstructor(newIns);
											break;

										case 5:
											System.out.println("Enter new section number: ");
											int newsec = sc.nextInt();
											c.setSectionNum(newsec);
											break;

										case 6:
											System.out.println("Enter new location: ");
											sc.nextLine();
											String newLoc = sc.nextLine();
											c.setLocation(newLoc);
											break;

										}
										//choice = sc.nextInt();
									}while(editChoice != 7);
									break;
								}
							}
							print("Course not found!");
							break;

						case 4:
							print("Enter course ID: ");
							ad.displayCourseInfoById(sc.next());
							break;

						case 5:
							System.out.println("Enter student username: ");
							String username1 = sc.next();
							System.out.println("Enter student password: ");
							String password1 = sc.next();
							System.out.println("Enter student first name: ");
							String firstN1 = sc.next();
							System.out.println("Enter student last name: ");
							String lastN1 = sc.next();
							ad.registerStudent(username1, password1, firstN1, lastN1);
							break;

						}	
					} while (choice != 6);	
				}


				if (adminChoice == 2) {
					int choice = 0;
					do {
						print("1.View all courses\n"
								+ "2.View all courses that are FULL\n"
								+ "3.Write to a file the list of course that are Full\n"
								+ "4.View the names of the students being registered in a specific course\n"
								+ "5.View the list of courses that a given student is being registered on\n"
								+ "6.Sort by current number of students\n"
								+ "7. Exit");
						choice = sc.nextInt();
						switch(choice) {
						case 1:
							ad.viewAll();
							break;
						case 2:
							ad.viewFull();
							break;
						case 3:
							ad.writeToFileFull();
							break;

						case 4:
							print("Enter the course name: ");
							sc.nextLine();
							String courseN = sc.nextLine();
							print("Enter the section number: ");
							int sec = sc.nextInt();
							ad.viewStudentsInCourse(courseN, sec);
							break;

						case 5:
							print("Enter the first name: ");
							String firstN = sc.next();
							print("Enter the last name: ");
							String lastN = sc.next();
							ad.viewStudentCourse(firstN, lastN);
							break;

						case 6:
							ad.sortByStudentNum();

						}
					}while(choice != 7);
				}
				print("Would you like to continue the program?\n1. Yes\n2. No");
				cont = sc.nextInt();
			}
			try {
				//FileOutput Stream writes data to a file


				FileOutputStream fos1 = new FileOutputStream("CourseList.ser");

				//ObjectOutputStream writes objects to a stream (A sequence of data)
				ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
				//Writes the specific object to the OOS

				oos1.writeObject(cl);

				oos1.close();
				fos1.close();
				System.out.println("CourseList Serialization complete");
			} 
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			try {
				//FileOutput Stream writes data to a file
				FileOutputStream fos = new FileOutputStream("Admin.ser");

				//ObjectOutputStream writes objects to a stream (A sequence of data)
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				//FileOutputStream fos1 = new FileOutputStream("CourseList.ser");

				//ObjectOutputStream writes objects to a stream (A sequence of data)
				//ObjectOutputStream oos1 = new ObjectOutputStream(fos);
				//Writes the specific object to the OOS
				oos.writeObject(ad);
				//oos1.writeObject(cl);
				//Close both streams
				oos.close();
				fos.close();
				//oos1.close();
				//fos1.close();
				System.out.println("Admin Serialization complete");
			} 
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}


	}

	public static void print(String x) {
		System.out.println(x);
	}

	public static void loadStudentObject(String fileName) {
		try{
			//FileInputSystem recieves bytes from a file
			FileInputStream fis = new FileInputStream(fileName);

			//ObjectInputStream does the deserialization-- it reconstructs the data into an object
			ObjectInputStream ois = new ObjectInputStream(fis);
			//Cast as Employee. readObject will take the object from ObjectInputStream
			me = (Student)ois.readObject();
			ois.close();
			fis.close();
			System.out.println("Successfully loaded Student");
		}
		catch(IOException ioe) {
			print("No previeous Student record. Creating new Student\n");
			me = new Student("abcd", "zxcv", "Oscar", "Wan");
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return;
		}


	}
	public static void loadAdminObject(String fileName) {
		try{
			//FileInputSystem recieves bytes from a file
			FileInputStream fis = new FileInputStream(fileName);

			//ObjectInputStream does the deserialization-- it reconstructs the data into an object
			ObjectInputStream ois = new ObjectInputStream(fis);

			//Cast as Employee. readObject will take the object from ObjectInputStream
			ad = (Admin)ois.readObject();
			ois.close();
			fis.close();
			System.out.println("Successfully loaded Admin");
		}
		catch(IOException ioe) {
			//ioe.printStackTrace();
			print("No previeous Admin record. Creating new Admin\n");
			ad =  new Admin("Admin", "Admin001", "The", "Admin");
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return;
		}


	}

	public static void loadCourseList(String fileName) {
		try{
			//FileInputSystem recieves bytes from a file
			FileInputStream fis = new FileInputStream(fileName);

			//ObjectInputStream does the deserialization-- it reconstructs the data into an object
			ObjectInputStream ois = new ObjectInputStream(fis);

			//Cast as Employee. readObject will take the object from ObjectInputStream
			cl = (CourseList)ois.readObject();
			ois.close();
			fis.close();
			System.out.println("Successfully loaded CourseList!");
		}
		catch(IOException ioe) {
			//ioe.printStackTrace();
			print("No course list modified previously. loading .csv file");
			cl =  new CourseList("MyUniversityCoursesFile.csv");
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return;
		}
	}

	public static void Studentlogin() {
		for (int i = 0; i < 3; i++) {
			print("Enter username: ");
			String uname = sc.next();
			print("Enter password: ");
			String password = sc.next();
			if (!(uname.equals(me.getUsername()) && password.equals(me.getPassword()))) {
				print("Incorrect username or password!");
				print(2-i + " tries remaining");
				
			}else {
				print("Login succesful!");
				return;
			}
		}
		print("Login failed");
		System.exit(0);
	}
	
	public static void Adminlogin() {
		for (int i = 0; i < 3; i++) {
			print("Enter username: ");
			String uname = sc.next();
			print("Enter password: ");
			String password = sc.next();
			if (!(uname.equals(ad.getUsername()) && password.equals(ad.getPassword()))) {
				print("Incorrect username or password!");
				print(2-i + " tries remaining");
				
			}else {
				print("Login succesful!");
				return;
			}
		}
		print("Login failed");
		System.exit(0);
	}
}

