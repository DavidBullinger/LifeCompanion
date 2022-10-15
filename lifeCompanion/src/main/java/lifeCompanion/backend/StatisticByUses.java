package lifeCompanion.backend;

import java.util.ArrayList;
import java.util.List;

public class StatisticByUses implements IActivityStatistic
{

	static final String statisticName = "A statistic about the total times an activity was done";
	
	public List<StatisticData> evaluate(ActivityCollection activityCollection, DayCollection dayCollection)
	{
		List<StatisticData> statisticList = new ArrayList<StatisticData>();

		for (Activity activity : activityCollection.getActivityList())
		{
			float totalTimesDone = 0;
			for (Day day : dayCollection.getDayList())
			{
				for (ActualActivity actualActivity : day.getActivities())
				{
					if (actualActivity.getActivity().equals(activity))
					{
						totalTimesDone++;
					}
				}
			}
			StatisticData statisticData = new StatisticData(activity, (int)totalTimesDone);
			statisticList.add(statisticData);
		}
		return statisticList;
	}

	public String getStatisticName()
	{
		return statisticName;
	}

}
