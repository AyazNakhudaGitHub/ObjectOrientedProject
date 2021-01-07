//Ayaz Nakhuda

import java.io.*;

import java.util.*;

//import apple.laf.JRSUIUtils.Tree; -----> THIS LINE GIVES ME AN ERROR WHILE TESTING IN TERMINAL



public class Registry
{
	
	/**
	 * for the students tree map, key is id, element is the Student object
	 * for the courses tree map, key is course code, element is the Active Course object
	 */

   private TreeMap<String,Student> students = new TreeMap<String,Student>(); 
   public TreeMap<String,ActiveCourse> courses = new TreeMap<String,ActiveCourse>();  
																				

   public Registry() throws FileNotFoundException
   {
			File infile = new File("students.txt");
			Scanner scanner = new Scanner(infile); //Scanner throws a FileNotFoundException

			/**
			 * we are going to handle the bad file format exception here,
			 * the exception however if being thrown from the read file method
			 */

			try
			{
				readFile(scanner);
			}
			
			catch (BadFileFormatException exception) //we are going to catch the exception 
			{
				
				System.out.println("Bad file format");
				System.exit(0);
			}

			ArrayList<Student> list = new ArrayList<Student>();

			//Add some active courses

			
			String courseName = "Machine Learning";
			String courseCode = "CS1354";
			String descr = "Learn how to make machine learning algorithms.";
			String format = "3Lec 2Lab";
			ActiveCourse ac = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
			courses.put("CS1354",ac); 

			/**
			By calling the addCourse method, the students get added 
			to the list which is an instance of an ActiveCourse object. The 
			course will be added to the students active course ArrayList
			Note that the same students added by default from assignment #1
			will be added to their courses in this constructor method.
			As well, the students added to courses in this constructor method
			are students who have already been registred by 
			the readFile() method.
			*/

			addCourse("52891","CS1354");
			addCourse("59863","CS1354");
			addCourse("76548","CS1354");


			
			list.clear();
			courseName = "Introduction To Computer Science";
			courseCode = "CS1015";
			descr = "Learn how to write programs using the Python programming language.";
			format = "3Lec";
			ac = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
			courses.put("CS1015",ac);

			addCourse("34562","CS1015"); 
			addCourse("25347","CS1015");
			addCourse("46532","CS1015");

			
			
			list.clear();
			courseName = "Data Science";
			courseCode = "CS2013";
			descr = "Learn how analyze data and compute statistics.";
			format = "3Lec 2Lab";
			ac = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
			courses.put("CS2013",ac);

			addCourse("34562","CS2013"); 
			addCourse("38467","CS2013");
			addCourse("57643","CS2013");
			addCourse("46532","CS2013");

			 
			 list.clear();
			 courseName = "Artificial Intelligence";
			 courseCode = "CS9037";
			 descr = "Learn about Artificial Intelligence.";
			 format = "3Lec 1Lab";
			 ac = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
			 courses.put("CS9037",ac);
			
			 
			 list.clear();
			 courseName = "Introduction to Stochastic Processes";
			 courseCode = "MTH5107";
			 descr = "Learn about Stochastic Processes.";
			 format = "3Lec 1Lab";
			 ac = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
			 courses.put("MTH5107",ac);



	   
   }
   

   /**
	* This method reads the student names and IDs
	* This mehtod also throws a BadFileFormatException
	* it will throw the exception if the name contains numbers,
	* if the ID contains letters, if the student has no ID or
	* if there is no name.
	* This method puts the student and their ID into the students tree map
	* @param scanner
	* @throws BadFileFormatException
    */

   public void readFile(Scanner scanner) throws BadFileFormatException 
   {

	//in this mehtod we have to throw new BadDataException

		//see bottom of file for BadDataEception
		try 
		{
			while (scanner.hasNext()) 
			{
				
					String studentName = scanner.next();
					String id = scanner.next();
					
					if (isNumeric(id) == false || isStringOnlyAlphabet(studentName) == false || id.isEmpty() || studentName.isEmpty()) //if id contains letters, if studetn name contains numbers and then check the key and the element if they are empty 
					{
						throw new BadFileFormatException();

					}
					Student s = new Student(studentName,id);
					students.put(id,s);
		

			}
		}
		catch (NoSuchElementException e)
		{
			throw new BadFileFormatException();
		}
		
		
   }

   
   /**
	* This method checks if the student's name has a number in it
	* @param str
	* @return
    */

	public static boolean isStringOnlyAlphabet(String str) 
	{ 
	// write method to check if string str contains only alphabetic characters 
		for (int i = 0; i < str.length(); i++)
		{
			
			if (Character.isDigit(str.charAt(i)))
			{
				
				
				return false;

			}
			
		}
		return true;
	} 


	/**
	* This method checks if the student's ID has a letter in it
	* @param str
	* @return
	*/
	
	public static boolean isNumeric(String str)
	{
		for (int i = 0; i < str.length(); i++)
		{
	
			if (Character.isLetter(str.charAt(i)))
			{
				
				return false;
					
			}
	
		}
		return true;
	}
	
	   
   /**
	* This method will add a new student to the registry (students TreeMap above). 
	* To do this, it needs to see if the student is already in the students TreeMap.
	* This method also utilizes the equals method found in the class Student.
	* @param name
	* @param id
	* @return true is returned if the student has been added, false otherwise.
	*/
	
   public boolean addNewStudent(String name, String id) 
   {
	

	   Set<String> setOfKeys = students.keySet();
	   Iterator<String> itr = setOfKeys.iterator();
	   while(itr.hasNext())
	   {
		   //this should not add people with the same id as someone who is already in the list
		   if (id.equals(itr.next()))
		   {
			   System.out.println("Student " + name + " already registered");
			   return false;
		   }
		   
	   }
	   Student s = new Student(name,id);
	   students.put(id,s);
	   System.out.println(name + " has been registered");
	   return true;
	

   }
  

   /**
	* This method removes a student from registry 
	* @param studentId
	* @return returns true if the student has been found in the students tree map and 
	 removes the student from the students tree map, false otherwise.
	*/

   public boolean removeStudent(String studentId)
   {

	   Set<String> setOfKeys = students.keySet();
	   Iterator<String> itr = setOfKeys.iterator(); //itr is a pointer/refrence variable
	   while(itr.hasNext())
	   {
		   
		   if (studentId.equals(itr.next()))
		   {
			   
			   itr.remove(); //Iterator.remove() will remove the last element that is returned by the itr.next() call
			   return true;
		   }
		   
	   }
	   
	   return false;

   }
   
  
   /**
	*  This method goes through the keys of the students TreeMap the gets their name. 
	*  It will then print out the student's ID and name.
	*/
	
   public void printAllStudents()
   {
	   Set<String> setOfKeys = students.keySet();
	
	  for (String key: setOfKeys )
	  {
		  String name = students.get(key).getName();
		  System.out.println("Name " + name + " ID " + key );
	  }
   }
   
   
   /**
	* Given a studentId and a course code, add student to the active course
	* @param studentId
	* @param courseCode
	* First this method goes throught the key set of the students tree map
	* It then sees if it has the correct student
	* then it checks if the course is already in the students'
	* credit course array list.
	* Then it checks if the student object is in the active 
	* course's array list of student objects.
	* If the student object is not in this list then the course
	* will be added to the student's credit course array list
	* and the student will be added to the active course's list
	* of student objects
	*/

   public void addCourse(String studentId, String courseCode)
   {
		Set<String> setOfKeys = students.keySet();
		
		for (String key: setOfKeys)
		{
			if (studentId.equalsIgnoreCase(students.get(key).getId())) 
			{
				
				Student s = students.get(key);
				if (! s.courses.contains(courses.get(courseCode))) 
				{	

					if(! courses.get(courseCode.toUpperCase()).getlist().contains(s))
					{
						ActiveCourse ac = courses.get(courseCode.toUpperCase());
						s.addCourse(ac.getName(),ac.getCode(),ac.getDesc(),ac.getFormat(),ac.getSemester(), 0.0);
						ac.addToList(s);
	
					}
				}
				
			}

		}
   }

   
   /**
	* Given a studentId and a course code, drop student from the active course
	* Essentially the same procedure as the addCourse method above but this time the method
	* removes course from the CreditCourse arrayList (only if the CreditCourse is set active) of the student 
	* and it removes the student from the active course's arrayList of Students
	* the line: ac.getlist().remove(s); calls a method getlist() in the class ActiveCourse
	* which returns the list of student objects and then it calls the 
	* remove() method which removes the specified student
	* the line: s.removeActiveCourse(courseCode); removes the course from 
	* the student's creditCourse arrayList, this method can be found in the Student class.
	* @param studentId
	* @param courseCode
	*/
	
   public void dropCourse(String studentId, String courseCode)
   {

	Set<String> setOfKeys = students.keySet();
		
		for (String key: setOfKeys)
		{
			if (studentId.equalsIgnoreCase(students.get(key).getId()))  
			{
			
				Student s = students.get(key);
				
				if(courses.get(courseCode.toUpperCase()).getlist().contains(s))
				{
					ActiveCourse ac = courses.get(courseCode.toUpperCase());
					s.removeActiveCourse(courseCode);
					ac.getlist().remove(s);
					

					
					
				}
			
			}

		}
   }

	
   /**
	* Print all active courses
	* This method prints out all active courses
	* Note that this method doesn't print out
	* active courses for a specified student
	* the getDescription method can be found in the ActiveCourse class.
	*/
	
   public void printActiveCourses()
   {

		Set<String> setOfKeys = courses.keySet();

		for(String key: setOfKeys)
		{
			ActiveCourse ac = courses.get(key);
			System.out.println(ac.getDescription());
		}
	
   }
   

   /**
	* Print the list of students in an active course
	* loop through the set of keys and check if you have the right 
	* one (by comparing the course code to the key value)
	* the line: ac.getlistAt(i); calls a
	* method which is found in the ActiveCourse class that retrieves a
	* specified element from the list of student objects of an active course
	* @param courseCode
	*/
	
   public void printClassList(String courseCode)
   {
	   
	Set<String> setOfKeys = courses.keySet();

	for(String key: setOfKeys)
	{
		if (courseCode.equalsIgnoreCase(key))
		{
			ActiveCourse ac = courses.get(key);
			for (int i =0 ; i < ac.getlist().size(); i++)
			{
				System.out.println(ac.getlistAt(i));
			}
			
		}
	
	}

   }
	

   /**
	* Given a course code, find course and sort class list by student name
	* First this method goes through the set of keys for the courses tree map
	* then the method checks if it has the correct course
	* then the method calls the sortByName method found in the ActiveCourse class
	* which takes in an ArrayList, namely the ArrayList of students
	* @param courseCode
	*/
	
   public void sortCourseByName(String courseCode)
   {

		Set<String> setOfKeys = courses.keySet();

		for(String key: setOfKeys)
		{
			if (courseCode.equalsIgnoreCase(key))
			{
				ActiveCourse ac = courses.get(key);
				ac.sortByName(ac.getlist());
			}
			
			
		}
	
   }


   /**
	* Given a course code, find course and sort class list by student ID
	* First this method goes through the set of keys for the courses tree map
	* then the method checks if it has the correct course
	* then the method calls the sortById method found in the ActiveCourse class
	* which takes in an ArrayList, namely the arrayList of students.
	* @param courseCode
	*/
	
   public void sortCourseById(String courseCode)
   {

	Set<String> setOfKeys = courses.keySet();

		for(String key: setOfKeys)
		{
			if (courseCode.equalsIgnoreCase(key))
			{
				ActiveCourse ac = courses.get(key);
				ac.sortById(ac.getlist());
			}
			
			
		}
 	}
   
   
   /**
	* Given a course code, find course and print student names and grades.
	* This method finds the correct active course then it calls the
	* printGrades() method found in the ActiveCourse class
	* @param courseCode
	*/
	
   public void printGrades(String courseCode)
   {
		Set<String> setOfKeys = courses.keySet();

		for(String key: setOfKeys)
		{
			if (courseCode.equalsIgnoreCase(key))
			{
				ActiveCourse ac = courses.get(key);
				ac.printGrades(courseCode);
			}
					
		}

   }
		

   /**
	* print all credit courses of a student
	* First this method checks if it has the correct student
	* If it does have the correct student, then
	* it will call the printActiveCourses() method which is 
	* found in the Student class. To print out the information of the course/s 
	* the printActiveCourses() method calls the getDescription()
	* method of the Course class only if the creditCourse is set active.
	* @param studentId
    */

   public void printCreditCourses(String studentId)
   {

		Set<String> setOfKeys = students.keySet();

		for(String key: setOfKeys)
		{
			if (studentId.equalsIgnoreCase(key))
			{

				Student s = students.get(key);
				for(int i = 0; i< s.courses.size(); i++)
				{
					s.printActiveCourses(s.courses.get(i));
				}
				
			}
			
			
		}

   }


   /**
	* Given a studentId, print all completed courses and grades of student
	* First this method checks to see if it has the correct student
	* if the method has the correct student then it will call
	* the printTranscript() method found in the Student class
	* where it will only print out the grades for inactive (completed) courses
	* @param studentId
	*/
	
   public void printStudentTranscript(String studentId)
   {

		Set<String> setOfKeys = students.keySet();

		for(String key: setOfKeys)
		{
			if (studentId.equalsIgnoreCase(key))
			{
				Student s = students.get(key);
				s.printTranscript();
			}
					
		}
   }
 
   
   /**
	* Given a course code, student id and numeric grade set the final grade of the student.
	* First it finds the correct student.
	* Then it will find the correct credit course
	* and check if the credit course is active.
	* If both of these conditions are satisfied, it will call 
	* the setGrade(grade) method found the CreditCourse class.
	* Then it will set the credit course inactive for the student.
	* @param courseCode
	* @param studentId
	* @param grade
	*/
	
   public void setFinalGrade(String courseCode, String studentId, double grade)
   {
	
		//Set<String> setOfKeys = courses.keySet();
		Set<String> setOfKeys2 = students.keySet();

		for (String key2: setOfKeys2)
		{
			if (studentId.equalsIgnoreCase(key2))
			{
				Student s = students.get(key2);
				for(int i = 0; i< s.courses.size(); i++)
				{
					CreditCourse cc = s.courses.get(i);

					if(cc.getCode().equalsIgnoreCase(courseCode) && cc.getActive() == true)
					{
						cc.setGrade(grade);
						cc.setInactive();
					}
				}
			}
		}
			
			
	}

   
/**
 * This is a class for the BadFileFormatException
 */
  
}
class BadFileFormatException extends IOException
{
	public BadFileFormatException() {}

}