//Ayaz Nakhuda 
import java.util.ArrayList;

// Make class Student implement the Comparable interface
// Two student objects should be compared by their name
public class Student 
{
  private String name;
  private String id;
  public  ArrayList<CreditCourse> courses;
  
  
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses   = new ArrayList<CreditCourse>();
  }
  

  public String getId()
  {
	  return id;
  }

  
  public String getName()
  {
	  return name;
  }

  
  // add a credit course to list of courses for this student
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
    
    // create a CreditCourse object
    // set course active
    // add to courses array list
    
    CreditCourse credc = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
    credc.setActive();
    courses.add(credc);

  }
  
  
  /**
   * Prints a student transcript.
   * Prints all completed (i.e. non active) courses 
   * for this student (course code, course name, semester, letter grade).
   * See class CreditCourse for useful methods.
   * This method finds an inactive course by 
   * using the getActive() method of the CreditCourse class.
   * If the course is inactive, the displayGrade() method
   * of the CreditCourse class will be called.
   */

  public void printTranscript()
  {
    for(int i = 0; i< courses.size(); i++)
    {
      if (courses.get(i).getActive() == false)
      {
        
        courses.get(i).displayGrade();
      }
      else
      {
        continue;
      }
    }
  }
  

  /**
   * Prints all active courses this student is enrolled in
   * see variable active in class CreditCourse.
   * 
   * @param cc
   */

  public void printActiveCourses(CreditCourse cc)
  {

   if (cc.getActive() == true)
   {
      System.out.println(cc.getDescription());
   }

  }


  /**
   * Drop a course (given by courseCode)
   * Find the credit course in courses arraylist above and remove it
   * only remove it if it is an active course
   * @param courseCode
   */

  public void removeActiveCourse(String courseCode)  
  {
    for (int i = 0; i < courses.size(); i++)
    {
      if (courses.get(i).getCode().equalsIgnoreCase(courseCode) && courses.get(i).getActive() == true)
      {
        courses.remove(courses.get(i));
      }
      else
      {
        continue;
      }
    }
  }

  
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  

  /** 
   * Override equals method inherited from superclass Object.
   * If student names are equal *and* student ids are equal (of "this" student
   * and "other" student) then return true
   * otherwise return false
   * Hint: you will need to cast other parameter to a local Student reference variable
   */

  public boolean equals(Object other)
  {
    Student st = (Student) other;
    if (this.id.equals(st.id) && this.name.equals(st.name))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
}
