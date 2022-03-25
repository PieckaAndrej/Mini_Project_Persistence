use [CSC-CSD-S211_10407533]

insert into Country values('Denmark','9000','Aalborg');

insert into Country values('Germany','20095','Hamburg');

go

insert into Person values('Joe','Norgesgade 18 2tv','Denmark','9000','1234567890', 'joe@gmail.com');

insert into Person values('Hans', 'Jungfernstieg 23', 'Germany', '20095', '89216457', 'hans@gmail.com');

go

insert into SaleOrder values('2022-03-25 10:00:00', 'delivered', '2022-03-26 10:00:00', '2022-03-27 16:00:00', 200, '1234567890');

insert into SaleOrder values('2022-04-12 19:00:00', 'delivered', '2022-04-13 19:00:00', '2022-04-15 16:00:00', 1000, '89216457');

go

insert into Product values('hat',10,15.00015,'USA',2,8,'1234567890');

insert into Product values('AK-47',10,1500,'Vietnam',2,20,'1234567890');

insert into Product values('Cowboy T-Shirt',10,5,'Denmark',4,100,'1234567890');

go

insert into [Copy] values('2022-03-25 10:00:00', 100, 1);

insert into [Copy] values('2022-05-02 14:00:00', 500, 2);

go

insert into RentOrderLine values(5, 1, 1);

insert into RentOrderLine values(3, 1, 2);

go

insert into SaleOrderLine values(2, 3, 1);

insert into SaleOrderLine values(4, 1, 2);

go

insert into Clothing values('XL', 'blue', 3);

go

insert into Equipment values ('pistol belts', 'comfy for use and comes with a great design', 2);

go

insert into GunReplica values(7.62, 'metal', 2);

go