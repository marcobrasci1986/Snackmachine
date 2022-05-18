CREATE TABLE Snackmachine
(
    id            long PRIMARY KEY,
    fiftyCentCoin int NOT NULL default 0,
    oneEuroCoin   int NOT NULL default 0,
    twoEuroCoin   int NOT NULL default 0,
    fiveEuroBill  int NOT NULL default 0,
    tenEuroBill   int NOT NULL default 0
);

CREATE TABLE Snack
(
    id   long PRIMARY KEY,
    name varchar NOT NULL
);

CREATE TABLE Slot
(
    id             long PRIMARY KEY,
    quantity       int     NOT NULL,
    price          decimal NOT NULL,
    position       int     NOT NULL,
    fk_snackmachine int     NOT NULL,
    fk_snack        int     NOT NULL,
    CONSTRAINT fk_snackmachine foreign key (fk_snackmachine) references Snackmachine,
    CONSTRAINT fk_snack foreign key (fk_snack) references Snack

);

