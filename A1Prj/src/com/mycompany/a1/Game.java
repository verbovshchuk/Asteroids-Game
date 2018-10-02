package com.mycompany.a1;

/* Pavel Verbovshchuk
 * CSC 133
 * A1Prj1
 * Initial copy of asteroids game, text based. 
 */

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form
{
	private GameWorld gw;
	
	public Game() 
	{
		gw = new GameWorld();//create world 
		gw.init();//initialize gameplay to empty world
		play();//begin gameplay
	}
	private void play()
	{
		Label myLabel=new Label("Enter a Command: "); //prompt for user command in form
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show(); //update form
		myTextField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				switch (sCommand.charAt(0)) 
				{
					case 'a':
						gw.createAsteroid();//put in code for add asteroid
						break;
					case 'b':
						gw.createSpaceStation();//code for add blinking space station
						break;
					case 's':
						gw.createShip();//code for add ship
						break;
					case 'i':
						gw.increaseSpeed();//increase speed of ship by small amount (+1)
						break;
					case 'd':
						gw.decreaseSpeed();//decrease speed of ship by small amount (-1), but don't go negative
						break;
					case 'l':
						gw.steerLeft();//turn left by a small amount
						break;						
					case 'r':
						gw.steerRight();//turn right by a small amount
						break;
					case 'f':
						gw.createMissile();//Fire missile. If no missiles, error message. Otherwise, create new missile.
						break;						
					case 'j':
						gw.jump(); //ship jumps to initial default position					
						break;						
					case 'n':
						gw.reloadMissiles();//load new supply of missiles into ship
						break;						
					case 'k':
						gw.killAsteroid();//missile struck and killed asteroid. Remove a missile and an asteroid and increment player's score by amount
						break;						
					case 'c':
						gw.shipCrashed();//remove ship and an asteroid and decrement count of lives left. If no lives, game over
						break;						
					case 'x':
						gw.asteroidsCollided();//remove two asteroids from game
						break;						
					case 't':
						gw.clockTick();//clock ticked. update moveable object positions, fuel level reduced by one, remove missiles out of fuel, space station toggles blinking light if count%blinkrate =0, elapsed game time incremembed by one
						break;						
					case 'p':
						gw.printDisplay();//print display giving current game state values
						break;						
					case 'm':
						gw.printMap();//print a map with current world state
						break;						
					case 'q': //confirm user's intent to quit, and then quit
						gw.quitAlert(); //display confirmation of intent to quit
						final TextField myTextField2=new TextField(); //make new input
						addComponent(myTextField2); //add new input to the form
						removeComponent(myTextField); //remove the previous text field to show new one
						show(); //update form
						myTextField2.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent evt2) 
							{
								String sCommand2=myTextField2.getText().toString();

								myTextField2.clear();
								
								if (sCommand2.charAt(0) == 'y')
										{
											gw.quit();
										}
								else if (sCommand2.charAt(0) == 'n') //restore to before quit
								{
									addComponent(myTextField);
									removeComponent(myTextField2);
									show();
								}

							}
						});
						break;								
				}
			}
		});
	}
}
