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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lifeCompanion.backend.ActualActivity;
import lifeCompanion.backend.Day;
import lifeCompanion.backend.IController;

public class MainScreen extends JFrame implements IMainScreen
{
	
	IController controller;
	
	Container pane;
	GroupLayout calendarGroup;
	List<ActivityGroup> dayFillInGroupList;
	
	GroupLayout activityGroup;
	JTextField calendarText;
	JButton buttonDayBefore;
	JButton buttonDayAfter;
	JButton buttonAddActivity;
	JButton buttonCreateActivity;
	JButton buttonShowAnalysis;
	JButton buttonShowStatistics;
	JButton buttonShowSettings;
	
	GroupLayout healthGroup;
	JButton buttonPhysicalHealth;
	JButton buttonMentalHealth;
	
	public MainScreen(IController controller)
	{
		this.controller = controller;

		prepareMainScreen();
		controller.getDayCollection().addDay(new Day(controller.getCurrentDate(), 7, 7));
		this.setVisible(true);
	}
	
	private void prepareMainScreen()
	{
		setTitle("Life Companion");
		setSize(1000, 700);
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
		
		activityGroup = new GroupLayout(pane);
		
		buttonShowSettings = new JButton("Settings");
		buttonShowSettings.setPreferredSize(new Dimension(90,40));
		buttonShowSettings.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				showSettingsScreen();
			}
		});
		
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
				showActivityScreen();
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
		
		buttonShowStatistics = new JButton("Statistics");
		buttonShowStatistics.setPreferredSize(new Dimension(90,40));
		buttonShowStatistics.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				showStatisticsScreen();
			}
		});
		
		activityGroup.setVerticalGroup(activityGroup.createSequentialGroup().addComponent(buttonShowSettings));
		activityGroup.setVerticalGroup(activityGroup.createSequentialGroup().addComponent(buttonAddActivity));
		activityGroup.setVerticalGroup(activityGroup.createSequentialGroup().addComponent(buttonCreateActivity));
		activityGroup.setVerticalGroup(activityGroup.createSequentialGroup().addComponent(buttonShowAnalysis));
		activityGroup.setVerticalGroup(activityGroup.createSequentialGroup().addComponent(buttonShowStatistics));
	}
	
	private void showSettingsScreen()
	{
		SettingsScreen settingsScreen = new SettingsScreen(controller);
		settingsScreen.setVisible(rootPaneCheckingEnabled);
	}
	private void showActivityScreen()
	{
		CreateActivityWizard wizard = new CreateActivityWizard(controller);
		wizard.setVisible(true);
	}
	
	private void addActivityWizard()
	{
		if (controller.getActivityCollection().getActivityList() == null || controller.getActivityCollection().getActivityList().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Please first create an activity before adding one!");
			return;
		}
		
		AddAcitivityWizard addActivityWizard = new AddAcitivityWizard(controller, this);
		addActivityWizard.setVisible(true);
	}
	
	public void addActivity(ActualActivity actualActivity)
	{
		Day currentDay = controller.getDayCollection().getDay(controller.getCurrentDate());
		currentDay.addActivity(actualActivity);
		dayFillInGroupList.add(new ActivityGroup(pane, actualActivity, this));
	}
	
	public void removeGroupLayout(GroupLayout layoutToDelete, ActualActivity actualActivity)
	{
		Day currentDay = controller.getDayCollection().getDay(controller.getCurrentDate());
		currentDay.removeActivity(actualActivity);
		dayFillInGroupList.remove(layoutToDelete);
		
		refreshPane();
	}
	
	private void updateActivitiesAndScores()
	{
		for (ActivityGroup activityGroup : dayFillInGroupList)
		{
			activityGroup.removeActivity();
		}
		
		dayFillInGroupList = new ArrayList<ActivityGroup>();
		
		Date currentDate = controller.getCurrentDate();
		Day currentDay = controller.getDayCollection().getDay(currentDate);
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
		dateCalendar.setTime(controller.getCurrentDate());
		calendarText.setText(dateCalendar.get(Calendar.DAY_OF_MONTH) + "." + (dateCalendar.get(Calendar.MONTH)+1) + "." + dateCalendar.get(Calendar.YEAR));
	
	}
	
	private void setDayOffset(int offset)
	{
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(controller.getCurrentDate()); 
		calendar.add(Calendar.DATE, offset);
		controller.setCurrentDate(calendar.getTime());
		Day currentDay = controller.getDayCollection().getDay(controller.getCurrentDate());
		if(currentDay == null)
		{
			controller.getDayCollection().addDay(new Day(controller.getCurrentDate(), 7, 7));
		}
		updateScreen();
	}
	
	private void setPhysicalOffset()
	{
		Day currentDay = controller.getDayCollection().getDay(controller.getCurrentDate());
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
		Day currentDay = controller.getDayCollection().getDay(controller.getCurrentDate());
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
		AnalysisScreen analysisScreen = new AnalysisScreen(controller.getDayCollection(), controller.getActivityCollection());
		analysisScreen.setVisible(true);
	}
	
	private void showStatisticsScreen()
	{
		StatisticsScreen statisticsScreen = new StatisticsScreen(controller.getDayCollection(), controller.getActivityCollection(), controller.getCurrentStatisticChoice());
		statisticsScreen.setVisible(true);
	}
	
	// The Pane contents only tent to disappear when something on it changes, this
	// is an easy fix
	private void refreshPane()
	{
		setSize(800, 499);
		setSize(800, 500);
	}
	
}
