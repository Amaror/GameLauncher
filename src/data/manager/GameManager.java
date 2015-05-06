package data.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import data.materials.Game;

public class GameManager {
	
	private List<Game> GamesList= new ArrayList<Game>();
	public List<String> SteamDir = new ArrayList<String>();
	public List<String> OriginDir = new ArrayList<String>();
	private List<String> CustomDir = new ArrayList<String>();
	
	public GameManager(){
		
	}
	
	public List<Game> getGamesList(){
		return GamesList;
	}
	
	public Game getGame(int id){
		return GamesList.get(id);
	}
	
	public void loadDirectories(){
		try {
			Scanner scan = new Scanner(new FileReader("./src/data/materials/GameLauncherConfig.txt"));
			String text;
			String directory = "";
			SteamDir.clear();
			OriginDir.clear();
			boolean readSteam = false;
			boolean readOrigin = false;
			while(scan.hasNext()){
				text = scan.next();
				if(text.equals("SteamDir")){
					directory = scan.next();
					readSteam = true;
				}
				if(text.equals("OriginDir")){
					directory = scan.next();
					readOrigin = true;
				}
				if(text.equals("stop")){
					if(readOrigin){OriginDir.add(directory); readOrigin = false;}
					if(readSteam){SteamDir.add(directory); readSteam = false;}
				}
				if(readSteam && !text.equals("SteamDir")){
					directory = directory + " " + text;
				}
				if(readOrigin && !text.equals("OriginDir")){
					directory = directory + " " + text;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadSteamGames(){
		for(String s: SteamDir) {
			File folder = new File(s);
			File[] filelist = folder.listFiles();
			for(File f: filelist){
				String filename = f.getName().toString();
				if(filename.length() > 10){
					if(filename.substring(0, 11).equals("appmanifest")){
						readSteamFile(f, s, "Steam");
					}
				}				
			}
		}
	}
	
	public void loadOriginGames(){
		for(String s: OriginDir) {
			File folder = new File(s);
			File[] filelist = folder.listFiles();
			for(File f: filelist){
				readGames(f, s);
			}
		}
	}
	
	public void loadCustomGames(){
		for(String s: CustomDir) {
			File folder = new File(s);
			File[] filelist = folder.listFiles();
			for(File f: filelist){
				readGames(f, s);
			}
		}
	}
	
	private String[] readSteamFile(File f, String s, String system){
		String name = "";
		String appid = "";
		String location = s + "/common/";
		List<String> namecomponents = new ArrayList<String>();
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
					namecomponents.add(name);
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
					namecomponents.add(text);
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
		Game game = new Game(name, location, system);
		game.setAppID(Integer.parseInt(appid));
		GamesList.add(game);
		return (String[])namecomponents.toArray(new String[namecomponents.size()]);
	}
	
	private void readGames(File f, String s){
		String name = f.getName().toString();
		StringTokenizer st = new StringTokenizer(name);
		List<String> namecomponents = new ArrayList<String>();
		while(st.hasMoreTokens()){
			namecomponents.add(st.nextToken());
		}
		File executable = findExecutable((String[])namecomponents.toArray(new String[namecomponents.size()]), f.getPath());
		if(executable == null){
			File[] filelist = f.listFiles();
			for(File f2: filelist){
				if(f2.getName().toString().substring(0, 3).equals("bin")){
					executable = findExecutable((String[])namecomponents.toArray(new String[namecomponents.size()]), f2.getPath());
				}
			}
		}
		Game game = new Game(name, f.getPath(), "Origin");
		game.setExecutable(executable);
		GamesList.add(game);
	}
	
	private File findExecutable(String[] namecomponents, String location){
		List<File> executables = new ArrayList<File>();
		File folder = new File(location);
		File[] filelist = folder.listFiles();
		for(File f: filelist){
			String filename = f.getName().toString();
			if(filename.length() > 3){
				if(filename.substring((filename.length()-3)).equals("exe")){
					executables.add(f);
				}
			}
		}
		int[] executablescore = new int[executables.size()];
		for(int i: executablescore){
			i = 0;
		}
		for(int x = 0; x < executables.size(); x++){
			String filename = executables.get(x).getName().toString();
			for(String s: namecomponents){
				if(filename.contains(s)){
					executablescore[x]++;
				}
			}
		}
		int max = -1;
		for(int x = 0; x < executables.size(); x++){
			if(max == -1 || executablescore[x] > executablescore[max]){
				max = x;
			}
		}
		if(!(max == -1)){
			return executables.get(max);
		}
		return null;
	}

}
