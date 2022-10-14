package lifeCompanion.frontend;

import javax.swing.GroupLayout;

import lifeCompanion.backend.ActualActivity;

public interface IOverlayingObject
{
	public void removeGroupLayout(GroupLayout layoutToDelete, ActualActivity actualActivity);
}
