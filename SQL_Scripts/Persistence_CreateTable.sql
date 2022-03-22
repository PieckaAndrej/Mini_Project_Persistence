USE CSC-CSD-S211_10407533

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
	country VARCHAR(20) NOT NULL,
	zipcode VARCHAR(5) NOT NULL,
	phoneno VARCHAR(10) PRIMARY KEY NOT NULL,
	email VARCHAR(50) NOT NULL,
	CONSTRAINT CustomerLocationFK
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
   customerPhoneno VARCHAR(10) NOT NULL,
   CONSTRAINT CustomerOrderFK
		FOREIGN KEY (customerPhoneno) REFERENCES Person(phoneno)
		ON DELETE SET NULL,
		)  

GO

CREATE TABLE dbo.Product (
	id int PRIMARY KEY IDENTITY(1,1),
	[name] VARCHAR(25) NOT NULL,
	purchasePrice int NOT NULL,
	salesPrice int NOT NULL,
	rentPrice int NOT NULL,
	countryOfOrigin VARCHAR(20) NOT NULL,
	minStock int NOT NULL,
	currentStock int NOT NULL,
	supplierPhoneno VARCHAR(10) NOT NULL,
	CONSTRAINT SupplierProductFK
		FOREIGN KEY (supplierPhoneno) REFERENCES Person(phoneno)
		ON DELETE SET NULL,
	)  

GO

CREATE TABLE dbo.[Copy] (
	copyId int PRIMARY KEY IDENTITY(1,1), 
	isRentable bit,
	rentDate datetime NOT NULL,
	productId int IDENTITY(1,1),
	CONSTRAINT ProductCopyFK
		FOREIGN KEY (productId) REFERENCES Product(id)
		ON DELETE CASCADE,
	)  

GO

CREATE TABLE dbo.SaleOrderLine (
	amount int NOT NULL,
	copyId int IDENTITY(1,1), 
	saleId int IDENTITY(1,1),
	PRIMARY KEY (copyId, saleId),
	CONSTRAINT SaleOrderLineFK
		FOREIGN KEY (saleId) REFERENCES SaleOrder(id)
		ON DELETE CASCADE,
	CONSTRAINT CopyOrderLineFK
		FOREIGN KEY (copyId) REFERENCES [Copy](copyId)
		ON DELETE SET NULL,
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