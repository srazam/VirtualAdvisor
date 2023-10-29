package hacknc_stephaniesarambo;

import java.util.LinkedList;

public class GenEds {
	private LinkedList<Node> classes;
	private Node course;
	private int count;
	
	public GenEds()
	{
		classes = new LinkedList<>();
		course = new Node();
		count = 0;
	}
	
	public Node indexOf(int index)
	{
		return classes.get(index);
	}
	
	public Node getCourse()
	{
		return course;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public boolean contains(String k)
	{
		String node = course.toString();
		if(node.contains(k))
			return true;
		return false;
	}
	
	public boolean contains(String[] req, String r)
	{
		for(int i = 0; i < req.length; i++)
		{
			if(req[i] == r)
				return true;
		}
		return false;
	}
	
	public void append(Node add)
	{
		classes.addLast(add);
		count++;
	}
	
	public void prepend(Node add)
	{
		classes.addFirst(add);
		count++;
	}
	
	public void insertAfter(Node add, Node course) throws Exception
	{
		int temp = 0;
		temp = classes.indexOf(course);
		classes.add(temp + 1, add);
		count++;
	}
	
	public LinkedList<Node> classesTook(String[] recentClasses)
	{
		Node temp = null;
		for(int i = 0; i < count; i++)
		{
			temp = classes.get(i);
			if(recentClasses[i] == temp.courseNum)
				classes.remove(i);
		}
		return classes;
	}
	
	public void display(String semester)
	{
		Node temp = null;
		for(int i = 1; i < count; i++)
		{
			temp = classes.get(i);
			if(temp.semesterType == semester)
			{
				System.out.println("*" + temp);
			}
			System.out.println();
		}
	}
	
	public void display()
	{
		for (int i = 1; i < count; i++)
		{
			System.out.println(classes.get(i));
		}
		
	}
}
