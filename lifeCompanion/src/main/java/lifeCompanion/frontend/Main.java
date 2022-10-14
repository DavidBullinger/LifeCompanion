package lifeCompanion.frontend;

import lifeCompanion.backend.Controller;

public class Main
{
	public static void main(String[] args)
	{
		Controller controller = new Controller();
		MainScreen mainScreen = new MainScreen(controller);
	}
}
