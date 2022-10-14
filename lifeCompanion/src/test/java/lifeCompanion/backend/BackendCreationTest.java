package lifeCompanion.backend;

import org.junit.Test;

public class BackendCreationTest
{
	Controller controller;
	
	//Tests if the Backend can be created
	@Test                                               
    public void testCreateController() 
	{
        controller = new Controller();
    }
}
