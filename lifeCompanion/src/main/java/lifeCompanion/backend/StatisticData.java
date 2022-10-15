package lifeCompanion.backend;

public class StatisticData
{
	Activity activity;
	int value;
	
	public StatisticData(Activity activity, int value)
	{
		super();
		this.activity = activity;
		this.value = value;
	}
	public Activity getActivity()
	{
		return activity;
	}
	public void setActivity(Activity activity)
	{
		this.activity = activity;
	}
	public int getValue()
	{
		return value;
	}
	public void setValue(int value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		return activity.getName() + ": " + value;
	}
}
