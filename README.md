# Asteroids-Game
## CSC 133 Object Oriented Computer Graphics Programming // Fall 2017
This game was built using Codename one with eclipse. A1Prj, A2Prj, and A3Prj are the iterations of the program. Each version had additional capabilities compared to the previous. A2Prj and A3Prj contain pdf files with the instructions for the assignment. The final version of the game included sound, a points view with functioning score counter, a command menu on the left, key binding, and a functioning gameplay. 

To run the game, run the following command from the project directory:
“java -cp dist\A3Prj.jar;JavaSE.jar com.codename1.impl.javase.Simulator com.mycompany.a3.Starter"

### A1Prj
All assignment specifications were followed for the most part. When user loses a game, it does not restart or quit unless user specifies with commands. Also, refueling and crashes do not happen based on location, commands need to be specified by the player. Entering a blank command repeatedly will give an error. Print display command is implemented in the game world but forgot to add it to game class.

### A2Prj
Changed: keys to do commands, you can find a list of working keys in the help menu. Also, have not fully implemented the methods to verify that object created is within boundaries. When lives reaches 0, user is prompted to start a new game or quit.

### A3Prj
All the functions work, except sometimes the sound on/off mismatches with the previous settings after play/pause.
Tested and functioning on ipad3_os7.skin, on a mac computer


