import java.util.Set;

public class MsgUser implements Comparable<MsgUser>{
	private Server server;
	private String name;
	private String password;
	MsgWindow myWindow;
	
	public MsgUser(Server s, String n, String p) {
		server = s;
		name = n;
		password = p;
		myWindow = null;
	}
	public void addBuddy(MsgUser u) {
		if (myWindow != null) {
			myWindow.addBuddy(u);
		}
	}
	public int compareTo(MsgUser other) {
		return this.name.compareToIgnoreCase(other.getName());
	}
	public boolean equals(Object other) {
		if (!(other instanceof MsgUser)) {
			throw new ClassCastException();
		}
		return this.name.equalsIgnoreCase(other.toString());
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public void openDialog(Set<MsgUser> buddies) {
		myWindow = new MsgWindow(this, buddies);
	}
	public void quit() {
		server.logout(this);
	}
	public void receiveMessage(String text) {
		if (myWindow != null) {
			myWindow.showMessage(text);
		}
	}
	public void removeBuddy(MsgUser u) {
		if (myWindow != null) {
			myWindow.removeBuddy(u);
		}
	}
	public String toString() {
		return name;
	}
}
