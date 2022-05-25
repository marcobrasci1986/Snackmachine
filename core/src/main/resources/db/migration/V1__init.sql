CREATE TABLE Snackmachine
(
    id            bigint PRIMARY KEY,
    fiftyCentCoin int NOT NULL default 0,
    oneEuroCoin   int NOT NULL default 0,
    twoEuroCoin   int NOT NULL default 0,
    fiveEuroBill  int NOT NULL default 0,
    tenEuroBill   int NOT NULL default 0
);

CREATE TABLE Snack
(
    id   bigint PRIMARY KEY,
    name varchar NOT NULL
);

CREATE TABLE Slot
(
    id              bigint PRIMARY KEY,
    quantity        int    NOT NULL,
    price           float8 NOT NULL,
    position        int    NOT NULL,
    fk_snackmachine bigint NOT NULL,
    fk_snack        bigint NOT NULL,
    CONSTRAINT fk_snackmachine foreign key (fk_snackmachine) references Snackmachine,
    CONSTRAINT fk_snack foreign key (fk_snack) references Snack

);

CREATE TABLE Atm
(
    id            bigint PRIMARY KEY,
    moneyCharged  float8 NOT NULL default 0,
    fiftyCentCoin int    NOT NULL default 0,
    oneEuroCoin   int    NOT NULL default 0,
    twoEuroCoin   int    NOT NULL default 0,
    fiveEuroBill  int    NOT NULL default 0,
    tenEuroBill   int    NOT NULL default 0
);

CREATE TABLE HeadOffice
(
    id            bigint PRIMARY KEY,
    balance       float8 NOT NULL default 0,
    fiftyCentCoin int    NOT NULL default 0,
    oneEuroCoin   int    NOT NULL default 0,
    twoEuroCoin   int    NOT NULL default 0,
    fiveEuroBill  int    NOT NULL default 0,
    tenEuroBill   int    NOT NULL default 0
);

