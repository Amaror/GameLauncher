package data.materials;

public class Game {
	
	private String Name;
	private int AppID;
	private String Location;
	private String System;
	
	public Game(String name, int appid, String location, String system){
		Name = name;
		AppID = appid;
		Location = location;
		System = system;
	}
	
	public String getName() {
		return Name;
	}
	
	public int getAppID(){
		return AppID;
	}
	
	public String getLocation(){
		return Location;
	}
	
	public String getSystem(){
		return System;
	}

}
