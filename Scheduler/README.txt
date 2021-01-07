DOCUMENTATION:
-------------------------------------


	The purpose of this program is to allow users to register students to classes, display students in certain classes, make a schedule for a semester, and add courses for a semester. Furthermore, this program serves as practise for object oriented programming and error handling for myself. Also, please see the pdf file included to see the structure of the project.




Some commands that you can use are:
-------------------------------------

pac -> This command prints active courses.

psc 59863 -> This command prints out all credit courses of a student. 


sch cs1354 mon 800 1  -> This command schedules a lecture on Monday at 8:00 am for 1 hour.


psch -> This command prints out your schedule.


l -> This command lists the students who are registered.


q -> This command ends the program.


reg HarrisonFord 5221 -> This command registers a student using their name and ID.


del 12345 -> This command deletes a student from the registry using a student's ID.


addc 12345 CS1354 -> This command adds a student with the ID 12345 to the course CS1354.


dropc 12345 CS1354 -> This command will remove the student from a specified course using a student's ID and a course code.


pcl CS1354 -> This command will print out the list of students for a course. The specified course here is CS1354 - Machine Learning.


pgr CS1354 -> This command will print out the ID's and grades of students in an active course.


sfg CS1354 12345 90 -> This command will set the final grade for an active course of a student. The command line's structure is: command, course code, student ID, student's final grade. Setting a final grade will remove the course from the student's credit course list.


scn CS1354 -> This command sorts the list of students in a course by name.


sci CS1354 -> This command sorts the list of students in a course by ID.


        


Examples of error checking:
-------------------------------------
1.

	sch mth5107 fri 1100 3

	sch cs1015 fri 900 3

	Expected output: "Lecture time collision" 


2.

	sch mth5107 fri 1100 3

	sch mth5107 wed 1200 2

	Expected output: "Invalid lecture duration" 


3. 

	The program will output "file not found" if there is not file named
	"students" in the same directory as the file with the main method.


4.
	sch com5109 wed 1100 1

	Expected output: "Unknown course: com5109" 


5.
	sch cs1354 mnn 900 3

	Expected output: "Invalid lecture day"

6.
	sch cs1015 mon 1000 5
	
	Expected output: "Invalid lecture duration"










   