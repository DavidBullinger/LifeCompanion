package lifeCompanion.backend;

import java.util.List;

public class Controller
{
	ActivityCollection activityCollection;
	List<User> userList;
	User currentUser;
	
	public Controller()
	{
		activityCollection = new ActivityCollection(); //load existing activity Collection if available
	}
	
	public void addActivity(String name, String description)
	{
		activityCollection.addActivity(name, description);
	}
	
	public void removeActivity(String name)
	{
		activityCollection.removeActivity(name);
	}
	
	private User getUserByName(String username)
	{
		for (User user : userList)
		{
			if(username.equals(user.getUserName()))
			{
				return user;
			}
		}
		return null;
	}
	
	public boolean createNewUser(String username)
	{
		if(getUserByName(username) == null)
		{
			userList.add(new User(username));
			currentUser = userList.get(userList.size()-1);
			return true;
		}
		return false;
	}
	
	public boolean deleteUser(String username)
	{
		User userToDelete = getUserByName(username);
		if(userToDelete != null)
		{
			userList.remove(userToDelete);
			return true;
		}
		return false;
	}
	
	public boolean loginUser(String username)
	{
		User userToLogin = getUserByName(username);
		if(userToLogin != null)
		{
			currentUser = userToLogin;
			return true;
		}
		return false;
	}

	public User getCurrentUser()
	{
		return currentUser;
	}
}
