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

public class MainScreen extends JFrame implements IOverlayingObject
{
	UIController uiController;
	
	Date currentDate;
	
	Container pane;
	GroupLayout calendarGroup;
	List<ActivityGroup> dayFillInGroupList;
	JTextField calendarText;
	JButton buttonDayBefore;
	JButton buttonDayAfter;
	
	public MainScreen(UIController uiController)
	{
		this.uiController = uiController;
		currentDate = new Date();
		prepareMainScreen();
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
		prepareDayFillInGroup();
		
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
	
	private void prepareDayFillInGroup()
	{
		dayFillInGroupList = new ArrayList<ActivityGroup>();
	}
	
	private void addActivity()
	{
		dayFillInGroupList.add(new ActivityGroup(pane, null, this));
	}
	
	public void removeGroupLayout(GroupLayout layoutToDelete)
	{
		dayFillInGroupList.remove(layoutToDelete);
	}
	
	private void updateScreen()
	{
		setCalendarDateToCurrentDate();
	}
	
	private void setCalendarDateToCurrentDate()
	{
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(currentDate);
		calendarText.setText(dateCalendar.get(Calendar.DAY_OF_MONTH) + "." + (dateCalendar.get(Calendar.MONTH)+1) + "." + dateCalendar.get(Calendar.YEAR));
	}
	
	private void setDayOffset(int offset)
	{
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(currentDate); 
		calendar.add(Calendar.DATE, offset);
		currentDate = calendar.getTime();
		updateScreen();
	}
}
