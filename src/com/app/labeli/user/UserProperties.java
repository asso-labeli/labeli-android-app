package com.app.labeli.user;

public abstract class UserProperties {

	public static final int PERMISSION_ERROR = -2;
	public static final int PERMISSION_DENIED = -1;
	public static final int PERMISSION_OLD_USER = 0;
	public static final int PERMISSION_USER = 1;
	public static final int PERMISSION_ADMIN = 3;
	
	private static int PERMISSION_LEVEL;
	private static String LOGIN;
	
	public static int getPermissionLevel(){
		return PERMISSION_LEVEL;
	}
	
	public static void setPermissionLevel(int level){
		PERMISSION_LEVEL = level;
	}
	
	public static String getLogin(){
		return LOGIN;
	}
	
	public static void setLogin(String login){
		LOGIN = login;
	}
}
