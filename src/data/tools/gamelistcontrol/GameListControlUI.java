package data.tools.gamelistcontrol;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GameListControlUI {
	
	private JFrame Frame;
	private JPanel GameListPanel;
	private JPanel GameInformationPanel;
	private JList GameList;
	private JLabel GameName;
	private JButton GameStartButton;
	//Data
	private GameListControl GameListControl;
	private DefaultListModel ListModel;
	
	public GameListControlUI(GameListControl gamelistobject){
		ListModel = new DefaultListModel<String>();
		GameListControl = gamelistobject;
		Frame = new JFrame("Game Launcher");
		
		//Setup Game List Panel
		GameListPanel = new JPanel();
		GameList = new JList<String>(ListModel);
		GameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GameList.setLayoutOrientation(JList.VERTICAL);
		GameList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				GameListControl.SelectionChanged();
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
				GameListControl.ButtonPressed();
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
