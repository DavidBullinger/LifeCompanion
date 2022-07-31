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
	IOverlayingObject mainScreen;
	JTextField activityInformationField;
	JButton deleteButton;

	public ActivityGroup(Container host, ActualActivity activity, IOverlayingObject mainScreen)
	{
		super(host);
		
		this.mainScreen = mainScreen;
		activityInformationField = new JTextField();
		activityInformationField.setPreferredSize(new Dimension(80, 30));
		activityInformationField.setEditable(false);
		
		deleteButton = new JButton("X");
		deleteButton.setPreferredSize(new Dimension(90, 40));
		deleteButton.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				removeActivity();
			}
		});
		this.setVerticalGroup(this.createSequentialGroup().addComponent(activityInformationField));
		this.setVerticalGroup(this.createSequentialGroup().addComponent(deleteButton));
		
		updateActivity(activity);
	}

	public void updateActivity(ActualActivity activity)
	{
		String textFieldText = activity.toString();
		activityInformationField.setText(textFieldText);
	}

	private void removeActivity()
	{
		mainScreen.removeGroupLayout(this);
	}
}
