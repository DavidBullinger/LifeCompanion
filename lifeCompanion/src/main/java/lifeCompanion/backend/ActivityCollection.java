package lifeCompanion.backend;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollection
{
	List<Activity> activityList;
	public ActivityCollection(List<Activity> activityList)
	{
		this.activityList = activityList;
	}
	public ActivityCollection()
	{
		activityList = new ArrayList<Activity>();
	}

	public List<Activity> getActivityList()
	{
		return activityList;
	}
	
	public boolean addActivity(Activity activity)
	{
		for (Activity activityTemp : activityList)
		{
			if(activityTemp.getName().equals(activity.getName()))
			{
				return false;
			}
		}
		activityList.add(activity);
		return true;
	}
	
	public boolean removeActivity(String name)
	{
		for (Activity activity : activityList)
		{
			if(name.equals(activity.getName()))
			{
				return activityList.remove(activity);
			}
		}
		return false;
	}
	
	public Activity getActivityByName(String name)
	{
		for (Activity activity : activityList)
		{
			if(name.equals(activity.getName()))
			{
				return activity;
			}
		}
		return null;
	}
}
