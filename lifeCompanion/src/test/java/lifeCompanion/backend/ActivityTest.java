package lifeCompanion.backend;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class ActivityTest
{
	Activity activity;
	ActualActivity actualActivity;
	String name;
	String desc;
	Date date;
	Date date2;
	int timeInHours;
	
	@Test
    public void testActivity() 
	{
		name = "Schlafen";
		desc = "Augen zu und wegnicken";
        activity = new Activity(name, desc);
        assertEquals(activity.getName(), name);
        assertEquals(activity.getDescription(), desc);
    }
	
	@Test
	public void testActualActivity()
	{
		name = "Schlafen";
		desc = "Augen zu und wegnicken";
        activity = new Activity(name, desc);
        date = new Date(30, 11, 20, 10, 0);
        timeInHours = 2;
        ActualActivity actualActivity = new ActualActivity(activity, date, timeInHours);
        assertEquals(actualActivity.getActivity(), activity);
        assertEquals(actualActivity.getTimeOfActivity(), date);
        assertEquals((int)actualActivity.getUsedTimeInHours(), timeInHours);
	}
	
	@Test
	public void testDay()
	{
		name = "Schlafen";
		desc = "Augen zu und wegnicken";
        activity = new Activity(name, desc);
        date = new Date(30, 11, 20, 10, 0);
        date2 = new Date(30, 11, 20, 12, 0);
        timeInHours = 2;
        
        ActualActivity actualActivity = new ActualActivity(activity, date, timeInHours);
        ActualActivity actualActivity2 = new ActualActivity(activity, date2, timeInHours);
        Day day = new Day(new Date(30,11,20), 6, 7);
        day.addActivity(actualActivity);
        day.addActivity(actualActivity2);

        assertEquals(day.getActivities().size(), 2);
        assertEquals(day.getActivities().get(0), actualActivity);
        assertEquals(day.getActivities().get(1), actualActivity2);
        assertEquals(day.getActivities().get(0).getTimeOfActivity(), date);
        assertEquals(day.getActivities().get(1).getTimeOfActivity(), date2);
        assertEquals(day.getPhysicalWellBeing(), 7);
        assertEquals(day.getHappinessScore(), 6);
	}
	
	@Test
	public void testDayCollection()
	{
		name = "Schlafen";
		desc = "Augen zu und wegnicken";
        activity = new Activity(name, desc);
        Activity activity2 = new Activity("Joggen", "Im freien joggen gehen");
        date = new Date(30, 11, 20, 10, 0);
        date2 = new Date(30, 11, 20, 12, 0);
        Date date3 = new Date(30,11,19,10,0);
        timeInHours = 2;
        int timeInHours2 = 3;
        
        ActualActivity actualActivity = new ActualActivity(activity, date, timeInHours);
        ActualActivity actualActivity2 = new ActualActivity(activity, date, timeInHours);
        ActualActivity actualActivity3 = new ActualActivity(activity2, date3, timeInHours2);
        
        Day day = new Day(new Date(30,11,20), 6, 7);
        day.addActivity(actualActivity);
        day.addActivity(actualActivity2);
        
        Day day2 = new Day(new Date(30,11,19), 6, 10);
        day.addActivity(actualActivity3);
        
        DayCollection dayCollection = new DayCollection();
        dayCollection.addDay(day);
        dayCollection.addDay(day2);
        
        assertEquals(dayCollection.getDay(new Date(30,11,20)), day);
        assertEquals(dayCollection.getDay(new Date(30,11,19)), day2);
        float[] activityScore = dayCollection.getActivityAnalysis(activity);
        assertEquals((int)activityScore[0], 7);
        assertEquals((int)activityScore[1], 6);
	}
}
