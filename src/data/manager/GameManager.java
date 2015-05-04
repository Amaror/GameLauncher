package data.manager;

import java.io.File;
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
	
	public void loadSteamGames(){
		for(String s: SteamDir) {
			File folder = new File(s);
			File[] filelist = folder.listFiles();
			for(File f: filelist){
				String filename = f.getName().toString();
				if(filename.length() > 10){
					if(filename.substring(0, 11).equals("appmanifest")){
						System.out.println(filename);
						readFile(f);
					}
				}				
			}
		}
	}
	
	private void readFile(File f){
		
	}

}
