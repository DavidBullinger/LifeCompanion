package lifeCompanion.backend;

import org.junit.Test;

import lifeCompanion.frontend.AddAcitivityWizard;
import lifeCompanion.frontend.CreateActivityWizard;
import lifeCompanion.frontend.UIController;

public class FrontendCreationTest
{
	Controller controller;
	
	//Tests if the Frontend together with the backend can be created
	@Test                                               
    public void testControllerCreation() 
	{
		UIController uiController = new UIController();
    }
	
	@Test
	public void testAddActivityWizardCreation()
	{
		UIController uiController = new UIController();
		AddAcitivityWizard addActivityWizard = new AddAcitivityWizard(uiController);
	}
	
	@Test
	public void testCreateActivityWizardCreation()
	{
		UIController uiController = new UIController();
		CreateActivityWizard createActivityWizard = new CreateActivityWizard(uiController);
	}
}
