package lifeCompanion.frontend;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import lifeCompanion.backend.ActualActivity;

public class ActivityGroup extends GroupLayout
{
	IMainScreen mainScreen;
	JTextField activityInformationField;
	JButton deleteButton;
	SequentialGroup sequentialGroup;
	Container pane;
	ActualActivity activity;

	public ActivityGroup(Container host, ActualActivity activity, IMainScreen mainScreen)
	{
		super(host);
		pane = host;

		this.mainScreen = mainScreen;
		activityInformationField = new JTextField();
		activityInformationField.setPreferredSize(new Dimension(250, 100));
		activityInformationField.setEditable(false);

		deleteButton = new JButton("X");
		deleteButton.setPreferredSize(new Dimension(90, 40));
		deleteButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				deleteActivity();
			}
		});
		sequentialGroup = this.createSequentialGroup().addComponent(activityInformationField);
		sequentialGroup.addComponent(deleteButton);
		this.setHorizontalGroup(sequentialGroup);

		if (activity != null)
		{
			activityInformationField.setText(activity.toString());
		}
		this.activity = activity;
	}

	private void deleteActivity()
	{
		removeActivity();
		mainScreen.removeGroupLayout(this, activity);
	}
	public void removeActivity()
	{
		pane.remove(activityInformationField);
		pane.remove(deleteButton);
	}
	
	public void reviveActivity()
	{
		pane.add(activityInformationField);
		pane.add(deleteButton);
	}
}
