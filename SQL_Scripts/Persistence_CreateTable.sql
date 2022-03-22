/*USE DatabaseName*/

CREATE TABLE dbo.Customer (
	[name] VARCHAR(25) NOT NULL,
	[address] VARCHAR(50) NOT NULL,
	zipcode VARCHAR(5) NOT NULL,
	city VARCHAR(25) NOT NULL,
	phoneno VARCHAR(10) PRIMARY KEY NOT NULL,
	)  

GO

CREATE TABLE dbo.Invoice (
	invoiceNo int PRIMARY KEY IDENTITY(1,1),
	paymentDate datetime NOT NULL,
	amount int NOT NULL,
	)  

GO

CREATE TABLE dbo.SaleOrder (
   id int PRIMARY KEY IDENTITY(1,1),  
   [date] datetime NOT NULL,  
   deliveryStatus bit,
   deliveryDate datetime NOT NULL,
   city VARCHAR(25) NOT NULL,
   customerPhoneno VARCHAR(10) NOT NULL,
   invoiceNumber int,
   CONSTRAINT CustomerOrderFK
		FOREIGN KEY (customerPhoneno) REFERENCES Customer(phoneno)
		ON DELETE SET NULL,
   CONSTRAINT InvoiceNumberFK
		FOREIGN KEY (invoiceNumber) REFERENCES Invoice(invoiceNo)
		ON DELETE SET NULL,
		)  

GO

CREATE TABLE dbo.SaleOrderLine (
	amount int NOT NULL,
	saleId int,
	productId int PRIMARY KEY IDENTITY(1,1), 
	CONSTRAINT SaleOrderFK
		FOREIGN KEY (saleId) REFERENCES SaleOrder(id)
		ON DELETE SET NULL,
	)  

GO

CREATE TABLE dbo.Supplier (
	[name] VARCHAR(25) NOT NULL,
	[address] VARCHAR(50) NOT NULL,
	country VARCHAR(20) NOT NULL,
	phoneno VARCHAR(10) PRIMARY KEY NOT NULL,
	email VARCHAR(50) NOT NULL,
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
	supplierPhoneno VARCHAR(10) NOT NULL,
	CONSTRAINT SupplierProductFK
		FOREIGN KEY (supplierPhoneno) REFERENCES Supplier(phoneno)
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
	calibre decimal(5,2),
	material VARCHAR(20) NOT NULL,
	productId int,
	CONSTRAINT GunReplicaProductFK
		FOREIGN KEY (productID) REFERENCES Product(id)
		ON DELETE SET NULL,
	)

GO