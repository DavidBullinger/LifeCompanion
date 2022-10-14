package lifeCompanion.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayCollection
{
	private List<Day> dayList;
	
	public DayCollection()
	{
		dayList = new ArrayList<Day>();
	}
	
	public void addDay(Day day)
	{
		dayList.add(day);
	}
	
	public Day getDay(Date date)
	{
		for (Day day : dayList)
		{
			if(day.getDate().getYear() == date.getYear() && day.getDate().getMonth() == date.getMonth() && day.getDate().getDate() == date.getDate())
			{
				return day;
			}
		}
		return null;
	}
	
	public List<Day> getDayList()
	{
		return dayList;
	}
	
	public float[] getActivityAnalysis(Activity activity)
	{
		List<Float> happinessRatings = new ArrayList<Float>();
		List<Float> physicalRatings = new ArrayList<Float>();
		float totalHours = 0;
		
		for (Day day : getDayList())
		{
			for (ActualActivity actualActivity : day.getActivities())
			{
				if(actualActivity.getActivity().equals(activity))
				{
					float hours = actualActivity.getUsedTimeInHours();
					totalHours += hours;
					happinessRatings.add(day.getHappinessScore() *hours);
					physicalRatings.add(day.getPhysicalWellBeing() *hours);
				}
			}
		}
		
		float averageHappinessRating = 0;
		float averagePhysicalRating = 0;
		
		for (float rating : happinessRatings)
		{
			averageHappinessRating += rating;
		}
		for (float rating : physicalRatings)
		{
			averagePhysicalRating += rating;
		}
		
		averageHappinessRating = averageHappinessRating/totalHours;
		averagePhysicalRating = averagePhysicalRating/totalHours;
		
		if(totalHours == 0)
		{
			return new float[0];
		}
		float[] averageRating = new float[2];
		averageRating[0] = averagePhysicalRating;
		averageRating[1] = averageHappinessRating;
		return averageRating;
	}
}
