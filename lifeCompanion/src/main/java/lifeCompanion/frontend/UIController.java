package lifeCompanion.frontend;

public class UIController
{
	MainScreen mainScreen;
	public UIController()
	{
		mainScreen = new MainScreen(this);
		mainScreen.setVisible(true); 
	}
}
