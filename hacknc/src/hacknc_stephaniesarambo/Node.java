package hacknc_stephaniesarambo;

public class Node {
	protected String courseType;
	protected String courseName;
	protected String courseNum;
	protected int creditHours;
	protected double difficulty;
	protected final int size = 5;
	protected String[] preReq;
	protected String[] coReq;
	protected String semesterType;
	protected String attribute;
	protected boolean required;
	
	public Node()
	{
		courseName = "";
		courseNum = "";
		creditHours = 0;
		difficulty = 0;
		preReq = new String[size];
		coReq = new String[size];
		semesterType = "";
	}
	
	public Node(String courseType, String courseName, String courseNum, String creditHours, String difficulty, String[] preReq, String[] coReq, String semesterType, String required)
	{
		this.courseType = courseType;
		this.courseName = courseName;
		this.courseNum = courseNum;
		this.creditHours = Integer.parseInt(creditHours);
		this.difficulty = Double.parseDouble(difficulty);
		this.preReq = preReq;
		this.coReq = coReq;
		this.semesterType = semesterType;
		this.required = Boolean.parseBoolean(required);
	}
	
	public Node(String courseType, String courseName, String courseNum, String creditHours, String difficulty, String[] preReq, String[] coReq, String semesterType, String attribute, String required)
	{
		this.courseType = courseType;
		this.courseName = courseName;
		this.courseNum = courseNum;
		this.creditHours = Integer.parseInt(creditHours);
		this.difficulty = Double.parseDouble(difficulty);
		this.preReq = preReq;
		this.coReq = coReq;
		this.semesterType = semesterType;
		this.attribute = attribute;
		this.required = Boolean.parseBoolean(required);
	}
}
