-- noinspection SqlNoDataSourceInspectionForFile

-- add players
INSERT INTO player (id, name, rank)
VALUES (1, 'Duane', 1);

INSERT INTO player (id, name, rank)
VALUES (2, 'Chris Diehl', 2);

INSERT INTO player (id, name, rank)
VALUES (3, 'Felipe Leite', 3);

INSERT INTO player (id, name, rank)
VALUES (4, 'Michael Valdes', 4);

-- add challenges
INSERT INTO challenge (id, startdate, status, challengerId, defenderId)
VALUES
  (1, 'aced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e2080c111e223731a38078', 1, 3, 1);

-- add users
INSERT INTO user (id, username, email, password, playerId, role, enabled)
VALUES (1, 'duane', 'duane@ping.pong', '$2a$10$zHDN6yxHHEDtYrieTbajLehhXNYlzj6qU8fZw0fXTCbZcsMgPoMBm', 1, 'Admin', 'true');

SELECT * FROM user;
