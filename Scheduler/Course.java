//Ayaz Nakhuda 
public class Course 
{
	private String code;
	private String name;
	private String description;
	private String format;
	   
	public Course()
	{
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
	}
	   
	public Course(String name, String code, String descr, String fmt)
	{
	  this.code        = code;
	  this.name        = name;
	  this.description = descr;
	  this.format      = fmt;
	}
	   
	public String getCode()
	{
	   return code;
	}
	   
	public String getName()
	{
	  return name;
	}
	   
	public String getFormat()
	{
	  return format;
	}

	public String getDesc() // I have to add this method because in the addCourse method in Registy.java, 
							// I have to put something in for the description parameter.
							// I cannot call the method getDescription() otherwise 
							// I will have a description that contains the course code "-" and name.

	{
		return description;
	}
	   
	public String getDescription()
	{

	  	return code +" - " + name + "\n" + description + "\n" + format;
	}
	
	 public String getInfo()
	 {
	   return code +" - " + name;
	 }
	 
	 // static method to convert numeric score to letter grade string 
	 // e.g. 91 --> "A+"
	 public static String convertNumericGrade(double grade)
	 {
		 // fill in code

		
		 if ( grade >= 90.0 && grade <= 100.0)
		 {
			 return ("A+");
		 }
 
		 else if (grade >= 85.0 && grade <= 89.0)
		 {
			 return ("A");
		 }
 
		 else if (grade >= 80.0 && grade <= 84.0)
		 {
			 return ("A-");
		 }
 
 
 
		 else if (grade >= 77.0 && grade <= 79.0)
		 {
			 return ("B+");
		 }
 
		 else if (grade >= 73.0 && grade <= 76.0)
		 {
			 return ("B");
		 }
 
		 else if (grade >= 70.0 && grade <= 72.0)
		 {
			 return ("B-");
		 }
 
 
 
		 else if (grade >= 67.0 && grade <= 69.0)
		 {
			 return ("C+");
		 }
		 else if (grade >= 63.0 && grade <= 66.0)
		 {
			 return ("C");
		 }
		 else if (grade >= 60.0 && grade <= 62.0)
		 {
			 return ("C-");
		 }
 
 
 
		 else if (grade >= 57.0 && grade <= 59.0)
		 {
			 return ("D+");
		 }
		 else if (grade >= 53.0 && grade <= 56.0)
		 {
			 return ("D");
		 }
		 else if (grade >= 50.0 && grade <= 52.0)
		 {
			 return ("D-");
		 }
		 
 
		 else 
		 {
			 
			 return ( "F");
		 }
		 
	 }
	 
}
