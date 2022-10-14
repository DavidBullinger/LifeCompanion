package lifeCompanion.backend;

import org.junit.Test;

import lifeCompanion.frontend.AddAcitivityWizard;
import lifeCompanion.frontend.CreateActivityWizard;
import lifeCompanion.frontend.MainScreen;

public class FrontendCreationTest
{
	IController controller;
	
	//Tests if the Frontend together with the backend can be created
	@Test                                               
    public void testMainScreenCreation() 
	{
		controller = new Controller();
		MainScreen mainScreen = new MainScreen(this.controller);
    }
	
	@Test
	public void testAddActivityWizardCreation()
	{
		controller = new Controller();
		MainScreen mainScreen = new MainScreen(controller);
		AddAcitivityWizard addActivityWizard = new AddAcitivityWizard(controller, mainScreen);
	}
	
	@Test
	public void testCreateActivityWizardCreation()
	{
		controller = new Controller();
		MainScreen mainScreen = new MainScreen(controller);
		CreateActivityWizard createActivityWizard = new CreateActivityWizard(controller);
	}
}
