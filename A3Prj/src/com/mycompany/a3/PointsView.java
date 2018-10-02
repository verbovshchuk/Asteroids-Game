package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class PointsView extends Container implements Observer
{
	private Label timeData;
	private Label livesData;
	private Label scoreData;
	private Label soundData;
	private Label missileData;


	public PointsView() 
	{ 
		Label livesLabel = new Label("Lives Left: ");
		Label scoreLabel = new Label("Score: ");
		Label timeLabel = new Label("Elapsed Time: ");		
		Label soundLabel = new Label("Sound: ");
		Label missileLabel = new Label("Missiles: ");		

		timeData = new Label("XXX");
		livesData = new Label("XXX");
		scoreData = new Label("XXX");
		soundData = new Label("XXX");
		missileData = new Label("XXX");

		timeLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeData.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeData.getAllStyles().setPadding(1,1,1,5);
		
		livesLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesData.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesData.getAllStyles().setPadding(1,1,1,5);
		
		scoreLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		scoreData.getAllStyles().setFgColor(ColorUtil.BLUE);
		scoreData.getAllStyles().setPadding(1,1,1,5);
		
		soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundData.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		missileLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		missileData.getAllStyles().setFgColor(ColorUtil.BLUE);
		missileData.getAllStyles().setPadding(1,1,1,5);
		
		add(scoreLabel);
		add(scoreData);
		add(timeLabel);
		add(timeData);
		add(livesLabel);
		add(livesData);
        add(missileLabel);
        add(missileData);
		add(soundLabel);
        add(soundData);

	}

	public void update(Observable o, Object arg) {

		IGameWorld gameWorld = (IGameWorld)o;
		timeData.setText(String.valueOf(gameWorld.getClock()));
		timeData.getParent().revalidate();

		livesData.setText(String.valueOf(gameWorld.getLives()));
		livesData.getParent().revalidate();

		missileData.setText(String.valueOf(gameWorld.getMissiles()));
		missileData.getParent().revalidate();

		scoreData.setText(String.valueOf(gameWorld.getScore()));
		scoreData.getParent().revalidate();

		if(gameWorld.getSound() == false){
			soundData.setText("OFF");
		}
		else{
			soundData.setText("ON");
		}
		soundData.getParent().revalidate();

	}	
}
