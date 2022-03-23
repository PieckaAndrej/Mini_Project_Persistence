USE [CSC-CSD-S211_10407533]


ALTER TABLE dbo.Person
DROP CONSTRAINT if exists PersonLocationFK;
ALTER TABLE dbo.SaleOrder
DROP CONSTRAINT if exists CustomerOrderFK;
ALTER TABLE dbo.Product
DROP CONSTRAINT if exists SupplierProductFK
ALTER TABLE dbo.[Copy]
DROP CONSTRAINT if exists ProductCopyFK;
ALTER TABLE dbo.RentOrderLine
DROP CONSTRAINT if exists RentOrderLineFK, CopyRentOrderLineFK;
ALTER TABLE dbo.SaleOrderLine
DROP CONSTRAINT if exists SaleOrderLineFK, CopySaleOrderLineFK;
ALTER TABLE dbo.Clothing
DROP CONSTRAINT if exists ClothingProductFK;
ALTER TABLE dbo.Equipment
DROP CONSTRAINT if exists EquipmentProductFK;
ALTER TABLE dbo.GunReplica
DROP CONSTRAINT if exists GunReplicaProductFK;

GO

drop table if exists dbo.Country;
drop table if exists dbo.GunReplica;
drop table if exists dbo.Person;
drop table if exists dbo.SaleOrder;
drop table if exists dbo.Product;;
drop table if exists dbo.[Copy];
drop table if exists dbo.RentOrderLine;
drop table if exists dbo.SaleOrderLine;
drop table if exists dbo.Clothing;
drop table if exists dbo.Equipment;



CREATE TABLE dbo.Country (
	country VARCHAR(20) NOT NULL,
	zipcode VARCHAR(5) NOT NULL,
	city VARCHAR(25) NOT NULL,
	PRIMARY KEY (country, zipcode),
	)  

GO


CREATE TABLE dbo.Person (
	[name] VARCHAR(25) NOT NULL,
	[address] VARCHAR(50) NOT NULL,
	country VARCHAR(20),
	zipcode VARCHAR(5),
	phoneno VARCHAR(10) PRIMARY KEY NOT NULL,
	email VARCHAR(50) NOT NULL,
	CONSTRAINT PersonLocationFK
		FOREIGN KEY (country, zipcode) REFERENCES Country(country, zipcode)
		ON DELETE SET NULL,
	)  

GO


CREATE TABLE dbo.SaleOrder (
   id int PRIMARY KEY IDENTITY(1,1),  
   [date] datetime NOT NULL,  
   deliveryStatus bit,
   deliveryDate datetime NOT NULL,
   paymentDate datetime NOT NULL,
   amount int NOT NULL,
   customerPhoneno VARCHAR(10),
   CONSTRAINT CustomerOrderFK
		FOREIGN KEY (customerPhoneno) REFERENCES Person(phoneno)
		ON DELETE SET NULL,
		)  

GO


CREATE TABLE dbo.Product (
	id int PRIMARY KEY IDENTITY(1,1),
	[name] VARCHAR(25) NOT NULL,
	purchasePrice MONEY NOT NULL,
	salesPrice MONEY NOT NULL,
	rentPrice MONEY NOT NULL,
	countryOfOrigin VARCHAR(20) NOT NULL,
	minStock int NOT NULL,
	currentStock int NOT NULL,
	supplierPhoneno VARCHAR(10),
	CONSTRAINT SupplierProductFK
		FOREIGN KEY (supplierPhoneno) REFERENCES Person(phoneno)
		ON DELETE SET NULL,
	)  

GO

CREATE TABLE dbo.[Copy] (
	copyId int PRIMARY KEY IDENTITY(1,1), 
	isRentable bit,
	rentDate datetime NOT NULL,
	productId int,
	CONSTRAINT ProductCopyFK
		FOREIGN KEY (productId) REFERENCES Product(id)
		ON DELETE CASCADE,
	)  

GO


CREATE TABLE dbo.RentOrderLine (
	quantity int NOT NULL,
	copyId int, 
	saleId int,
	rentDate datetime NOT NULL,
	PRIMARY KEY (copyId, saleId),
	CONSTRAINT RentOrderLineFK
		FOREIGN KEY (saleId) REFERENCES SaleOrder(id)
		ON DELETE CASCADE,
	CONSTRAINT CopyRentOrderLineFK
		FOREIGN KEY (copyId) REFERENCES [Copy](copyId)
		ON DELETE CASCADE,
	)  

GO

CREATE TABLE dbo.SaleOrderLine (
	quantity int NOT NULL,
	productId int, 
	saleId int,
	PRIMARY KEY(productId, saleId),
	CONSTRAINT SaleOrderLineFK
		FOREIGN KEY (saleId) REFERENCES SaleOrder(id)
		ON DELETE CASCADE,
	CONSTRAINT CopySaleOrderLineFK
		FOREIGN KEY (productId) REFERENCES Product(id)
		ON DELETE CASCADE,
	)  

GO

CREATE TABLE dbo.Clothing (
	size VARCHAR(5) NOT NULL,
	color VARCHAR(10) NOT NULL,
	productId int,
	CONSTRAINT ClothingProductFK
		FOREIGN KEY (productID) REFERENCES Product(id)
		ON DELETE SET NULL,
	)

GO

CREATE TABLE dbo.Equipment (
	[type] VARCHAR(20) NOT NULL,
	[description] VARCHAR(100) NOT NULL,
	productId int,
	CONSTRAINT EquipmentProductFK
		FOREIGN KEY (productID) REFERENCES Product(id)
		ON DELETE SET NULL,
	)

GO

CREATE TABLE dbo.GunReplica (
	calibre decimal(5,2) NOT NULL,
	material VARCHAR(20) NOT NULL,
	productId int,
	CONSTRAINT GunReplicaProductFK
		FOREIGN KEY (productID) REFERENCES Product(id)
		ON DELETE SET NULL,
	)

GO