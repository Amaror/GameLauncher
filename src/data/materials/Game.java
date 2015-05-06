package data.materials;

import java.io.File;

public class Game {
	
	private String Name;
	private int AppID;
	private String Location;
	private String System;
	private File Executable;
	
	public Game(String name, String location, String system){
		Name = name;
		Location = location;
		System = system;
	}
	
	public void setAppID(int appid){
		AppID = appid;
	}
	
	public void setExecutable(File executable){
		Executable = executable;
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
	
	public File getExecutable(){
		return Executable;
	}

}
