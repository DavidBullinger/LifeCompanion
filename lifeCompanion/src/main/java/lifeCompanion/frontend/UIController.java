package lifeCompanion.frontend;

import java.util.Date;

import lifeCompanion.backend.Activity;
import lifeCompanion.backend.ActivityCollection;
import lifeCompanion.backend.ActualActivity;
import lifeCompanion.backend.Controller;
import lifeCompanion.backend.DayCollection;

public class UIController
{
	MainScreen mainScreen;
	
	Controller controller;
	Date currentDate;
	DayCollection dayCollection;
	
	int physicalValue = 7;
	int mentalValue = 7;
	
	public UIController()
	{
		dayCollection = new DayCollection();
		currentDate = new Date();
		controller = new Controller();
		mainScreen = new MainScreen(this);
		mainScreen.setVisible(true); 
	}
	
	public boolean createActivity(Activity activity)
	{
		return controller.addActivity(activity);
	}
	
	
	public ActivityCollection getActivityCollection()
	{
		return controller.getActivityCollection();
	}

	public Date getCurrentDate()
	{
		return currentDate;
	}

	public void setCurrentDate(Date currentDate)
	{
		this.currentDate = currentDate;
	}
	
	public void addActualActivity(ActualActivity actualAcitivity)
	{
		controller.addActualActivity(actualAcitivity);
	}
	
	public DayCollection getDayCollection()
	{
		return dayCollection;
	}
	
	public MainScreen getMainScreen()
	{
		return mainScreen;
	}
}
