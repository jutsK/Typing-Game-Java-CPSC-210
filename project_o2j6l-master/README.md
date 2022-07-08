# Personal Project: Java Typing Game

## Proposal

**Functionality:**
- randomized word to type
- typing game
- game ends when word is entered incorrectly
- shows one word at a time, correct typed words add 1 to score, the goal is to have the highest score possible



The application that will be created for this project will be a simple typing game. The program will select 
randomly from a list of common words. There will be a text box where the user types the above
words. The current word will be displayed above the text box. If the typed word matches the random word, add 
1 to the score. If the typed word does not match the string in the test, the game ends and the score for the
game is displayed. The game continues until a wrong word is entered. Through the menu, the past scores are
accessible to view.


##User Stories
 - As a user, I want to be able to start a new typing game
 - As a user, I want to be able to see my most recent score right after ending a game
 - As a user, I want to be able to see a list of my past scores
 - As a user, I want to be able to see the average of my past scores
 - As a user, I want to be able to add multiple game scores to the list of past scores

 - As a user, I want to be able to see my high score
 - As a user, I want to be able to save my high score and my last 10 scores
 - As a user, I want to be given the option to load my past scores (last 10 scores, high score)


## Phase 4: Task 2
added Score to log: 0

Wed Nov 24 21:23:20 PST 2021

added Score to log: 26

Wed Nov 24 21:23:20 PST 2021

added Score to log: 6

Wed Nov 24 21:23:20 PST 2021

added Score to log: 26

Wed Nov 24 21:23:20 PST 2021

added Score to log: 7

Wed Nov 24 21:23:20 PST 2021

added Score to log: 2

Wed Nov 24 21:23:20 PST 2021

added Score to log: 7

Wed Nov 24 21:23:20 PST 2021

added Score to log: 4

Wed Nov 24 21:23:20 PST 2021

added Score to log: 21

Wed Nov 24 21:23:20 PST 2021

added Score to log: 1

Wed Nov 24 21:23:50 PST 2021

added Score to log: 16

Wed Nov 24 21:23:53 PST 2021

added Score to log: 1

Wed Nov 24 21:23:58 PST 2021

added Score to log: 2

Wed Nov 24 21:23:58 PST 2021

Removed first score: 6


## Phase 4: Task 3
To improve on this project, I would maybe split the GUI class into multiple classes, because the way
it is now, there are many methods and the class has become somewhat saturated. By splitting it up, 
readability should increase, and it will be easier to find what we're looking for. I might also be able
to remove the Writable interface, as the methods in Score and PastScores would work the same without 
overriding it. I would also add another class for the high score rather than storing it as the 0th
index in pastScores, as this might minimize confusion later on and makes some of the methods more
straightforward.