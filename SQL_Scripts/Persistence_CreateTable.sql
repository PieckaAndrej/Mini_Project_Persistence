USE CSC-CSD-S211_10407533

/*drop table if exists dbo.Country;*/

CREATE TABLE dbo.Country (
	country VARCHAR(20) NOT NULL,
	zipcode VARCHAR(5) NOT NULL,
	city VARCHAR(25) NOT NULL,
	PRIMARY KEY (country, zipcode),
	)  

GO

/*drop table if exists dbo.Person;
ALTER TABLE dbo.Country
DROP CONSTRAINT PersonLocationFK;*/

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

/*drop table if exists dbo.SaleOrder;
ALTER TABLE dbo.SaleOrder
DROP CONSTRAINT CustomerOrderFK;*/

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

/*drop table if exists dbo.Product;
ALTER TABLE dbo.Product
DROP CONSTRAINT SupplierProductFK;*/

CREATE TABLE dbo.Product (
	id int PRIMARY KEY IDENTITY(1,1),
	[name] VARCHAR(25) NOT NULL,
	purchasePrice int NOT NULL,
	salesPrice int NOT NULL,
	rentPrice int NOT NULL,
	countryOfOrigin VARCHAR(20) NOT NULL,
	minStock int NOT NULL,
	currentStock int NOT NULL,
	supplierPhoneno VARCHAR(10),
	CONSTRAINT SupplierProductFK
		FOREIGN KEY (supplierPhoneno) REFERENCES Person(phoneno)
		ON DELETE SET NULL,
	)  

GO

/*drop table if exists dbo.[Copy];
ALTER TABLE dbo.[Copy]
DROP CONSTRAINT ProductCopyFK;*/

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

/*drop table if exists dbo.RentOrderLine;
ALTER TABLE dbo.SaleOrderLine
DROP CONSTRAINT RentOrderLineFK, CopyOrderLineFK;*/

CREATE TABLE dbo.RentOrderLine (
	quantity int NOT NULL,
	copyId int, 
	saleId int,
	rentDate datetime NOT NULL,
	PRIMARY KEY (copyId, saleId),
	CONSTRAINT RentOrderLineFK
		FOREIGN KEY (saleId) REFERENCES SaleOrder(id)
		ON DELETE CASCADE,
	CONSTRAINT CopyOrderLineFK
		FOREIGN KEY (copyId) REFERENCES [Copy](copyId)
		ON DELETE CASCADE,
	)  

GO

/*drop table if exists dbo.SaleOrderLine;
ALTER TABLE dbo.SaleOrderLine
DROP CONSTRAINT SaleOrderLineFK, CopyOrderLineFK;*/

CREATE TABLE dbo.SaleOrderLine (
	quantity int NOT NULL,
	productId int, 
	saleId int,
	PRIMARY KEY(productId, saleId),
	CONSTRAINT SaleOrderLineFK
		FOREIGN KEY (saleId) REFERENCES SaleOrder(id)
		ON DELETE CASCADE,
	CONSTRAINT CopyOrderLineFK
		FOREIGN KEY (productId) REFERENCES Product(id)
		ON DELETE CASCADE,
	)  

GO


/*drop table if exists dbo.Clothing;
ALTER TABLE dbo.Clothing
DROP CONSTRAINT ClothingProductFK;*/

CREATE TABLE dbo.Clothing (
	size VARCHAR(5) NOT NULL,
	color VARCHAR(10) NOT NULL,
	productId int,
	CONSTRAINT ClothingProductFK
		FOREIGN KEY (productID) REFERENCES Product(id)
		ON DELETE SET NULL,
	)

GO

/*drop table if exists dbo.Equipment;
ALTER TABLE dbo.Equipment
DROP CONSTRAINT EquipmentProductFK;*/

CREATE TABLE dbo.Equipment (
	[type] VARCHAR(20) NOT NULL,
	[description] VARCHAR(100) NOT NULL,
	productId int,
	CONSTRAINT EquipmentProductFK
		FOREIGN KEY (productID) REFERENCES Product(id)
		ON DELETE SET NULL,
	)

GO

/*drop table if exists dbo.GunReplica;
ALTER TABLE dbo.GunReplica
DROP CONSTRAINT GunReplicaProductFK;*/

CREATE TABLE dbo.GunReplica (
	calibre decimal(5,2) NOT NULL,
	material VARCHAR(20) NOT NULL,
	productId int,
	CONSTRAINT GunReplicaProductFK
		FOREIGN KEY (productID) REFERENCES Product(id)
		ON DELETE SET NULL,
	)

GO