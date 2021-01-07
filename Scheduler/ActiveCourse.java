//Ayaz Nakhuda

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.lang.model.util.ElementScanner6;

// Active Course
 
public class ActiveCourse extends Course // // extends means to make a subclass implemetnns means you are just using things from another class just to see if this works this class can now use the instance variable of class Course
{
	private ArrayList<Student> students; 
   private String             semester;
   private int lectureStart;
   private int lectureDuration;
   private String lectureDay;
   private int hours;
	
	   
   // Add a constructor method with appropriate parameters
   // should call super class constructor to initialize inherited variables
   // make sure to *copy* students array list being passed in into new arraylist of students
   // see class Registry to see how an ActiveCourse object is created and used


   public ActiveCourse(String name, String code, String descr, String fmt,String semester, ArrayList<Student> students)
   {
      
      //have to make list of students
      //remember that you are dealing with array list thus you can't just say this.students = students


     super(name,code,descr,fmt);   //call the super class's constructor method that takes in these given parameters
     this.students = (ArrayList<Student>)students.clone();
     this.semester = semester;
     this.lectureStart = 0;
     this.lectureDuration = 0;
     this.lectureDay = "";
     this.hours = 0;

   }


   /**
    * This method sets the lecture start time
    * @param lecStrt
    */

   public void setlectureStart(int lecStrt)
   {
      lectureStart = lecStrt;

   }


   /**
    * This method sets the lecture duration
    * @param lecDur
    */

   public void setlectureDuration(int lecDur)
   {
      hours += lecDur;
      lectureDuration = lecDur;
   }


   /**
    * this method gets the hours taken by a course
    * @return
    */

   public int getHours()
   {
      return hours;
   }

   public void setHours(int i)
   {
      hours = i;
   }


   /**
    * This method sets the lecture day
    * @param s
    */

   public void setlectureDay(String s)
   {
      lectureDay = s;
   }


   /**
    * This methos gets the lecture start time
    * @return
    */

   public int getlectureStart()
   {
      return lectureStart;
   }


   /**
    * this method gets the lecture duration
    * @return
    */

   public int getlectureDuration()
   {
      return lectureDuration;
   }

   
   /**
    * this method gets the lecture day
    * @return
    */

   public String getlectureDay()
   {
      return lectureDay;
   }

   /**
    * this methods gets the semester
    * @return
    */

   public String getSemester()
   {
	   return semester;
   }


   /**
    * This is a method made by myself that returns the arrayList of students when called.
    * @return ArrayList of Students
    */

   public ArrayList getlist() 
   {
      return students;
   }


   /**
    * This is a method made by myself that will return a student from the students arrayList of an active course at a specified index
    * @param j The method will retrieve the value in the students ArrayList at the index j.
    * @return  The Student at index j in the students arrayList.
    */

   public Student getlistAt(int j)
   {
      return students.get(j);

   }


   /**
    * This is a method made by myself where it removes the student at the specified index
    * the student from the students arrayList at a specified index/
    * @param j
    */

   public void removelistAt(int j) 
   {
      students.remove(j);
   }


   /**
    * This method will add the given student to the students arrayList
    * @param s
    */
   
   public void addToList(Student s) 
   {
      students.add(s);
      
   }
   

   /**
    * This method will go through the students arryaList and 
    * print out all the students (name and student id)
    */

   public void printClassList()
   {
   
      for (int j = 0; j < students.size(); j++)
      {
         System.out.println(students.get(j));
      }

   }
   

   /**
    * Prints the grade of each student in this course (along with name and student id)
    * This method will loop through the list of students.
    * This method calls the getGrade method which is found below this method.
    * @param courseCode
    */

   public void printGrades(String courseCode)
   {
         for (int k = 0 ; k < students.size();k++)
         {
            System.out.println(students.get(k).getId()+ " " + students.get(k).getName()+ " Grade " + getGrade(students.get(k).getId()));
         
         }
         
      
   }
   

   /**
    * Returns the numeric grade for a student in a course found in the active course ArrayList which is found in registry.

    * If student not found in course, return 0.
    * First the method finds the correct student.
    * Then the method will loop through the students credit courses arrayList
    * and get the correct credit course by comapring the course codes.
    * If the corect credit course has been found, the method will call
    * the getGrade() method of the credit course class

    Notes given by default :
     // Search the student's list of credit courses to find the course code that matches this active course
	  // see class Student method getGrade() 
     // return the grade stored in the credit course object

    * @param studentId
    * @return  double; the numeric grade for this course for this student.
    */
  
   public double getGrade(String studentId)
   {

     for (int i = 0; i < students.size() ; i ++)
     {

        if (students.get(i).getId().equals(studentId))
        {
            Student s = students.get(i);

            for (int j = 0; j < s.courses.size(); j++)
            {
               if (s.courses.get(j).getCode().equalsIgnoreCase(this.getCode()))
               {
                  return s.courses.get(j).getGrade();
               }
               else
               {
                  continue;
               }
            }
        }

        else
        {
           continue;
        }

     }
	  return 0; 
   }
   
   /**
    * Notes given by default: 
    * Returns a String containing the course information 
    * as well as the semester and the number of students 
    * enrolled in the course. Must override method in the superclass 
    * Course and use super class method getDescription().

    * We are overriding what the getDescription() method found in the super class.
    * But, we are still using the getDescription() call in the class Course.
    */

   @Override
   public String getDescription()
   {
      
      
      return super.getDescription() + " "+ this.getSemester() +" Enrolement: "+ students.size() +"\n";
      
   }
    
   
   
   /**
    * Sort the students in the course by name using the 
    * Collections.sort() method with appropriate arguments.
    * Make use of a private Comparator class below
    * @param st
    */
   
   public void sortByName(ArrayList<Student> st)
   {

      Collections.sort(st, new NameComparator());
       
   }

   
   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student name

   private class NameComparator implements Comparator<Student>
   {

      /**
       * This method returns a negative integer, 
       * a positive integer or 0 depending
       * on the placement of 
       * student 1 and student 2 in the arrayList.
       */
      
      public int compare(Student s1, Student s2)
      {

         //note compareTo() is a method of the comparable interface while compare() is a mehtod of the comparator classs1.getName()
         return s1.getName().compareTo(s2.getName());


      }
      
   }

   
   /**
    * Sort the students in the course by student id using the 
    * Collections.sort() method with appropriate arguments Make 
    * use of a private Comparator class below
    * @param st
    */
   
   public void sortById(ArrayList<Student> st)
   {
 	  Collections.sort(st, new IdComparator());
   }
   

   // Fill in the class so that this class implement the Comparator interface
   // This class is used to compare two Student objects based on student id

   private class IdComparator implements Comparator<Student> 
   {

      /**
       * This method returns a negative integer, 
       * a positive integer or 0 depending
       * on the placement of 
       * student 1 and student 2 in the arrayList.
       */
      
      public int compare(Student s1, Student s2)
      {
         return Integer.parseInt(s1.getId()) - Integer.parseInt(s2.getId());
      }

   }
}
