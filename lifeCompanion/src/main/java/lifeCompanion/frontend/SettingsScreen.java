package lifeCompanion.frontend;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import lifeCompanion.backend.ActivityCollection;
import lifeCompanion.backend.DayCollection;
import lifeCompanion.backend.IActivityStatistic;
import lifeCompanion.backend.IController;
import lifeCompanion.backend.StatisticByHoursDone;
import lifeCompanion.backend.StatisticByUses;
import lifeCompanion.backend.StatisticData;

public class SettingsScreen extends JFrame
{
	Container pane;
	JTextField statisticsText;
	JButton buttonAcceptSettings;
	JComboBox<String> dropDownStatisticChooser;
	
	IController controller;
	
	public static final String[] statisticOptions = {"by hours", "by uses"};
	
	IActivityStatistic activityStatistic;

	public SettingsScreen(IController controller) throws HeadlessException
	{
		this.controller = controller;
		setTitle("Analysis Screen");
		setSize(800, 500);
		setLocationRelativeTo(null);
		pane = getContentPane();
		LayoutManager layout = new FlowLayout();
		pane.setLayout(layout);
		statisticsText = new JTextField();
		statisticsText.setEditable(false);
		statisticsText.setText("Prefered Statistics Method:");
		
		
		dropDownStatisticChooser = new JComboBox<String>(statisticOptions);
		dropDownStatisticChooser.setPreferredSize(new Dimension(80, 30));
		dropDownStatisticChooser.setEditable(true);
		
		buttonAcceptSettings = new JButton("Save and exit");
		buttonAcceptSettings.setPreferredSize(new Dimension(120,40));
		buttonAcceptSettings.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				saveAndExit();
			}
		});
		
		pane.add(statisticsText);
		pane.add(dropDownStatisticChooser);
		pane.add(buttonAcceptSettings);
	}
	
	private String getStatisticString(ActivityCollection activityCollection, DayCollection dayCollection)
	{
		List<StatisticData> statisticList = activityStatistic.evaluate(activityCollection, dayCollection);
		StringBuilder statisticsStringBuilder = new StringBuilder();
		for (StatisticData statisticData : statisticList)
		{
			statisticsStringBuilder.append(statisticData.toString());
			statisticsStringBuilder.append("\n");
		}
		return statisticsStringBuilder.toString();
	}
	
	private void saveAndExit()
	{
		int dropDownIndex = dropDownStatisticChooser.getSelectedIndex();
		
		if(dropDownIndex == 0)
		{
			controller.setCurrentStatisticChoice(new StatisticByHoursDone());
		}
		
		else if(dropDownIndex == 1)
		{
			controller.setCurrentStatisticChoice(new StatisticByUses());
		}
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		
	}
}
