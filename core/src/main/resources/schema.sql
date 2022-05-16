CREATE TABLE Snackmachine
(
    id            long PRIMARY KEY,
    fiftyCentCoin int NOT NULL default 0,
    oneEuroCoin   int NOT NULL default 0,
    twoEuroCoin   int NOT NULL default 0,
    fiveEuroBill  int NOT NULL default 0,
    tenEuroBill   int NOT NULL default 0
);
