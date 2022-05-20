INSERT INTO Snackmachine (id, fiftyCentCoin, oneEuroCoin, twoEuroCoin, fiveEuroBill, tenEuroBill)
VALUES (1, 1, 1, 1, 1, 1);

INSERT INTO Snack (id, name)
VALUES (1, 'Chocolate');
INSERT INTO Snack (id, name)
VALUES (2, 'Soda');
INSERT INTO Snack (id, name)
VALUES (3, 'Gum');


INSERT INTO Slot (id, quantity, price, position, fk_snackmachine, fk_snack)
VALUES (1, 10, 1.5, 1, 1, 1);
INSERT INTO Slot (id, quantity, price, position, fk_snackmachine, fk_snack)
VALUES (2, 10, 1, 2, 1, 2);
INSERT INTO Slot (id, quantity, price, position, fk_snackmachine, fk_snack)
VALUES (3, 10, 3.5, 3, 1, 3);


INSERT INTO Atm (id, moneyCharged, fiftyCentCoin, oneEuroCoin, twoEuroCoin, fiveEuroBill, tenEuroBill)
VALUES (1, 0, 1, 1, 1, 1, 1);


