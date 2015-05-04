package data.tools.gamelist;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.DefaultListModel;

import data.manager.GameManager;
import data.materials.ApplicationParameters;
import data.materials.Game;

public class GameList {
	
	private GameListUI UI;
	//Temporary Data
	private GameManager gameManager;
	
	public GameList(GameManager manager){
		UI = new GameListUI(this);
		gameManager = manager;
		updateGameList();
	}
	
	public void ButtonPressed(){
		URI gamelaunch;
		try {
			gamelaunch = new URI("steam://rungameid/" + gameManager.getGame(UI.getSelectedIndex()).getAppID());
			Desktop.getDesktop().browse(gamelaunch);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void SelectionChanged(){
		UI.getGameName().setText(gameManager.getGame(UI.getSelectedIndex()).getName());
	}
	
	public void updateGameList(){
		for(Game g: gameManager.getGamesList()){
			UI.addListElement(g.getName());
		}
	}
}
