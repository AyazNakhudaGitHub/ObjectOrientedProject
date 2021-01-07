//Ayaz Nakhuda 
public class CreditCourse extends Course
{
	// add a constructor method with appropriate parameters
	// should call the super class constructor
	private double grade;
	private boolean active;
	private String semester;
	public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade)
	{
		// add code
		super(name,code,descr,fmt);
		this.grade = grade;
		this.semester = semester;

	}


	public void setGrade(double g)
	{
		grade = g;
	}


	public double getGrade()
	{
	  return grade;
	}
	

	public boolean getActive()
	{
		return active;
	}
	

	public void setActive()
	{
		active = true;
	}
	

	public void setInactive()
	{
		active = false;
	}
	
	
	public void displayGrade()
	{
		// Change line below and print out info about this course plus which semester and the grade achieved
		// make use of inherited method in super class
		
		System.out.println(super.getCode() + " " + super.getName() + " "+semester + " "  + convertNumericGrade(grade));
	}
	
}