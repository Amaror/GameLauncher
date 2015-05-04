package data;

import data.manager.GameManager;
import data.materials.ApplicationParameters;
import data.tools.gamelist.GameList;

public class main {
	
	private static GameManager gamemanager = new GameManager();
	private static GameList gamelist;

	public static void main(String[] args) {
		gamemanager.SteamDir.add(ApplicationParameters.SteamDir + "/SteamApps");
		gamemanager.loadSteamGames();
		gamelist = new GameList(gamemanager);
	}

}
