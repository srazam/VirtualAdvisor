package hacknc_stephaniesarambo;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class UI {
	
	public static void browseCourses(String semester, Major majCourses, GenEds genCourses)
	{
		System.out.println("The courses in your major available in the " + semester + " semester are:");
		majCourses.display(semester);
		System.out.println();
		System.out.println("The general education courses available in the " + semester + " semester are:");
		genCourses.display(semester);
	}
	
	public static void planAhead(String semester, int creditHours, String[] interests, Major majCourse, GenEds genCourse)
	{
		int mCount = majCourse.getCount();
		int gCount = genCourse.getCount();
		int credit = 0;
		int creditLeft = 0;
		Node temp = null;
		
		//For major courses (will only take 3 classes total)
		for(int k = 0; k < 3; k++)
		{
			for(int i = 1; i < mCount; i++)
			{
				temp = majCourse.indexOf(i);
				//If the current semester type the node indexed is equal to the current semester the student enters or is both then it's okay to use
				if(semester == temp.semesterType || "both" == temp.semesterType);
				{
					//If the prereqs aren't present for the next course in the linked list, then take that course and subtract class credits from creditHours
					for(int j = 0; j < temp.preReq.length; j++)
					{
						if(!majCourse.contains(temp.preReq, temp.preReq[j]))
						{
							System.out.println(temp.courseName);
							credit += temp.creditHours;
						}
					}
				}
			}
		}
		
		creditLeft = creditHours - credit;
		if(creditLeft > 0)
		{
			System.out.println("You have " + creditLeft + " more credits until you reach your desired credit load.");
			System.out.println("Based off of your interests, here are some suggested general education courses you may take.");
			for(int d = 0; d < 5; d++)
			{
				//For gen ed courses and if there are still hours left over
				for(int i = 1; i < gCount; i++)
				{
					//If the current semester type the node indexed is equal to semester entered, contains one of the interests of the user, has the prereqs taken, then use
						//Subtract credit hours from total creditHours
					temp = genCourse.indexOf(i);
					for(int j = 0; i < 3; j++)
					{
						if(genCourse.contains(interests[j]))
						{
							//If the current semester type the node indexed is equal to the current semester the student enters or is both then it's okay to use
							if(semester == temp.semesterType || "both" == temp.semesterType);
							{
								//If the prereqs aren't present for the next course in the linked list, then take that course and subtract class credits from creditHours
								for(int k = 0; k < temp.preReq.length; k++)
								{
									if(!genCourse.contains(temp.preReq, temp.preReq[k]))
									{
										System.out.println(temp.courseName);
									}
								}
							}
						}
					}
				}
			}
		}
		
	}

	public static void main(String[] args) {
		Scanner input = null;
		String name, num, sem, attribute, hours, difficulty, required;
		String temp;
		String[] pre, co;
		
		//Major courses
		Node cCourse = null;
		Major majCourses = new Major();
		
		try
		{
			input = new Scanner(new File("courses/Courses_-_Major.txt")).useDelimiter(",");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
			System.exit(0);
		}
		
		while(input.hasNextLine())
		{
			num = input.nextLine();
			name = input.nextLine();
			hours = input.nextLine();
			difficulty = input.nextLine();
			
			temp = input.nextLine();
			pre = temp.split(";");
			
			temp = input.nextLine();
			co = temp.split(";");
			
			sem = input.nextLine();
			required = input.nextLine();
			
			cCourse = new Node("Computer Science", name, num, hours, difficulty, pre, co, sem, required);
			majCourses.append(cCourse);
		}
		
		//GenEd courses
		Node mCourse = null;
		Node eCourse = null;
		Node seCourse = null;
		Node hpCourse = null;
		Node nsCourse = null;
		
		try
		{
			input = new Scanner(new File("courses/Courses_-_GenEd.txt")).useDelimiter(",");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
			System.exit(0);
		}
				
		GenEds genCourses = new GenEds();
		while(input.hasNextLine())
		{
			num = input.nextLine();
			name = input.nextLine();
			hours = input.nextLine();
			difficulty = input.nextLine();
			
			temp = input.nextLine();
			pre = temp.split(";");
			
			temp = input.nextLine();
			co = temp.split(";");
			
			sem = input.nextLine();
			required = input.nextLine();
			attribute = input.nextLine();
			
			if(num.contains("AAAS") || num.contains("PSYC") || num.contains("ANTH") || num.contains("COMM")
					|| num.contains("ECON") || num.contains("GENS") || num.contains("GEOG") || num.contains("HIST") 
					|| num.contains("LING") || num.contains("MUSC") || num.contains("POLS") || num.contains("SOCI"))
		    {
				seCourse = new Node("Social Science", name, num, hours, difficulty, pre, co, sem, attribute, required);
				genCourses.append(seCourse);
			}
			else if(num.contains("ENGL"))
			{
				eCourse = new Node("English", name, num, hours, difficulty, pre, co, sem, attribute, required);
				genCourses.append(eCourse);
			}
			else if(num.contains("MATH"))
			{
				mCourse = new Node("Math", name, num, hours, difficulty, pre, co, sem, attribute, required);
				genCourses.append(mCourse);
			}
			else if(num.contains("HLTH") || input.next().contains("KINE"))
			{
				hpCourse = new Node("Health/PE", name, num, hours, difficulty, pre, co, sem, attribute, required);
				genCourses.append(hpCourse);
			}
			else if(num.contains("CHEM") || num.contains("BIOL") || num.contains("PHYS") || num.contains("GEO"))
			{
				nsCourse = new Node("Natural Sciences", name, num, hours, difficulty, pre, co, sem, attribute, required);
				genCourses.append(nsCourse);
			}
		}
		String semester;
		String[] interest = new String[3];
		String[] finalInterst = new String[3];
		String[] SofiaMajor = {"CSCI1010", "CSCI1011", "CSCI2530", "CSCI2400", "CSCI2540", "CSCI2410", "CSCI2405"};
        String[] SofiaGenEd = {"COAD1000", "ENGL1100", "MUSC1018", "HLTH1000", "RELI1000", "MATH2173", "PSYC2777", "PHIL2275", "MATH2300", "ENGL2201"};

        majCourses.classesTook(SofiaMajor);
        genCourses.classesTook(SofiaGenEd);
		int creditHours;
		input = new Scanner(System.in);
		String option;
		
		//Home Page
		System.out.println("***** ECU Virtual Advisor *****");
		
		System.out.println("Select your top 3 interests (Press enter after each entry): ");
		System.out.println("a) Writing \nb) Music\nc) Visual Art\nd) Psychology");
		interest[0] = input.next();
		interest[1] = input.next();
		interest[2] = input.next();
		
		switch(interest[0])
		{
		case "a":
			finalInterst[0] = "Writing";
			break;
		case "b":
			finalInterst[0] = "Music";
			break;
		case "c":
			finalInterst[0] = "Visual Art";
			break;
		case "d":
			finalInterst[0] = "Psychology";
			break;
		}
		
		switch(interest[1])
		{
		case "a":
			finalInterst[1] = "Writing";
			break;
		case "b":
			finalInterst[1] = "Music";
			break;
		case "c":
			finalInterst[1] = "Visual Art";
			break;
		case "d":
			finalInterst[1] = "Psychology";
			break;
		}
		
		switch(interest[2])
		{
		case "a":
			finalInterst[2] = "Writing";
			break;
		case "b":
			finalInterst[2] = "Music";
			break;
		case "c":
			finalInterst[2] = "Visual Art";
			break;
		case "d":
			finalInterst[2] = "Psychology";
			break;
		}
		
		System.out.println("Enter your current semester: ");
		semester = input.next();
		
		System.out.println("How many credit hours do you want to take next semester?");
		creditHours = input.nextInt();
		
		System.out.println("Select an option: ");
		System.out.println("A) Browse Courses\nB) Plan Ahead");
		input.nextLine();
		option = input.nextLine();
		option = option.toUpperCase();
		
		switch(option)
		{
		case "A":
			browseCourses(semester, majCourses, genCourses);
			break;
		case "B":
			planAhead(semester, creditHours, finalInterst, majCourses, genCourses);
			break;
		default:
			System.out.println("Invalid Option");
			System.exit(0);
		}
		
		input.close();
	}

}
