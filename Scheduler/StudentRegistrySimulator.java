//Ayaz Nakhuda 
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File ;
import java.io.FileNotFoundException ;
import java.io.IOException;


public class StudentRegistrySimulator 
{
  public static void main(String[] args)
  {

		try 
		{ 
			
			Registry registry = new Registry(); // calls the constructor method Registry and the Registry construcotr method will throw an exception so the caller has to handle it.
			Scheduler scheduler = new Scheduler(registry.courses);

			Scanner scanner = new Scanner(System.in);
			System.out.print(">");
			
			while (scanner.hasNextLine())
			{
				String inputLine = scanner.nextLine();
				if (inputLine == null || inputLine.equals("")) continue;
				
				Scanner commandLine = new Scanner(inputLine);
				String command = commandLine.next();
				
				if (command == null || command.equals("")) continue;
				
				else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
				{
					registry.printAllStudents();
				}
				else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
					return;
				
				else if (command.equalsIgnoreCase("REG"))
				{
					
					// register a new student in registry
					// get name and student id string 
					String n = commandLine.next(); 											//This is going to be the name
					String i = commandLine.next();											//Integer.toString(input.nextInt()); //this is going to be the id  dont use next int bec is user puts in a letter you will get mismatched error

					
					if (isStringOnlyAlphabet(n) == true && isNumeric(i) == true)
					{
						
						registry.addNewStudent(n,i);
					}
				

					// e.g. reg BillGates 3459234
					// Checks:
					//  ensure name is all alphabetic characters
					//  ensure id string is all numeric characters
					
				}
				else if (command.equalsIgnoreCase("DEL"))
				{
					// delete a student from registry
					// get student id
					// ensure numeric
					// remove student from registry
					String n = commandLine.next();
					if(isNumeric(n) == true)
					{
						registry.removeStudent(n);
					}

				}
				
				else if (command.equalsIgnoreCase("ADDC"))
				{
					// add a student to an active course
					// get student id and course code strings
					// add student to course (see class Registry)
					String id = commandLine.next(); // id
					String cc = commandLine.next(); //course code
					registry.addCourse(id,cc);
				}
				else if (command.equalsIgnoreCase("DROPC"))
				{
					// get student id and course code strings
					// drop student from course (see class Registry)
					
					String id = commandLine.next(); // id
					String cc = commandLine.next(); //course code
					registry.dropCourse(id, cc);
				}
				else if (command.equalsIgnoreCase("PAC"))
				{
					// print all active courses
					registry.printActiveCourses();
				}		  
				else if (command.equalsIgnoreCase("PCL"))
				{
					// get course code string
					// print class list (i.e. students) for this course
					//Scanner input = new Scanner(System.in);
					String cc = commandLine.next(); 
					registry.printClassList(cc);
					
				}
				else if (command.equalsIgnoreCase("PGR"))
				{
					// get course code string
					// print name, id and grade of all students in active course
					
					String cc = commandLine.next();
					registry.printGrades(cc);
					
				}
				else if (command.equalsIgnoreCase("PSC"))
				{
					// get student id string
					// print all credit courses of student
					
					String id = commandLine.next(); 
					registry.printCreditCourses(id);
					
					
				}
				else if (command.equalsIgnoreCase("PST"))
				{
					// get student id string
					// print student transcript
					String id = commandLine.next();
					registry.printStudentTranscript(id);

					
				}
				else if (command.equalsIgnoreCase("SFG"))
				{
					// set final grade of student
					// get course code, student id, numeric grade
					// use registry to set final grade of this student (see class Registry)
					
					String cc = commandLine.next(); 
					String id = commandLine.next();
					double g = commandLine.nextDouble();
					registry.setFinalGrade(cc,id,g);
				}
				else if (command.equalsIgnoreCase("SCN"))
				{
					// get course code
					// sort list of students in course by name (i.e. alphabetically)
					// see class Registry
					String cc = commandLine.next();
					registry.sortCourseByName(cc);			  
					
				}

				else if (command.equalsIgnoreCase("SCH"))
				{
					String courseCode = commandLine.next(); 
					String day = commandLine.next();
					int startTime = commandLine.nextInt();
					int duration = commandLine.nextInt();

					try
					{
						scheduler.setDayAndTime(courseCode, day, startTime, duration); //have to use scheduler not Schedual because the method is an instance of the class that's associated with an object
					}
					
					catch(UnknownCourseException exception)
					{
						System.out.println("Unkown course: " + courseCode);
					}

					catch(InvalidDayException exception)
					{
						System.out.println("Invalid lecture day");
					}

					catch(InvalidTimeException exception)
					{
						System.out.println("Invalid lecture start time");
					}

					catch(InvalidDurationException exception)
					{
						System.out.println("Invalid lecture duration");
					}

					catch(LectureTimeCollisionException exception)
					{
						System.out.println("Lecture time collision");
					}
					
				}

				else if (command.equalsIgnoreCase("CSCH"))
				{
					String cc = commandLine.next(); 
					scheduler.clearSchedule(cc);
				}

				else if (command.equalsIgnoreCase("PSCH"))
				{
					
					scheduler.printSchedule();
				}

				else if (command.equalsIgnoreCase("SCI"))
				{
					// get course code
					// sort list of students in course by student id
					// see class Registry
					//Scanner input = new Scanner(System.in);
					String cc = commandLine.next(); 
					registry.sortCourseById(cc);	

				}
				System.out.print("\n>");
			}


		}
		catch (FileNotFoundException exception)
		{
		
		  System.out.println("file not found");
		  System.exit(0);
  
		}
		
		
		

	}
  
	  private static boolean isStringOnlyAlphabet(String str) 
	  { 
	      // write method to check if string str contains only alphabetic characters 
		  for (int i = 0; i < str.length(); i++)
		  {
			  
			  if (Character.isDigit(str.charAt(i)))
			  {
				  
				  System.out.println("Invalid characters in Name "+  str);
				  return false;
				  
				  
				  
			  }
			  
		  }
		  return true;
	  } 
	  
	  public static boolean isNumeric(String str)
	  {
	      // write method to check if string str contains only numeric characters
		  for (int i = 0; i < str.length(); i++)
		  {
	
			  if (Character.isLetter(str.charAt(i)))
			  {
				  System.out.println("Invalid characters in ID "+  str);
				  return false;
				 
			  }
	
		  }
		  return true;
	  }
  
}


/**
 * The classes are just for the different types
 * RunTimeExceptions that are thrown
 */

class UnknownCourseException  extends RuntimeException
{
	public UnknownCourseException() {}

}


class InvalidDayException extends RuntimeException
{
	public InvalidDayException() {}

}


class InvalidTimeException extends RuntimeException
{
	public InvalidTimeException() {}

}


class InvalidDurationException extends RuntimeException
{
	public InvalidDurationException() {}

}


class LectureTimeCollisionException extends RuntimeException
{
	public LectureTimeCollisionException() {}

}