package lifeCompanion.backend;

import java.util.Date;
import java.util.List;

public class Controller implements IController
{
	ActivityCollection activityCollection;
	List<ActualActivity> actualActivityList;

	Date currentDate;
	
	DayCollection dayCollection;
	
	IActivityStatistic currentStatisticChoice;
	
	public Controller()
	{
		currentStatisticChoice = new StatisticByHoursDone();
		dayCollection = new DayCollection();
		currentDate = new Date();
		activityCollection = new ActivityCollection(); //load existing activity Collection if available
	}
	
	public boolean createActivity(Activity activity)
	{
		return activityCollection.addActivity(activity);
	}
	
	public void removeActivity(String name)
	{
		activityCollection.removeActivity(name);
	}
	
	public Activity getActivityByName(String name)
	{
		return activityCollection.getActivityByName(name);
	}
	
	public ActivityCollection getActivityCollection()
	{
		return activityCollection;
	}
	
	public void addActualActivity(ActualActivity actualActivity)
	{
		actualActivityList.add(actualActivity);
	}
	
	public Date getCurrentDate()
	{
		return currentDate;
	}

	public void setCurrentDate(Date currentDate)
	{
		this.currentDate = currentDate;
	}
	
	public DayCollection getDayCollection()
	{
		return dayCollection;
	}

	public IActivityStatistic getCurrentStatisticChoice()
	{
		return currentStatisticChoice;
	}

	public void setCurrentStatisticChoice(IActivityStatistic currentStatisticChoice)
	{
		this.currentStatisticChoice = currentStatisticChoice;
	}
	
}
