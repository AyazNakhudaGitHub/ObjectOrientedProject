import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.*;

public class Scheduler
{
    // In main() after you create a Registry object, create a Scheduler object and pass in the students ArrayList/TreeMap
	// If you do not want to try using a Map then uncomment
	// the line below and comment out the TreeMap line
	
	ArrayList<ArrayList<String>> schedualPrintOut;
	TreeMap<String,ActiveCourse> schedule;

	//ArrayList<Student> students;
	
	
	/**
	 * This is the constructor method.
	 * It makes a 2D array list. 
	 * It will first add the rows then add spaces for each element
	 * Then the course times will be placed at the side
	 * Then it will add the days at the top row of the 2D 
	 * ArrayList
	 * @param courses
	 */
		
	public Scheduler(TreeMap<String,ActiveCourse> courses)  //constructor method
	{
		schedule = courses;
		this.schedualPrintOut  = new ArrayList<ArrayList<String>>();

		//first add spaces to the 2D ArrayList
		for (int j = 0; j <10; j++)
		{
			schedualPrintOut.add(new ArrayList<String>()); // adding the rows
			for (int k = 0; k < 6; k++)
			{
				schedualPrintOut.get(j).add("     ");
			}
		}


		//adding the times
		int courseTime = 800;
		for(int i = 1; i < 10; i++)  
		{

			schedualPrintOut.get(i).set(0,Integer.toString(courseTime));
			courseTime +=100;

		}

		schedualPrintOut.get(0).set(0,"     ");
		schedualPrintOut.get(0).set(1," Mon ");
		schedualPrintOut.get(0).set(2," Tue ");
		schedualPrintOut.get(0).set(3," Wed ");
		schedualPrintOut.get(0).set(4," Thu  ");
		schedualPrintOut.get(0).set(5," Fri  ");
		


	}


	/**
	 * Error checking occurs here:
	 * It first checks if the new start time is the same as the old start time of the active course.
	 * Then it will check for overlapping using an idea I had: if the new start time is larger than the old one
	 * and if the start time is less than or equal to the old start time + its duration then there is a collision
	 * also if the start time is less than the old start time of the active course
	 * and if the old start time is less than or equal to the new start time + the duration then there
	 * is also going to be a collision.
	 * The method will then do the error chekcing for the day duration etc.
	 * I also included and extra if statement for bonus a)
	 * Finally the nethod sets the data if no exceptions were thrown.
	 */
	
	public void setDayAndTime(String courseCode, String day, int startTime, int duration) throws RuntimeException
	{
		// see assignment doc
		//lecturestart etc is from the the active course class
		//boolean tORf = true;
		Set<String> setOfKeys = schedule.keySet();

		
		for(String key2: setOfKeys) // this has to be done first other wise ac could be another active course
		{
			ActiveCourse ac = schedule.get(key2);

			if (ac.getlectureDay().equalsIgnoreCase(day))
			{
				
				if(startTime == ac.getlectureStart()) //if the new start time equals the "old" start time then throw an error
				{
					throw new LectureTimeCollisionException();
				}


				else if(startTime > ac.getlectureStart() && startTime <= (ac.getlectureStart()+ (100 *(ac.getlectureDuration()-1)))) 
				//check for overlapping see if the block if inside another block
				{
					throw new LectureTimeCollisionException();
				}


				else if(startTime < ac.getlectureStart() && ac.getlectureStart() <= (startTime +(100 *(duration-1))))
				{
					throw new LectureTimeCollisionException();
				}

				
				
			}

			else
			{
				continue;
			}

		}


		for(String key: setOfKeys)
		{
			if(!setOfKeys.contains(courseCode.toUpperCase()))
			{
				throw new UnknownCourseException();
			}

			else if (courseCode.equalsIgnoreCase(key))
			{
				ActiveCourse ac = schedule.get(key); //get the correct active course
				
					if (!day.equalsIgnoreCase("Mon") && !day.equalsIgnoreCase("Tue") && !day.equalsIgnoreCase("Wed") && !day.equalsIgnoreCase("Thu") && !day.equalsIgnoreCase("Fri"))
					{
						throw new InvalidDayException();
					}

					/**
					 * Note to remember:
					 * if there is one true then the whole statement is true if all the
					 * statements are false then the whole statement is false
					 * 
					 */

					else if ( startTime < 800 || startTime > 1600)
					{
						
						throw new InvalidTimeException();
					}

					else if (duration != 1 && duration != 2 && duration != 3) 
					{
						throw new InvalidDurationException();
					}
					
					else if ((startTime+ (100 * (duration-1))) >= 1700)
					{
						throw new InvalidDurationException();
					}
					// if the hours is already 3 for an active course then throw the excpetion
					else if (ac.getHours() == 3 || (ac.getHours() + duration) > 3) // this is for bonus a) 
					{
						throw new InvalidDurationException();
					}

					else if(lecTimeCollisionHelper(courseCode, day, startTime, duration))
					{
			
						throw new LectureTimeCollisionException();
					}

					else
					{
					ac.setlectureDay(day);
					ac.setlectureStart(startTime);
					ac.setlectureDuration(duration);
					addCourseToSchedualPrintOut(courseCode,day,startTime,duration);

					}
			}
		
					
		}

			
			
	}



	public boolean lecTimeCollisionHelper(String courseCode, String day, int startTime , int duration)
	{
		
		for (int i = 0 ; i < schedualPrintOut.get(0).size(); i++) //first loop through the days
		{
			
			if (schedualPrintOut.get(0).get(i).trim().replaceAll("\\s+", " ").equalsIgnoreCase(day)) // if we have the correct day... then stay in this column and start chekcing the rows
			{
				
				for (int j = 1 ; j < schedualPrintOut.size(); j++ )
				{

					if(Integer.toString(startTime).equals(schedualPrintOut.get(j).get(0)))
					{

						if (duration == 1)
						{
							
							if (!schedualPrintOut.get(j).get(i).trim().isEmpty())
							{

							return true;

							}

						}

						else if (duration == 2)
						{
							
							if (!schedualPrintOut.get(j).get(i).trim().isEmpty())
							{
								
								return true;

							}
							
							else if (!schedualPrintOut.get(j+1).get(i).trim().isEmpty())
							{
								
								return true;
							}

						}

						else if (duration == 3)
						{

							if (!schedualPrintOut.get(j).get(i).trim().isEmpty())
							{

								return true;

							}

							else if ( !schedualPrintOut.get(j+1).get(i).trim().isEmpty() )
							{
								return true;
							}

							else if ( !schedualPrintOut.get(j+2).get(i).trim().isEmpty() )
							{
								return true;
							}

						}

					}
					else
					{
						continue;
					}
				}
			}

		}
		return false;
	}
		

	/**
	 * This method will reset the data for a given active course
	 * It will then remove the course code from any element in the 
	 * 2D array that contains it.
	 * @param courseCode
	 */	
	
	public void clearSchedule(String courseCode)
	{
		Set<String> setOfKeys = schedule.keySet();

		for(String key: setOfKeys)
		{
			if (courseCode.equalsIgnoreCase(key))
			{
				ActiveCourse ac = schedule.get(key);
				ac.setlectureDay("");
				ac.setlectureStart(0);
				ac.setlectureDuration(0);
				ac.setHours(0);
				
			}

		}

		for (int i = 0; i < schedualPrintOut.size(); i++)
		{
			for (int j = 0; j < schedualPrintOut.get(i).size(); j++ )
			{
				if (schedualPrintOut.get(i).get(j).trim().replaceAll("\\s+", " ").equalsIgnoreCase(courseCode))
				{
					schedualPrintOut.get(i).set(j,"     ");
				}
			}
		}
	}
		

	/**
	 * This method will first loop through the days.
	 * If it gets the correct day it will then check the rows.
	 * If the method has the correct time it will then set the course code string to
	 * the 2D ArrayList given that it now knows the correct row and column
	 */

	public void addCourseToSchedualPrintOut(String courseCode, String day, int startTime , int duration)
	{
		
		for (int i = 0 ; i < schedualPrintOut.get(0).size(); i++) //first loop through the days
		{
			
			if (schedualPrintOut.get(0).get(i).trim().replaceAll("\\s+", " ").equalsIgnoreCase(day)) // if we have the correct day... then stay in this column and start chekcing the rows
			{
				
				for (int j = 1 ; j < schedualPrintOut.size(); j++ )
				{
					if(Integer.toString(startTime).equals(schedualPrintOut.get(j).get(0)))
					{
					
						if(duration == 3)
						{
							schedualPrintOut.get(j+2).set(i,courseCode); 
							schedualPrintOut.get(j+1).set(i,courseCode);
							schedualPrintOut.get(j).set(i,courseCode);
							
							
						}

						else if (duration == 2)
						{
							schedualPrintOut.get(j+1).set(i,courseCode);
							schedualPrintOut.get(j).set(i,courseCode);
							
						}

						else
						{
							schedualPrintOut.get(j).set(i,courseCode);
						}
						

						
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
	}


	/**
	 * This method will loop through the 2D array and print out the elements.
	 */

	public void printSchedule()
	{
		// see assignment doc
		//make a double ArrayList that holds strings make each array contain max 6 elements
		for (int i = 0; i< schedualPrintOut.size(); i++)
		{
			
			for (int j = 0; j < schedualPrintOut.get(i).size(); j++)
			{
				System.out.print(schedualPrintOut.get(i).get(j));
				if (j == schedualPrintOut.get(i).size()-1)
				{
					
						System.out.println("\n----------------------------------");

				}
			}
		}
	}
	
}

