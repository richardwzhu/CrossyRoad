# Y2020_Zhu_Richard_Period_1_Individual_Project
# README #

**Name:**	Richard Zhu

**Period:**	1

**Game Title:** Cross The Road

## Game Proposal ##

I want to make a game that is a clone of Crossy Road. The original game is a mobile app where touch input is used to navigate the character. However, I will be utilizing WASD controls to simulate movement, which will be shifts in the character instead of hops. Otherwise, the game mechanics should be identical.

Game Controls:

+ WASD controls on the keyboard are used to move the character up, left, down, and right.

Game Elements:

+ Hops in the original game are replaced by shifts of the character (up, left, down, right) across one tile because I don't think I can replicate the same effect
+ Different terrain and barriers to cross
	+ Cars passing by 
		+ Different speeds and directions
	+ Trees, bushes, etc. will act as barriers to make it more difficult for the player to navigate and stay alive
	+ Types of terrain include
		+ Regular road with cars 
		+ Rivers with logs that can be used as steps
		+ Train tracks where a train passes by at different time intervals 
+ Just like in the original game, if the player is stationary for an extended period of time, they will lose
	+ This forces continuous movement and engagement
	+ As the game progresses, this period of time will shrink slowly to increase difficulty

How to Win:

+ This game will continue for as long as the player is able to stay alive. The goal in this game is to achieve the highest score and beat your previous record.

## Link Examples ##
Provide links to examples of your game idea.  This can be a playable online game, screenshots, YouTube videos of gameplay, etc.

+ [Crossy Road](https://www.youtube.com/watch?v=Out73NMtuMY)

## Teacher Response ##

**Approved**

This is a perfect kind of game for our two-week project.  And I think you CAN implement hopping by using a short little JavaFX transition effect.  For example, when the user presses a direction to go, you can create a mashup of these effects all running at once:
 - ScaleTransition enlarges the player then returns them back to normal size (lools like it's zooming in then out)
 - TranslateTransition moves the player by one hop
 As long as these happen pretty quickly, it will look cool and won't cause too much trouble with collision detection.
 If you just make the player slide along, it's going to look and feel strange in my opinion.
 
 Otherwise, you game idea is solid as long as it's implemented well.  On another hand, I can see this game falling into an easy-to-code one-screen game that doesn't have much going on.  Just depends on how you approach it.
 

## Class Design and Brainstorm ##

Put all your brainstorm ideas, strategy approaches, and class outlines here

## Development Journal ##

Every day you work, keep track of it here.

**Friday May 22 (4 hours)**

Goal:  Create all the necessary classes and elements for the game. Begin adding the basic structure of the game (terrain, different types of obstacles, character movement, etc.).

Work accomplished:  Today, I spent a lot of time brainstorming how I was going to fit all the components together. The biggest issue I am working on right now is how I should create the map of the game (the rows of all the different roads with moving and stationary obstacles on each of them). I have coded the basic functionality and necessary methods for most of the individual components, however, because I can't use an ImageView object for each road object(currently I'm using a Rectangle object), there are issues with adding them to my CrossyWorld because they are not Actor objects.

**Monday May 25 (3 hours)**

Goal:  Complete game logic and begin to add sprites.

Work accomplished:  Today, I spent about the first two hours working on my game's logic. I resolved my previous issue with the road not being able to be an ImageView object after discovering GIMP. I'm currently having issues with my World subclass (CrossyWorld) and my Game driver class, which isn't displaying anything on the stage. I suspect my add() function that creates the map in the CrossyWorld class is cuasing issues. Things are still pretty scattered apart (I started a bunch of different things at once), but I'm starting to see progress. I also added about 15 new sprites that I edited through GIMP and wrote the code that sets each object to the specific image I need.

**Tuesday May 26 (5 hours)**

Goal:  Complete game logic/player interactions and begin to add scene switching and animations.

Work accomplished:  Today, I was able to properly display my map (grass, roads, rivers) with the correct generation of moving and stationary obstacles. I also worked out most of the character interactions with the obstacles (character cannot move in a direction if there are stationary obstacles)(if a player touches a moving car, the game is over) except the player interaction with moving logs on the river. I also added scene switching and a few effects and animations that make the game smoother and more enjoyable. One of these was the ScaleTransition effect during the character's movement, creating a more realistic jumping translation.

**Thursday May 28 (6 hours)**

Goal:  Complete game logic and player interaction with log, add score counting, improve collision detecting, add/finish scene switching.

Work accomplished:  First, I attempted to optimize my collision detecting mechanism (character against stationary and moving obstacles). I tried a few different approaches, all of which I committed to GitHub before scrapping (if it didn't work), just in case I were to return to the idea. I ended up improving marginally on my prior detecting method. I also finished my character - obstacle interactions after coding the character and river/log interaction. I added score counting during the running game, however, my game over page still doesn't display to correct final score. I also added a proper instruction scene and game over scene. My game over scene's 'back to main menu' button works, however, the 'replay' button is only somewhat functional.

**Friday May 29 (3 hours)**

Goal:  Finish and polish all game mechanics, fix scene switching ('replay' button in game over scene) and score display, create scrolling effect (a continuous map).

Work accomplished:  First, I resolved an issue with the game over screen not properly displaying the final score by re-organizing my game logic within the Game driver class. I then attempted to create a scrolling effect similar to the one in the Crossy Road app, however, I had no success doing this. My solution to this issue was to reset the player to the bottom of the map (maintaining the same x-pos) after they reached the top of the current map and generate a new map, creating a 'continuous' map feel. I ran into some issues with the score and player not showing up on the new map, but utilizng the toFront() method quickly resolved this problem. The toFront() method also improved my overall gameplay by allowing the character to constantly appear on top of the moving obstacles (logs, cars), which is more realistic. I also managed to fix my 'replay' button to correctly reset and generate a new map by tracing and debugging my code (it was awfully tedious).
***
***
