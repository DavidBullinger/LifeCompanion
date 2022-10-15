package lifeCompanion.frontend;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import lifeCompanion.backend.Activity;
import lifeCompanion.backend.ActivityCollection;
import lifeCompanion.backend.DayCollection;
import lifeCompanion.backend.IActivityStatistic;
import lifeCompanion.backend.StatisticData;

public class StatisticsScreen extends JFrame
{
	Container pane;
	JTextArea statisticsText;
	JButton buttonStatisticChange;
	
	IActivityStatistic activityStatistic;

	public StatisticsScreen(DayCollection dayCollection, ActivityCollection activityCollection, IActivityStatistic activityStatistic) throws HeadlessException
	{
		this.activityStatistic = activityStatistic;
		setTitle("Analysis Screen");
		setSize(800, 500);
		setLocationRelativeTo(null);
		pane = getContentPane();
		LayoutManager layout = new FlowLayout();
		pane.setLayout(layout);
		statisticsText = new JTextArea();
		statisticsText.setEditable(false);
		pane.add(statisticsText);
		
		StringBuilder statisticsStringBuilder = new StringBuilder();
		statisticsStringBuilder.append(activityStatistic.getStatisticName());
		statisticsStringBuilder.append("\n");
		statisticsStringBuilder.append(getStatisticString(activityCollection, dayCollection));
		
		statisticsText.setText(statisticsStringBuilder.toString());
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
}
