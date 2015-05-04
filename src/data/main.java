package data;

import data.manager.GameManager;

public class main {
	
	private static GameManager gamemanager = new GameManager();

	public static void main(String[] args) {
		gamemanager.SteamDir.add("C:/Program Files (x86)/Steam/SteamApps");
		gamemanager.loadSteamGames();
	}

}
