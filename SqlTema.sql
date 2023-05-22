use schooldb;

create table if not exists clients
(CID int not null unique auto_increment primary key,
nume char(30) not null,
adresa  char(30) not null,
contact varchar(10) not null);

create table if not exists product 
(PID int not null unique auto_increment primary key,
numeProdus varchar(30) not null,
pret int not null,
stoc int not null);

create table if not exists orders 
(OID int not null unique auto_increment primary key,
cantitate int(11) not null,
CID int(11) not null,
PID int(11) not null,
FOREIGN KEY (CID) references clients(CID),
FOREIGN KEY (PID) references product(PID)
);

drop table orders;