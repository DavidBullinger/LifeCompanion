package lifeCompanion.backend;

import java.util.Date;

public interface IController
{

	public ActivityCollection getActivityCollection();
	
	public Date getCurrentDate();
	
	public void setCurrentDate(Date currentDate);
	
	public boolean createActivity(Activity activity);
	
	public void removeActivity(String name);
	
	public Activity getActivityByName(String name);
	
	public DayCollection getDayCollection();
}
