package lifeCompanion.frontend;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lifeCompanion.backend.Activity;
import lifeCompanion.backend.IController;

public class CreateActivityWizard extends JFrame
{
	Container pane;
	
	GroupLayout layout;
	JTextField nameText;
	JTextField descriptionText;
	JButton confirmButton;
	IController controller;
	
	
	public CreateActivityWizard(IController controller)
	{
		this.controller = controller;
		pane = getContentPane();
		LayoutManager layoutManager = new FlowLayout();
		pane.setLayout(layoutManager);
		layout = new GroupLayout(pane);
		setSize(500, 300);
		
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(80, 30));
		nameText.setEditable(true);
		nameText.setText("Name");
		
		descriptionText = new JTextField();
		descriptionText.setPreferredSize(new Dimension(80, 30));
		descriptionText.setEditable(true);
		descriptionText.setText("Description");
		
		confirmButton = new JButton("Confirm");
		confirmButton.setPreferredSize(new Dimension(90,40));
		confirmButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				addActivity();

			}
		});
		layout.setAutoCreateContainerGaps(true);
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(nameText));
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(descriptionText));
		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(confirmButton));
	}
	
	private void addActivity()
	{
		Activity activity = new Activity(nameText.getText(), descriptionText.getText());
		if(!controller.createActivity(activity))
		{
			JOptionPane.showMessageDialog(this, "This acitivty already exists. Please choose another name or close this Wizard to abort the Activity Creation.");
			return;
		}
		JOptionPane.showMessageDialog(this, "Activity created successfully, please close the Wizard if you are done or add another activity");
	}
}
