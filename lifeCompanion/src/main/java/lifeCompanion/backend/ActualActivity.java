package lifeCompanion.backend;

import java.util.Calendar;
import java.util.Date;

public class ActualActivity
{
	private Activity activity;
	
	private Date timeOfActivity;
	
	private float usedTimeInHours;

	public Activity getActivity() 
	{
		return activity;
	}

	public void setActivity(Activity activity) 
	{
		this.activity = activity;
	}

	public Date getTimeOfActivity()
	{
		return timeOfActivity;
	}

	public void setTimeOfActivity(Date timeOfActivity)
	{
		this.timeOfActivity = timeOfActivity;
	}

	public float getUsedTimeInHours()
	{
		return usedTimeInHours;
	}

	public void setUsedTimeInHours(float timeInHours)
	{
		this.usedTimeInHours = timeInHours;
	}
	
	public String toString()
	{
		Calendar time = Calendar.getInstance();
		time.setTime(timeOfActivity);
		return time.get(Calendar.HOUR) + ":" + time.get(Calendar.MINUTE) + "\r\n" + usedTimeInHours + "h" + "\r\n"+ activity.getName() + "\r\n" + activity.getDescription();
	}

}
