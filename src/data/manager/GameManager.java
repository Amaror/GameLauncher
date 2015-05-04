package data.manager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import data.materials.Game;

public class GameManager {
	
	private List<Game> GamesList= new ArrayList<Game>();
	public List<String> SteamDir = new ArrayList<String>();
	
	public GameManager(){
		
	}
	
	public List<Game> getGamesList(){
		return GamesList;
	}
	
	public Game getGame(int id){
		return GamesList.get(id);
	}
	
	public void loadSteamGames(){
		for(String s: SteamDir) {
			File folder = new File(s);
			File[] filelist = folder.listFiles();
			for(File f: filelist){
				String filename = f.getName().toString();
				if(filename.length() > 10){
					if(filename.substring(0, 11).equals("appmanifest")){
						readFile(f, s);
					}
				}				
			}
		}
	}
	
	private void readFile(File f, String s){
		String name = "";
		String appid = "";
		String location = s + "/common/";
		boolean readname = false;
		boolean readlocation = false;
		try{
			Scanner scan = new Scanner(new FileReader(f));
			String text;
			while(scan.hasNext()){
				text = scan.next();
				//read Game ID
				if(text.equals("\"appid\"")){
					appid = scan.next();
				} 
				//Start reading Game Name
				else if(text.equals("\"name\"")){
					name = scan.next();
					readname = true;
				} 
				//Stop reading Game Name
				else if(text.equals("\"StateFlags\"")) {
					readname = false;
				} 
				//Start reading Game Location
				else if(text.equals("\"installdir\"")) {
					location = location + scan.next().substring(1);
					readlocation = true;
				}
				//Stop reading Game Location
				else if(text.equals("\"LastUpdated\"")){
					readlocation = false;
					break;
				}
				//read Game Name
				if(readname && !text.equals("\"name\"")){
					name = name + " " + text;
				} 
				//read Game Location
				if(readlocation && !text.equals("\"installdir\"")){
					location = location + " " + text;
				}
			}
			scan.close();
		} catch(Exception e){
			
		}
		name = name.substring(1, name.length()-1);
		appid = appid.substring(1, appid.length()-1);
		location = location.substring(0, location.length()-1);
		GamesList.add(new Game(name, Integer.parseInt(appid), location, "Steam"));
	}

}
