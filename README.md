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

**Date (time spent)**

Goal:  What are you trying to accomplish today?

Work accomplished:  Describe what you did today and how it went.

**Date (time spent)**

Goal:  What are you trying to accomplish today?

Work accomplished:  Describe what you did today and how it went.

***
***
