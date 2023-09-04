# Calorie Tracker App

## Application Details:

The proposed application will be a **Calorie Calculator and tracker**. Users of the application include health and fitness
enthusiasts as well as people that need to accurately determine the amount of food they should be consuming. This
project is of interest to me because I tend to keep a very close watch on the amount of calories I consume every day
and I have a good understanding of how over/under eating can affect the body.


## User Stories:
- As a user, I would like to find out how many calories I should be consuming daily based on my weight and height
- As a user, I would like to add foods that I have consumed during the day to find out how many calories they have
- As a user, I want to be able to select whether I'd like to gain, lose, or maintain my weight
- As a user, I would like a menu with convenient foods with their respective calories
- As a user, I would like to save my previous foods that I've eaten during the day (If I so choose)
- As a user, I would like to load my previous foods that I've eaten during the day from file (If I so choose)

## Instructions for Grader:
- Start by running WelcomeFrame.java
- After prompted with the splash screen, press the lets go button to the home page
- You can select either calculator, or tracker
- In the calculator page, you can enter all of your details and the program will return a calorie goal 
- Based on that goal, you may return to the home page and start tracking the calories you've had
- In the tracker page, you can enter a food along with its calories and add it to your total calories
- You can also save the calories you've had in this session, or load from a previous session through the button
- If you are tracking throughout the day/week, ENSURE you save before you leave this tracker page.

## Phase 4: Task 2

Sample of Events:
- Calculate a calorie goal using user's height, weight, age, sex, and goal
- Add a food to the tracker
- Add another food to the tracker
- Remove that food from the tracker
- Exit the program

Upon exiting the console will print the following:

BMR Calculated.
Calculated Calorie Goal
Food Added.
Food Added.
Food removed.

## Phase 4: Task 3

Overall I'm very happy with the way the project has turned out, however there are some design choices that I would
change if given the time. The most glaring change would be the fact that there is a lot of duplication in the code,
specifically in the UI. I would change that by adding a class that has key functionality and extending other classes, so 
they inherit those methods. This would allow me to  have fewer blocks of code and upgrade the code if needed much more 
efficiently. An example of this would be the printing of the event logs. Lastly, I would also change how the GUI looks
and feels by changing the colors of the buttons, using different cohesive fonts, and potentially adding gifs instead of
jpegs as my visual element.
