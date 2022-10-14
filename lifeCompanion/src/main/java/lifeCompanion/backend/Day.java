package lifeCompanion.backend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Day
{
	private Date date;

	private List<ActualActivity> activityList;

	private int happinessScore;

	private int physicalWellBeing;
	
	//private boolean d

	public Day(Date date, int happinessScore, int physicalWellBeing)
	{
		activityList = new ArrayList<ActualActivity>();
		this.date = date;
		this.happinessScore = happinessScore;
		this.physicalWellBeing = physicalWellBeing;
	}
	
	public boolean isSpecificDate(Date date)
	{
		Calendar dateToCheck = Calendar.getInstance();
		dateToCheck.setTime(date);
		Calendar thisDate = Calendar.getInstance();
		thisDate.setTime(this.date);
		thisDate.get(Calendar.MONTH);
		if(dateToCheck.get(Calendar.YEAR) == thisDate.get(Calendar.YEAR) 
				&& dateToCheck.get(Calendar.MONTH) == thisDate.get(Calendar.MONTH) 
				&& dateToCheck.get(Calendar.DAY_OF_MONTH) == thisDate.get(Calendar.DAY_OF_MONTH))
		{
			return true;
		}
		return false;
	}
	
	public List<ActualActivity> getActivities()
	{
		return activityList;
	}

	public void addActivity(ActualActivity activity)
	{
		activityList.add(activity);
	}

	public void removeActivity(ActualActivity activity)
	{
		activityList.remove(activity);
	}
	
	public int getHappinessScore()
	{
		return happinessScore;
	}

	public void setHappinessScore(int happinessScore)
	{
		this.happinessScore = happinessScore;
	}

	public int getPhysicalWellBeing()
	{
		return physicalWellBeing;
	}

	public void setPhysicalWellBeing(int physicalWellBeing)
	{
		this.physicalWellBeing = physicalWellBeing;
	}

	public Date getDate()
	{
		return date;
	}

}
