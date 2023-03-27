import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Server implements Login{
	private TreeMap<String, MsgUser> users;
	private TreeSet<MsgUser> loggedIn;
	public Server() {
		users = new TreeMap<String, MsgUser>();
		loggedIn = new TreeSet<MsgUser>();
	}
	public int addUser(String name, String password) {
		if (name.length() < 4 || name.length() > 10) {
			return -1;
		}
		else if (password.length() < 2 || password.length() > 10) {
			return -2;
		}
		else {
			for (Map.Entry<String, MsgUser> entry : users.entrySet()) {
				if (entry.getValue().getName().equals(name)) {
					return -3;
				}
			}
		}
		MsgUser user = new MsgUser(this, name, password);
		users.put(name, user);
		return 0;
	}
	public int login(String name, String password) {
		if (users.get(name) == null) {
			return -1;
		}
		if (password.length() < 2 || password.length() > 10) {
			return -2;
		}
		for (MsgUser u : loggedIn) {
			if (u.getName().equals(name)) {
				return -3;
			}
		}
		MsgUser user = users.get(name);
		for (MsgUser u : loggedIn) {
			u.addBuddy(user);
		}
		user.openDialog(loggedIn);
		loggedIn.add(users.get(name));
		return 0;
	}
	public void logout(MsgUser u) {
		for (MsgUser user : loggedIn) {
			user.removeBuddy(u);
		}
		loggedIn.remove(u);
	}
}
