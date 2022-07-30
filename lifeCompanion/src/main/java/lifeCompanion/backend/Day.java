package lifeCompanion.backend;

import java.util.Date;
import java.util.List;

public class Day
{
	private Date date;

	private List<ActualActivity> activities;

	private int happinessScore;

	private int physicalWellBeing;

	public Day(Date date)
	{
		this.date = date;
	}

	public List<ActualActivity> getActivities()
	{
		return activities;
	}

	public void addActivitie(ActualActivity activitie)
	{
		activities.add(activitie);
	}

	public int getHappinessScore()
	{
		return happinessScore;
	}

	public void setHappinessScore(int happinessScore)
	{
		this.happinessScore = happinessScore;
	}

	public int getPhysicalWellBeing()
	{
		return physicalWellBeing;
	}

	public void setPhysicalWellBeing(int physicalWellBeing)
	{
		this.physicalWellBeing = physicalWellBeing;
	}

}
