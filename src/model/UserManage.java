package model;

import java.util.ArrayList;

public class UserManage {
	public ArrayList<User> userList;
	public UserManage() {
		this.userList = new ArrayList<User>();
	}

	public UserManage(ArrayList<User> userList) {
		this.userList = userList;
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
	
	public void sua(User ngDung1, User ngDung2) {
		this.userList.remove(ngDung1);
		this.userList.add(ngDung2);
	}
	
	public void chen(User ngDung) {
		this.userList.add(ngDung);
	}
	
	public void xoa(User ngDung) {
		this.userList.remove(ngDung);
	}
		public boolean kiemtatontai(User ngDung) {
		for (User user : userList) {
			if(ngDung.getMasove() == user.getMasove())
				return true;
		} return false;
	}
	
	
	  
}
