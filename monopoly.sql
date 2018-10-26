
--
-- This SQL script builds a monopoly database, deleting any pre-existing version.
-- Edited by: Valeria Martinez
-- 10-19-2018
-- CS 262
-- @author kvlinden
-- @version Summer, 2015
--

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS propertyPlayerGame;
DROP TABLE IF EXISTS PlayerGame;
DROP TABLE IF EXISTS Game;
DROP TABLE IF EXISTS Player;
DROP TABLE IF EXISTS properties;



-- Create the schema.
CREATE TABLE Game (
	ID integer PRIMARY KEY,
	time timestamp
	);

CREATE TABLE Player (
	ID integer PRIMARY KEY,
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
	);

CREATE TABLE PlayerGame (
	gameID integer REFERENCES Game(ID),
	playerID integer REFERENCES Player(ID),
	score integer,
	playerCash money,
	playerLocation integer
	);

CREATE TABLE properties (
	ID integer PRIMARY KEY,
	name varchar(100)
	);

CREATE TABLE propertyPlayerGame (
	ID integer PRIMARY KEY,
	gameID integer REFERENCES Game(ID),
	playerID integer REFERENCES Player(ID),
	propertiesID integer REFERENCES properties(ID),
	hotels boolean,
	houses integer

	);


-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;
GRANT SELECT ON propertyPlayerGame TO PUBLIC;
GRANT SELECT ON properties TO PUBLIC;


-- Game(ID, time)
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2006-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2006-06-29 18:41:00');
INSERT INTO Game VALUES (4, '2018-10-18 18:41:00');
INSERT INTO Game VALUES (5, '2018-10-14 18:41:00');

-- Player(ID, emailAddress,name)
INSERT INTO Player(ID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Player VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Player VALUES (3, 'dog@gmail.edu', 'Dogbreath');

-- PlayerGame (gameID,playerID,score,playerCash, playerLocation)
INSERT INTO PlayerGame VALUES (1, 1, 0.00, 5.00,1);
INSERT INTO PlayerGame VALUES (1, 2, 0.00,8.25,25);
INSERT INTO PlayerGame VALUES (1, 3, 2350.00,14,22);
INSERT INTO PlayerGame VALUES (2, 1, 10000.00,40,16);
INSERT INTO PlayerGame VALUES (2, 2, 10.00,33,46);
INSERT INTO PlayerGame VALUES (2, 3, 500.00,25,11);
INSERT INTO PlayerGame VALUES (3, 2, 450.00,20,12);
INSERT INTO PlayerGame VALUES (3, 3, 5500.00,13,33);

-- properties(ID, name)
INSERT INTO properties VALUES (1, 'Boston');
INSERT INTO properties VALUES (2, 'Virginia Avenue');
INSERT INTO properties VALUES (3, 'States Avenue');

-- propertyPlayerGame(ID,gameID,playerID, propertiesID, hotels, houses)
INSERT INTO propertyPlayerGame VALUES (1, 1,1,1,FALSE,5);
INSERT INTO propertyPlayerGame VALUES (2, 2,2,2,TRUE,45);

SELECT * FROM PlayerGame;
SELECT * FROM propertyPlayerGame;
SELECT * FROM properties;


-------------------------------------------------------------
-----  LAB 08
----- Valeria Martinez (vam6)
----- CS 262
----- 10-26-18
--------------------------------------------------------------


-- EXERCISE 8.1
--- a. List of all games, ordered by date starting from the most recent
SELECT * FROM GAME
ORDER BY time ASC;


--- b. Retrieve all the games that occurred in the past week
--SELECT * FROM GAME

SELECT * FROM GAME WHERE time >= '2018-10-14 00:00:00'
ORDER BY time DESC;
 
--- c. Retrieve a list of players who have a (non-NULL) names

SELECT * FROM PLAYER WHERE name <> NULL; 


--- d. Retrieve a list of IDs of players who have some score larger than 2000
SELECT playerID FROM PlayerGame where score > 2000;



----- e. Retrieve a list of players who have a GMAIL account
SELECT * FROM Player where emailAddress LIKE '%gmail%';




----- EXERCISE 8.2 
--- MULTIPLE-TABLE QUERIES
-- a. Retrieve all "The King"'s game scores in decreasing order
SELECT score
FROM Player, PlayerGame
WHERE Player.ID = PlayerGame.playerID
AND Player.name = 'The King' 
ORDER BY score DESC;

--- b. Retrieve the name of the winner of the game played on 2006-06-28 13:20:00
SELECT name 
FROM Game, Player, PlayerGame
WHERE Game.time = '2006-06-28 13:20:00'
AND Game.ID = PlayerGame.GameID
AND PlayerGame.playerID = player.ID
ORDER BY score DESC
LIMIT 1;


---- c.So what does that P1.ID < P2.ID clause do in the last example query? 
---- If the players share the same name, then it(p1.id <p2.id) makes sure that the p1 and the p2 don't have the same ID 
----- and it also checks if one id is greater than the other one 


------- d. The query that joined the Player table to itself seems rather contrived. Can you think of a realistic situation in which you’d want to join a table to itself? 
--------- We would like to join a table to itself when we want to compare how often an event happens involving the same part of a table. For example, if I want to know how
--------- many time player1 won over player2, we would use the query SELECT P1.name, FROM Player AS P1, Player AS P2 ... 






































