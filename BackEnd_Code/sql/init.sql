use rfid;

create table chemCAS if not exists
(
	CAS_id int not null,
	Name varchar(45) null,
	FusionPoint DOUBLE null,
	BoilingPoint double null,
	ExistType int null,
	IsOrganic int null,
	Oxidation int null,
	Reducibility int null,
	Inflammability int null,
	Explosion int null,
	constraint chemCAS_pk
		primary key (CAS_id)
);

create table spot if not exists
(
	SpoitId int not null,
	SpotName varchar(45) null,
	SpotX int null,
	SpotY int null,
	constraint spot_pk
		primary key (SpoitId)
);

create table batch if not exists
(
	batchId int not null,
	batchName varchar(45) null,
	Destination int null,
	Departure int null,
	constraint batch_pk
		primary key (batchId)
);

create table transport if not exists
(
	TransportId int not null,
	CAS_id int null,
	batchId int null,
	Num int null,
	State int null,
	constraint transport_pk
		primary key (TransportId)
);



