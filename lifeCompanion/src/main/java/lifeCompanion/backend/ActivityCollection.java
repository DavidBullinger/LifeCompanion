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

	public boolean addActivity(String name, String description)
	{
		for (Activity activity : activityList)
		{
			if(name.equals(activity.getName()))
			{
				return false;
			}
		}
		activityList.add(new Activity(name, description));
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
}
