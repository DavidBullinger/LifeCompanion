package lifeCompanion.backend;

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
	

}
