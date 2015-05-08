package data.tools.OptionsMenu;

import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class OptionsMenuUI {
	
	private JFrame MainFrame;	
	private JTabbedPane MainPanel;
	private JPanel BottomPanel;
	private JPanel SteamPanel;
	private JPanel OriginPanel;
	private JPanel CustomPanel;
	private List<JTextField> SteamDir = new ArrayList<JTextField>();
	private List<JTextField> OriginDir = new ArrayList<JTextField>();
	private List<JTextField> CustomDir = new ArrayList<JTextField>();
	private JButton OkButton;
	private JButton CancelButton;
	
	public OptionsMenuUI(List<String> steamdir, List<String> origindir,List<String> customdir){
		MainFrame = new JFrame();
		
		SteamPanel = makeDirectoryPanel(steamdir, "Steam");
		OriginPanel = makeDirectoryPanel(origindir, "Origin");
		CustomPanel = makeDirectoryPanel(customdir, "Custom");
		BottomPanel = new JPanel();
		BottomPanel.setLayout(new FlowLayout());
		configureButtons();
		BottomPanel.add(OkButton);
		BottomPanel.add(CancelButton);
		BottomPanel.setBounds(new Rectangle(400, 70));
		
		MainPanel = new JTabbedPane();
		MainPanel.addTab("Steam", SteamPanel);
		MainPanel.addTab("Origin", OriginPanel);
		MainPanel.addTab("Custom", CustomPanel);
		MainPanel.setBounds(new Rectangle(400, 470));
		
		MainFrame.setSize(420, 560);
		MainFrame.add(MainPanel);
		MainFrame.add(BottomPanel);
	}
	
	public void showWindow(){
		MainFrame.setVisible(true);
	}
	
	private JPanel makeDirectoryPanel(List<String> directories, String system){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		for(String s: directories){
			JTextField textfield = new JTextField(s);
			textfield.setBounds(new Rectangle(200, 50));
			if(system.equals("Steam")){SteamDir.add(textfield);}
			if(system.equals("Origin")){OriginDir.add(textfield);}
			if(system.equals("Custom")){CustomDir.add(textfield);}
			panel.add(textfield);
		}
		JButton addButton = new JButton("add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField textfield = new JTextField("");
				textfield.setBounds(new Rectangle(200, 50));
				if(system.equals("Steam")){SteamDir.add(textfield);}
				if(system.equals("Origin")){OriginDir.add(textfield);}
				if(system.equals("Custom")){CustomDir.add(textfield);}
				panel.add(textfield);
				MainFrame.repaint();
			}
		});
		addButton.setBounds(new Rectangle(100, 50));
		panel.add(addButton);
		return panel;
	}
	
	private void configureButtons(){
		OkButton = new JButton("Ok");
		CancelButton = new JButton("Cancel");
		OkButton.setBounds(new Rectangle(100, 50));
		CancelButton.setBounds(new Rectangle(100, 50));
		OkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeConfig();
			}
		});
		CancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.setVisible(false);
			}
		});
	}
	
	private void writeConfig(){
		BufferedWriter writer = null;
		String text = "";
		String newline = System.getProperty("line.separator");
		for(JTextField t: SteamDir){
			if(!t.getText().equals("")){
				text = text + "SteamDir " + t.getText() + " stop" + newline;
			}
		}
		for(JTextField t: OriginDir){
			if(!t.getText().equals("")){
				text = text + "OriginDir " + t.getText() + " stop" + newline;
			}
		}
		for(JTextField t: SteamDir){
			if(!t.getText().equals("")){
				text = text + "CustomDir " + t.getText() + " stop" + newline;
			}
		}
		
		try{
		writer = new BufferedWriter(new FileWriter(new File("./src/data/materials/GameLauncherConfig.txt")));
		writer.write(text);
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
	}
}
