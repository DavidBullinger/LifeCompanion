package lifeCompanion.frontend;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lifeCompanion.backend.Activity;
import lifeCompanion.backend.ActualActivity;

public class AddAcitivityWizard extends JFrame
{
	Container pane;

	GroupLayout layout;
	JComboBox<String> dropDownActivityChooser;
	JComboBox<String> dropDownTime;
	JTextField descriptionText;
	JTextField numberOfHoursText;
	JButton confirmButton;
	UIController uiController;
	//ActualActivity actualActivity;
	MainScreen mainScreen;

	List<Activity> activityList;
	

	public AddAcitivityWizard(UIController uiController)
	{
		this.uiController = uiController;
		this.mainScreen = uiController.getMainScreen();
		pane = getContentPane();
		LayoutManager layoutManager = new FlowLayout();
		pane.setLayout(layoutManager);
		layout = new GroupLayout(pane);
		setSize(500, 300);

		String[] times = new String[48];
		for (int i = 0; i < 48; i++)
		{
			if(i % 2 == 0)
			{
				times[i] = (i/2) + ":00";
			}
			else
			{
				times[i] = (i/2) + ":30";
			}
		}
		dropDownTime = new JComboBox<String>(times);
		dropDownTime.setPreferredSize(new Dimension(80, 30));
		dropDownTime.setEditable(true);
		
		activityList = uiController.getActivityCollection().getActivityList();
		String[] activityNames = new String[activityList.size()];
		for (int i = 0; i < activityList.size(); i++)
		{
			activityNames[i] = activityList.get(i).getName();

		}
		dropDownActivityChooser = new JComboBox<String>(activityNames);
		dropDownActivityChooser.setPreferredSize(new Dimension(80, 30));
		dropDownActivityChooser.setEditable(true);
		dropDownActivityChooser.addActionListener (new ActionListener () 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		        chooseAnotherActivity();
		    }
		});

		descriptionText = new JTextField();
		descriptionText.setPreferredSize(new Dimension(200, 80));
		descriptionText.setEditable(false);
		descriptionText.setText("Description");

		numberOfHoursText = new JTextField();
		numberOfHoursText.setPreferredSize(new Dimension(30, 30));
		numberOfHoursText.setEditable(true);
		numberOfHoursText.setText("1");

		confirmButton = new JButton("Confirm");
		confirmButton.setPreferredSize(new Dimension(90, 40));
		confirmButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				addActualActivity();

			}
		});
		layout.setAutoCreateContainerGaps(true);
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(dropDownTime));
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(dropDownActivityChooser));
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(descriptionText));
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(numberOfHoursText));
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(confirmButton));
	}

	
	private void chooseAnotherActivity()
	{
		String selectedActivity = (String)dropDownActivityChooser.getSelectedItem();
        descriptionText.setText(uiController.getActivityCollection().getActivityByName(selectedActivity).getDescription());
	}
	private void addActualActivity()
	{
		int numberOfHours;
		
		try
		{
			numberOfHours = Integer.valueOf(numberOfHoursText.getText());
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, "Please enter the number of hours you did that activity in (full) numbers only.");
			return;
		}
		
		int timeIndex = dropDownTime.getSelectedIndex();
		Date selectedDate = uiController.getCurrentDate();
		selectedDate.setHours(0);
		selectedDate.setMinutes(0);
		selectedDate.setSeconds(0);
		selectedDate.setMinutes((30*timeIndex));
		
		for (Activity activity : activityList)
		{
			if(activity.getName().equals(dropDownActivityChooser.getSelectedItem()))
			{
				mainScreen.addActivity(new ActualActivity(activity, selectedDate, numberOfHours));
			}
		}
		
		
	}
}
