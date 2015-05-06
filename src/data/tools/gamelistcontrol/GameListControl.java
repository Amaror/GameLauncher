package data.tools.gamelistcontrol;

import java.awt.Desktop;
import java.net.URI;

import data.manager.GameManager;
import data.materials.Game;

public class GameListControl {
	
	private GameListControlUI UI;
	//Temporary Data
	private GameManager gameManager;
	
	public GameListControl(GameManager manager){
		UI = new GameListControlUI(this);
		gameManager = manager;
		updateGameList();
	}
	
	public void ButtonPressed(){
		
		if(gameManager.getGame(UI.getSelectedIndex()).getSystem().equals("Steam")){
			try {
				URI gamelaunch = new URI("steam://rungameid/" + gameManager.getGame(UI.getSelectedIndex()).getAppID());
				Desktop.getDesktop().browse(gamelaunch);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(gameManager.getGame(UI.getSelectedIndex()).getSystem().equals("Origin")){
			try {
				ProcessBuilder pb = new ProcessBuilder(gameManager.getGame(UI.getSelectedIndex()).getExecutable().getPath());
				pb.directory(gameManager.getGame(UI.getSelectedIndex()).getExecutable().getParentFile());
				Process p = pb.start();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
