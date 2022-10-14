package lifeCompanion.frontend;

import javax.swing.GroupLayout;

import lifeCompanion.backend.ActualActivity;
import lifeCompanion.backend.Day;

public interface IMainScreen
{
	public void addActivity(ActualActivity actualActivity);
	public void removeGroupLayout(GroupLayout layoutToDelete, ActualActivity actualActivity);
}
