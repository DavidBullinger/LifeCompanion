package lifeCompanion.backend;

import java.util.List;

public interface IActivityStatistic
{
	//Evaluates the a specific activity depending on all the actualActivities
	public List<StatisticData> evaluate(ActivityCollection activityCollection, DayCollection dayCollection);
	
	public String getStatisticName();
}
