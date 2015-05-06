package data.manager;

import data.tools.gamelistcontrol.GameListControl;

public class GUIManager {
	
	private GameManager gamemanager;
	private GameListControl gamelistcontrol;
	
	public GUIManager(){
		gamemanager = new GameManager();
		gamemanager.loadDirectories();
		gamemanager.loadSteamGames();
		gamemanager.loadOriginGames();
		gamelistcontrol = new GameListControl(gamemanager);
	}

}
