package lifeCompanion.backend;

import java.util.Date;
import java.util.List;

public class User 
{
	private String userName;
	private List<Day> days;

	public User(String userName)
	{
		this.userName = userName;
	}
	
	public List<Day> getDays() 
	{
		return days;
	}
	
	public Day getDayByDate(Date date)
	{
		for (Day day : days)
		{
			if(day.isSpecificDate(date)) 
			{
				return day;
			}
		}
		return null;
	}

	public boolean deleteDayByDate(Date date)
	{
		for (Day day : days)
		{
			if(day.isSpecificDate(date)) 
			{
				days.remove(day);
				return true;
			}
		}
		return false;
	}
	
	public boolean addDay(Day day)
	{
		if(getDayByDate(day.getDate()) == null)
		{
			days.add(day);
			return true;
		}
		return false;
	}
	
	public boolean updatePhysicalWellBeing(int physicalWellBeing, Date date)
	{
		Day day = getDayByDate(date);
		if(day != null)
		{
			day.setPhysicalWellBeing(physicalWellBeing);
			return true;
		}
		return false;
	}
	
	public boolean updateHappinessScore(int happinessScore, Date date)
	{
		Day day = getDayByDate(date);
		if(day != null)
		{
			day.setPhysicalWellBeing(happinessScore);
			return true;
		}
		return false;
	}
	
	public void setDays(List<Day> days) 
	{
		this.days = days;
	}
	
	public boolean addActivity(ActualActivity activity)
	{
		Day day = getDayByDate(activity.getTimeOfActivity());
		if(day != null)
		{
			day.addActivity(activity);
			return true;
		}
		return false;
	}
	
	public boolean removeActivity(ActualActivity activity)
	{
		Day day = getDayByDate(activity.getTimeOfActivity());
		if(day != null)
		{
			day.removeActivity(activity);
			return true;
		}
		return false;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

}
