package lifeCompanion.backend;

import java.util.ArrayList;
import java.util.List;

public class StatisticByHoursDone implements IActivityStatistic
{

	static final String statisticName = "A statistic about the completed hours per activity";
	
	public List<StatisticData> evaluate(ActivityCollection activityCollection, DayCollection dayCollection)
	{
		List<StatisticData> statisticList = new ArrayList<StatisticData>();
		
		for (Activity activity : activityCollection.getActivityList())
		{
			float totalHours = 0;
			for (Day day : dayCollection.getDayList())
			{
				for (ActualActivity actualActivity : day.getActivities())
				{
					if(actualActivity.getActivity().equals(activity))
					{
						totalHours += actualActivity.getUsedTimeInHours();
					}
				}
			}
			StatisticData statisticData = new StatisticData(activity, (int)totalHours);
			statisticList.add(statisticData);
		}
		return statisticList;
	}

	public String getStatisticName()
	{
		return statisticName;
	}

}
