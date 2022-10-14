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
    public void testControllerCreation() 
	{
		MainScreen mainScreen = new MainScreen();
    }
	
	@Test
	public void testAddActivityWizardCreation()
	{
		MainScreen mainScreen = new MainScreen();
		//Da es hier nur um den Test der Erstellung geht wurde hier ein zusätzlicher Controller erstellt
		controller = new Controller();
		AddAcitivityWizard addActivityWizard = new AddAcitivityWizard(controller, mainScreen);
	}
	
	@Test
	public void testCreateActivityWizardCreation()
	{
		MainScreen mainScreen = new MainScreen();
		controller = new Controller();
		CreateActivityWizard createActivityWizard = new CreateActivityWizard(controller);
	}
}
