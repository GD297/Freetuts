/*Declare @JSON varchar(max)
SELECT @JSON=Blog
FROM OPENROWSET (BULK 'D:\FPT University\data.json', SINGLE_CLOB) import
SELECT *
FROM OPENJSON (@JSON)
WITH
(
	[title] nvarchar(max),
	[content] nvarchar(max)
)*/

DECLARE @jsonData NVARCHAR(MAX);
SET @jsonData = N'
[
{"category":"Học Pascal","link":"/hoc-pascal"},
{"category":"Học C / C++","link":"/hoc-c"},
{"category":"Giải thuật","link":"/giai-thuat"},
{"category":"Học SQL Server","link":"/hoc-sql-server"},
{"category":"Học Java","link":"/hoc-java"},
{"category":"Học PHP","link":"/hoc-php"},
{"category":"Khóa học lập trình","link":"/khoa-hoc/ngon-ngu-lap-trinh"},
{"category":"Khóa học tin học VP","link":"/khoa-hoc/tin-hoc-may-tinh"},
{"category":"Khóa học thiết kế","link":"/khoa-hoc/design"},
{"category":"Khóa học khác","link":"/khoa-hoc/khoa-hoc-khac"},
{"category":"Nên dùng","link":"#"},
{"category":"Hosting tốt nhất","link":"/hosting-tot-nhat-2547.html"},
{"category":"VPS tốt nhất","link":"/vps-tot-nhat-2568.html"},
{"category":"Mua domain ở đâu","link":"/nhung-noi-ban-domain-uy-tin-2881.html"},
{"category":"Kiên thức Domain","link":"/kien-thuc-domain"},
{"category":"Thủ thuật Hosting","link":"/thu-thuat-hosting"},
{"category":"Học WordPress","link":"/hoc-wordpress"},
{"category":"Quản trị Linux","link":"/hoc-linux"},
{"category":"Quản trị Windows","link":"/quan-tri-windows"},
{"category":"Tinohost","link":"/giam-gia/ma-giam-gia-tinohost"},
{"category":"Azdigi","link":"/giam-gia/ma-giam-gia-azdigi"},
{"category":"Vultr","link":"/giam-gia/ma-giam-gia-vultr"},
{"category":"Word","link":"/hoc-word"},
{"category":"Excel","link":"/hoc-excel"},
{"category":"PowerPoint","link":"/hoc-powerpoint"},
{"category":"Access","link":"/hoc-access"},
{"category":"Photoshop","link":"/hoc-photoshop"},
{"category":"Cứu hộ máy tính","link":"/thu-thuat/cuu-ho-may-tinh"},
{"category":"Cách ghost Win","link":"/thu-thuat/cuu-ho-may-tinh/ghost-win"},
{"category":"Cách cài Win","link":"/thu-thuat/cuu-ho-may-tinh/cai-win"},
{"category":"Tạo USB Boot","link":"/thu-thuat/cuu-ho-may-tinh/tao-usb-boot"},
{"category":"Phục hồi dữ liệu","link":"/thu-thuat/cuu-ho-may-tinh/phuc-hoi-du-lieu"},
{"category":"Fix lỗi win","link":"/thu-thuat/cuu-ho-may-tinh/loi-win"},
{"category":"Điện Thoại","link":"/thu-thuat/dien-thoai"},
{"category":"Máy tính","link":"/thu-thuat/may-tinh"},
{"category":"Game","link":"/thu-thuat/game"},
{"category":"Kỹ Thuật Số","link":"/thu-thuat/ky-thuat-so"},
{"category":"Ứng dụng","link":"/thu-thuat/ung-dung"},
{"category":"Review","link":"/review"},
{"category":"Phần mềm","link":"/download-windows"},
{"category":"Tài liệu","link":"/tai-lieu"},
{"category":"Font chữ","link":"/font-chu"},
{"category":"Driver","link":"/driver"},
{"category":"Ảnh đẹp","link":"/anh-dep"},
{"category":"Toán","link":"/toan"},
{"category":"Tiếng Anh","link":"/tieng-anh"},
{"category":"Văn học","link":"/van-hoc"},
{"category":"Marketing","link":"/marketing"},
{"category":"Thuật ngữ","link":"/glossary"},
{"category":"Khám phá","link":"/kham-pha"}
]';

insert into category(title, linkcate)
SELECT category, link
FROM OPENJSON(@jsonData)
WITH(
	category nvarchar(255),
	link varchar(255)
)


/*DECLARE @id Table (id uniqueidentifier,
					title nvarchar(255));

insert into @id(id, title)
SELECT cateid, title From category

insert into sub_category(cateid, title)
select id, J.main_title from @id inner join OPENJSON(@jsonData)
    WITH ( 
		 category nvarchar(255),
		 main_title nvarchar(255)
		 ) J ON J.category = title*/


/*SELECT cateid
FROM category
WHERE (SELECT category FROM OPENJSON(@json) WITH (category nvarchar(255)) where category.title = category) = category.title*/
	
/*DECLARE @json NVARCHAR(MAX);
SET @json = N'[
  {"info": {"name": "John", "surname": "Smith"}, "age": 25},
  { "info": {"name": "Jane", "surname": "Smith"}, "dob": "2005-11-04T12:00:00"}
]';
Insert into Blog(Title,Content)
SELECT *
FROM OPENJSON(@json)
  WITH (
   
    Title NVARCHAR(50) '$.info.name',
    Content NVARCHAR(50) '$.info.surname'
  );*/