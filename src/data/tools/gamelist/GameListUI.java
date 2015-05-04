package data.tools.gamelist;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.materials.Game;

public class GameListUI {
	
	//UI Elements
	private JFrame Frame;
	private JPanel GameListPanel;
	private JPanel GameInformationPanel;
	private JList GameList;
	private JLabel GameName;
	private JButton GameStartButton;
	//Data
	private GameList GameListTool;
	private DefaultListModel ListModel;
	
	public GameListUI(GameList gamelistobject){
		ListModel = new DefaultListModel<String>();
		GameListTool = gamelistobject;
		Frame = new JFrame("Game Launcher");
		
		//Setup Game List Panel
		GameListPanel = new JPanel();
		GameList = new JList<String>(ListModel);
		GameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GameList.setLayoutOrientation(JList.VERTICAL);
		GameList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				GameListTool.SelectionChanged();
			}
		});
		JScrollPane listScroller = new JScrollPane(GameList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		GameListPanel.add(GameList);
		
		//Setup Game Information Panel
		GameInformationPanel = new JPanel(new BorderLayout());
		GameName = new JLabel("Game Name");
		GameStartButton = new JButton("Start Game");
		GameStartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameListTool.ButtonPressed();
			}
		});
		GameInformationPanel.add(GameName, BorderLayout.NORTH);
		GameInformationPanel.add(GameStartButton, BorderLayout.CENTER);
		
		//Add Panels
		Frame.setSize(500, 250);
		Frame.getContentPane().setLayout(new BorderLayout());
		Frame.getContentPane().add(GameListPanel, BorderLayout.WEST);
		Frame.getContentPane().add(GameInformationPanel, BorderLayout.EAST);
		Frame.setVisible(true);
	}
	
	public int getSelectedIndex(){
		return GameList.getSelectedIndex();
	}
	
	public JLabel getGameName(){
		return GameName;
	}
	
	public JList getGameList(){
		return GameList;
	}
	
	public void addListElement(String gamename){
		ListModel.addElement(gamename);
		Frame.repaint();
	}

}
