# SportsLadder
Repo for the ping pong ladder in the office

Rules:

Challenge System Rules

1.	Select Opponents for Challenge
A player may challenge the 3 players ahead on the ladder, plus 3 players behind the challenger (if there are any).
Challenges are mandatory. 

2.	Game Format
Best 2 of 3 if playing to 21 points, or, best 3 of 5 if playing to 11 points.  Players should agree before playing.  Other standard rules apply. 

3.	Game Location
The match may be held at any location and time agreed to by both participants. Matches are encouraged to be held at the office.

4.	Challenge Mechanism
A challenge can be delivered verbally or by any other means like email/messenger.  The match should be schedule within 5 working days unless there are other conflict like out-of-office or injury.  
A challenger can only have one outstanding challenge at any time.  
If anyone fails to show up or complete a match without valid reason, it will be considered as losing the match. 
A challenge can only be dismissed if both player agree. 

5.	Challenge Results
If the lower ranked player wins, the lower ranked player moves above the higher ranked player.
For example: 
Before challenge ranking: 1- Player A, 2 – Player B, 3 – Player C
Challenge result: C def. A
Updated Rankings: 1. Player C, 2- Player A, 3- Player B
If the higher ranked player wins, no changes shall be made to the ladder.

6.	Rechallenges
If a player loses a match to another player, he or she can immediately rechallenge but the other player can reject the immediate challenge.  Once rejected, he or she will have to wait for more than 5 working days to issue another challenge the same player.  

7.	Unranked Players
For unranked players interested in the challenge ladder, they are permitted to challenge one of the bottom 3 positions on the ladder in order to join. If they win the match, they may take the position above the victor. If they lose, they are added to the bottom of the ladder.

8.	Maintenance of the Ladder
The ladder is posted in the ping pong room.  Anyone in the ladder system can change the placement based on the results of challenges.  

9.	Honor Code
Players are expected to be honest and respect the rules of the system. The first time a player is found cheating the player shall be demoted 3 places. The second violation will lead to a ban of the player from the challenge ladder for a month and public shaming in the office.  

---

Used the following tutorials to get started:

Spring boot: http://javabeat.net/spring-data-jpa/

Spring & angular integration: https://dzone.com/articles/angular-2-and-spring-boot-development-environment

How to run backendcode:

1. cd backend-sports-ladder
2. mvn spring-boot:run
3. Server will be running on localhost:8080/api

How to run frontend code:
1. Download and Install node from https://nodejs.org/en/
2. cd frontend-sports-ladder
3. npm start
4. Code will be running on localhost:4200
5. All requests going to localhost:4200/api will be redirected to localhost:8080/api See proxy.conf.json
