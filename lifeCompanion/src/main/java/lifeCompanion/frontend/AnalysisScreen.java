package lifeCompanion.frontend;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lifeCompanion.backend.Activity;
import lifeCompanion.backend.ActivityCollection;
import lifeCompanion.backend.ActualActivity;
import lifeCompanion.backend.Day;
import lifeCompanion.backend.DayCollection;

public class AnalysisScreen extends JFrame
{
	Container pane;
	JTextArea analysisText;

	public AnalysisScreen(DayCollection dayCollection, ActivityCollection activityCollection) throws HeadlessException
	{
		setTitle("Analysis Screen");
		setSize(800, 500);
		setLocationRelativeTo(null);
		pane = getContentPane();
		LayoutManager layout = new FlowLayout();
		pane.setLayout(layout);
		
		analysisText = new JTextArea();
		//analysisText.setPreferredSize(new Dimension(80, 30));
		analysisText.setEditable(false);
		pane.add(analysisText);
		
		StringBuilder analysisStringBuilder = new StringBuilder();
		analysisStringBuilder.append("Average Score for your activities are as follows:");
		analysisStringBuilder.append("\n");
		
		for (Activity activity : activityCollection.getActivityList())
		{
			String currentLine = makeActivityStatistic(activity, dayCollection);
			if(currentLine == null)
			{
				continue;
			}
			analysisStringBuilder.append(currentLine);
			analysisStringBuilder.append("\n");
		}
		
		analysisText.setText(analysisStringBuilder.toString());
	}
	
	private String makeActivityStatistic(Activity activity, DayCollection dayCollection)
	{
		float[] ratings = dayCollection.getActivityAnalysis(activity);

		if(ratings.length == 0)
		{
			return activity.getName() + ":\r\nPhysical Score: -\r\nHappiness Score: -";
		}
		
		float averagePhysicalRating = ratings[0];
		float averageHappinessRating = ratings[1];
		
		return activity.getName() + ":\r\nPhysical Score: " + averagePhysicalRating + "\r\nHappiness Score: " + averageHappinessRating;
	}
}
