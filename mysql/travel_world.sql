create schema travel_world;

create table korisnik
(
	ime varchar(20) not null,
	prezime varchar(35) not null,
	email varchar(50) not null,
	biografija varchar(300) not null,
	datum_rodjenja varchar(40) not null,
	korisnicka_grupa int not null,
	korisnicko_ime varchar(20) primary key,
	lozinka varchar(140) not null
);

create table putopis
(
	id int primary key,
	naziv varchar(30) not null,
	datum varchar(40) not null,
	mjesto varchar(50) not null,
	tekst_putopisa varchar(1000) not null,
	korisnicko_ime varchar(20),
	constraint FK_korisnik_putopis
	foreign key (korisnicko_ime)
	references korisnik(korisnicko_ime)
);

create table slika
(
	id int primary key,
	naziv varchar(50) not null,
	datum varchar(40) not null,
	mjesto varchar(40) not null,
	opis varchar(50),
	putanja varchar(100) not null,
	korisnicko_ime varchar(20),
	constraint FK_korisnik_slika
	foreign key (korisnicko_ime)
	references korisnik(korisnicko_ime)
);