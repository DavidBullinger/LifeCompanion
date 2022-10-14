package lifeCompanion.frontend;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import lifeCompanion.backend.ActualActivity;
import lifeCompanion.backend.Day;

public class MainScreen extends JFrame implements IOverlayingObject
{
	UIController uiController;
	
	Container pane;
	GroupLayout calendarGroup;
	List<ActivityGroup> dayFillInGroupList;
	
	GroupLayout addGroup;
	JTextField calendarText;
	JButton buttonDayBefore;
	JButton buttonDayAfter;
	JButton buttonAddActivity;
	JButton buttonCreateActivity;
	JButton buttonShowAnalysis;
	
	GroupLayout healthGroup;
	JButton buttonPhysicalHealth;
	JButton buttonMentalHealth;
	
	public MainScreen(UIController uiController)
	{
		this.uiController = uiController;

		prepareMainScreen();
		
		uiController.getDayCollection().addDay(new Day(uiController.getCurrentDate(), 7, 7));
	}
	
	private void prepareMainScreen()
	{
		setTitle("Life Companion");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pane = getContentPane();
		LayoutManager layout = new FlowLayout();
		pane.setLayout(layout);
		
		prepareCalendarGroup();
		prepareHealthGroup();
		prepareActivitiySection();
		
	}
	
	private void prepareCalendarGroup()
	{
		calendarGroup = new GroupLayout(pane);
		calendarText = new JTextField();
		calendarText.setPreferredSize(new Dimension(80, 30));
		calendarText.setEditable(false);
		setCalendarDateToCurrentDate();
		//calendarGroup.
		
		//calendarGroup.setHorizontalGroup(calendarGroup.createSequentialGroup().addComponent(calendarText));
		calendarGroup.setAutoCreateContainerGaps(true);
		
		buttonDayBefore = new JButton("<-");
		buttonDayBefore.setPreferredSize(new Dimension(90,40));
		buttonDayBefore.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				setDayOffset(-1);
			}
		});
		buttonDayAfter = new JButton("->");
		buttonDayAfter.setPreferredSize(new Dimension(90,40));
		buttonDayAfter.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				setDayOffset(1);
			}
		});
		
		calendarGroup.setVerticalGroup(calendarGroup.createSequentialGroup().addComponent(buttonDayBefore));
		calendarGroup.setVerticalGroup(calendarGroup.createSequentialGroup().addComponent(calendarText));
		calendarGroup.setVerticalGroup(calendarGroup.createSequentialGroup().addComponent(buttonDayAfter));
	}
	
	private void prepareHealthGroup()
	{
		healthGroup = new GroupLayout(pane);

		healthGroup.setAutoCreateContainerGaps(true);
		
		buttonPhysicalHealth = new JButton("Physical Health: 7/10");
		buttonPhysicalHealth.setPreferredSize(new Dimension(180,40));
		buttonPhysicalHealth.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				setPhysicalOffset();
			}
		});
		buttonMentalHealth = new JButton("Happiness Score: 7/10");
		buttonMentalHealth.setPreferredSize(new Dimension(180,40));
		buttonMentalHealth.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				setMentalOffset();
			}
		});
		
		calendarGroup.setVerticalGroup(calendarGroup.createSequentialGroup().addComponent(buttonPhysicalHealth));
		calendarGroup.setVerticalGroup(calendarGroup.createSequentialGroup().addComponent(buttonMentalHealth));
	}
	
	private void prepareActivitiySection()
	{
		dayFillInGroupList = new ArrayList<ActivityGroup>();
		
		addGroup = new GroupLayout(pane);
		buttonAddActivity = new JButton("Add");
		buttonAddActivity.setPreferredSize(new Dimension(90,40));
		buttonAddActivity.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				addActivityWizard();
			}
		});
		
		buttonCreateActivity = new JButton("Create");
		buttonCreateActivity.setPreferredSize(new Dimension(90,40));
		buttonCreateActivity.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				createActivity();
			}
		});
		
		buttonShowAnalysis = new JButton("Analysis");
		buttonShowAnalysis.setPreferredSize(new Dimension(90,40));
		buttonShowAnalysis.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				showAnalysisScreen();
			}
		});
		
		addGroup.setVerticalGroup(addGroup.createSequentialGroup().addComponent(buttonAddActivity));
		addGroup.setVerticalGroup(addGroup.createSequentialGroup().addComponent(buttonCreateActivity));
		addGroup.setVerticalGroup(addGroup.createSequentialGroup().addComponent(buttonShowAnalysis));
	}
	
	public void createActivity()
	{
		CreateActivityWizard wizard = new CreateActivityWizard(uiController);
		wizard.setVisible(true);
	}
	
	private void addActivityWizard()
	{
		AddAcitivityWizard addActivityWizard = new AddAcitivityWizard(uiController);
		addActivityWizard.setVisible(true);
		//chooser
		
		//realoadActivities();
	}
	
	public void addActivity(ActualActivity actualActivity)
	{
		Day currentDay = uiController.getDayCollection().getDay(uiController.currentDate);
		currentDay.addActivity(actualActivity);
		dayFillInGroupList.add(new ActivityGroup(pane, actualActivity, this));
	}
	
	public void removeGroupLayout(GroupLayout layoutToDelete, ActualActivity actualActivity)
	{
		Day currentDay = uiController.getDayCollection().getDay(uiController.getCurrentDate());
		currentDay.removeActivity(actualActivity);
		dayFillInGroupList.remove(layoutToDelete);
		
		refreshPane();
	}
	
	public void updateActivitiesAndScores()
	{
		for (ActivityGroup activityGroup : dayFillInGroupList)
		{
			activityGroup.removeActivity();
		}
		
		dayFillInGroupList = new ArrayList<ActivityGroup>();
		
		Date currentDate = uiController.getCurrentDate();
		Day currentDay = uiController.getDayCollection().getDay(currentDate);
		for (ActualActivity actualActivity : currentDay.getActivities())
		{
			dayFillInGroupList.add(new ActivityGroup(pane, actualActivity, this));
		}
		
		buttonPhysicalHealth.setText("Physical Health: " + currentDay.getPhysicalWellBeing() + "/10");
		buttonMentalHealth.setText("Happiness Score: " + currentDay.getHappinessScore() + "/10");
		
		refreshPane();
	}
	
	private void updateScreen()
	{
		setCalendarDateToCurrentDate();
		updateActivitiesAndScores();
	}
	
	private void setCalendarDateToCurrentDate()
	{
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(uiController.getCurrentDate());
		calendarText.setText(dateCalendar.get(Calendar.DAY_OF_MONTH) + "." + (dateCalendar.get(Calendar.MONTH)+1) + "." + dateCalendar.get(Calendar.YEAR));
	
	}
	
	private void setDayOffset(int offset)
	{
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(uiController.getCurrentDate()); 
		calendar.add(Calendar.DATE, offset);
		uiController.setCurrentDate(calendar.getTime());
		Day currentDay = uiController.getDayCollection().getDay(uiController.getCurrentDate());
		if(currentDay == null)
		{
			uiController.getDayCollection().addDay(new Day(uiController.getCurrentDate(), 7, 7));
		}
		updateScreen();
	}
	
	private void setPhysicalOffset()
	{
		Day currentDay = uiController.getDayCollection().getDay(uiController.getCurrentDate());
		int value = currentDay.getPhysicalWellBeing() + 1;
		if(value > 10)
		{
			value = 0;
		}
		currentDay.setPhysicalWellBeing(value);
		buttonPhysicalHealth.setText("Physical Health: " + value + "/10");
	}
	
	private void setMentalOffset()
	{
		Day currentDay = uiController.getDayCollection().getDay(uiController.getCurrentDate());
		int value = currentDay.getHappinessScore() + 1;
		if(value > 10)
		{
			value = 0;
		}
		currentDay.setHappinessScore(value);
		buttonMentalHealth.setText("Happiness Score: " + value + "/10");
	}
	
	private void showAnalysisScreen()
	{
		AnalysisScreen analysisScreen = new AnalysisScreen(uiController.getDayCollection(), uiController.getActivityCollection());
		analysisScreen.setVisible(true);
	}
	// The Pane contents only tent to disappear when something on it changes, this
	// is an easy fix
	private void refreshPane()
	{
		setSize(800, 499);
		setSize(800, 500);
	}
	
}
