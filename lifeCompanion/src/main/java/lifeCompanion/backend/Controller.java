package lifeCompanion.backend;

import java.util.Date;
import java.util.List;

public class Controller
{
	ActivityCollection activityCollection;
	List<ActualActivity> actualActivityList;

	Date currentDate;
	
	List<User> userList;
	User currentUser;
	
	public Controller()
	{
		currentDate = new Date();
		activityCollection = new ActivityCollection(); //load existing activity Collection if available
	}
	
	public boolean addActivity(Activity activity)
	{
		return activityCollection.addActivity(activity);
	}
	
	public void removeActivity(String name)
	{
		activityCollection.removeActivity(name);
	}
	
	public Activity getActivityByName(String name)
	{
		return activityCollection.getActivityByName(name);
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
	
	public ActivityCollection getActivityCollection()
	{
		return activityCollection;
	}
	
	public void addActualActivity(ActualActivity actualActivity)
	{
		actualActivityList.add(actualActivity);
	}
	
	public Date getCurrentDate()
	{
		return currentDate;
	}

	public void setCurrentDate(Date currentDate)
	{
		this.currentDate = currentDate;
	}
}
