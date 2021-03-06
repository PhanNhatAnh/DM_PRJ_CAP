USE [master]
GO
/****** Object:  Database [DM]    Script Date: 3/2/2016 6:16:37 PM ******/
CREATE DATABASE [DM] ON  PRIMARY 
( NAME = N'DM', FILENAME = N'E:\MSSQL\DATA\DM.mdf' , SIZE = 2304KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'DM_log', FILENAME = N'E:\MSSQL\DATA\DM_log.LDF' , SIZE = 576KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [DM] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DM].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DM] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DM] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DM] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DM] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DM] SET ARITHABORT OFF 
GO
ALTER DATABASE [DM] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DM] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DM] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DM] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DM] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DM] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DM] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DM] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DM] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DM] SET  ENABLE_BROKER 
GO
ALTER DATABASE [DM] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DM] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DM] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DM] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DM] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DM] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DM] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DM] SET RECOVERY FULL 
GO
ALTER DATABASE [DM] SET  MULTI_USER 
GO
ALTER DATABASE [DM] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DM] SET DB_CHAINING OFF 
GO
USE [DM]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 3/2/2016 6:16:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryId] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nvarchar](2000) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[CategoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Department]    Script Date: 3/2/2016 6:16:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Department](
	[DepartmentId] [int] IDENTITY(1,1) NOT NULL,
	[DepartmentName] [nvarchar](4000) NOT NULL,
	[HeadDepartment] [int] NULL,
 CONSTRAINT [PK_Department] PRIMARY KEY CLUSTERED 
(
	[DepartmentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Document]    Script Date: 3/2/2016 6:16:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Document](
	[DocumentId] [int] IDENTITY(1,1) NOT NULL,
	[CategoryId] [int] NULL,
	[Type] [int] NULL,
	[DocumentName] [nvarchar](2000) NULL,
	[DateIssued] [date] NULL,
	[SignedDate] [date] NULL,
	[WhoIssuedRegulation] [nvarchar](500) NULL,
	[Signer] [nvarchar](500) NULL,
	[FromDate] [date] NULL,
	[ToDate] [date] NULL,
	[DocumentNo] [nvarchar](500) NULL,
	[Path] [nvarchar](4000) NULL,
	[UserUpload] [int] NULL,
	[TimeUpload] [datetime] NULL,
 CONSTRAINT [PK_Document] PRIMARY KEY CLUSTERED 
(
	[DocumentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DocumentDetail]    Script Date: 3/2/2016 6:16:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DocumentDetail](
	[DocumentDetailId] [int] IDENTITY(1,1) NOT NULL,
	[DocumentId] [int] NULL,
	[ContentText] [nvarchar](4000) NULL,
 CONSTRAINT [PK_ContentOfDocument] PRIMARY KEY CLUSTERED 
(
	[DocumentDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[FormatFile]    Script Date: 3/2/2016 6:16:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FormatFile](
	[FormartFileId] [int] NOT NULL,
	[FormartFileName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_FormatFile] PRIMARY KEY CLUSTERED 
(
	[FormartFileId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 3/2/2016 6:16:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[RoleId] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [nvarchar](500) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[RoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Users]    Script Date: 3/2/2016 6:16:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserId] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nvarchar](50) NOT NULL,
	[FirstAndLastName] [nvarchar](500) NULL,
	[Password] [nvarchar](20) NOT NULL,
	[DepartmentId] [int] NULL,
	[RoleId] [int] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (1, N'Văn bản đến')
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (2, N'Văn bản đi')
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (3, N'Quyết định nội bộ')
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (4, N'Tài liệu ISO')
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (5, N'Văn bản pháp quy')
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Department] ON 

INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (1, N'Đào tạo', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (2, N'Công tác sinh viên', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (3, N'Tuyển sinh', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (4, N'Hành chính', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (5, N'Nhân sự', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (6, N'Kế toán', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (7, N'QA', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (8, N'IT', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (9, N'PR', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (10, N'Dịch vụ đời sống', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (11, N'Xây dựng', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (12, N'Dự án', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (13, N'Bộ/Ban/Ngành', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (14, N'Phát triển chương trình', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (15, N'Xuất bản', 1)
INSERT [dbo].[Department] ([DepartmentId], [DepartmentName], [HeadDepartment]) VALUES (16, N'Khác', 1)
SET IDENTITY_INSERT [dbo].[Department] OFF
SET IDENTITY_INSERT [dbo].[Document] ON 

INSERT [dbo].[Document] ([DocumentId], [CategoryId], [Type], [DocumentName], [DateIssued], [SignedDate], [WhoIssuedRegulation], [Signer], [FromDate], [ToDate], [DocumentNo], [Path], [UserUpload], [TimeUpload]) VALUES (1, 3, 1, N'Thành lập Hội đồng thanh lý tài sản tại Khối Phát triển sinh viên quốc tế', CAST(N'2016-02-18' AS Date), CAST(N'2016-02-20' AS Date), N'phonglv@fpt.edu.vn', N'minh@fpt.edu.vn', CAST(N'2016-02-20' AS Date), CAST(N'2016-04-20' AS Date), N'112/QĐ-ĐHFPT', NULL, 1, CAST(N'2016-02-18 00:00:00.000' AS DateTime))
INSERT [dbo].[Document] ([DocumentId], [CategoryId], [Type], [DocumentName], [DateIssued], [SignedDate], [WhoIssuedRegulation], [Signer], [FromDate], [ToDate], [DocumentNo], [Path], [UserUpload], [TimeUpload]) VALUES (2, 3, 1, N'Thưởng công trình khoa học của ông Nguyễn Khắc Việt được công bố trên tạp chí khoa học
', CAST(N'2016-02-15' AS Date), CAST(N'2016-02-17' AS Date), N'minh@fpt.edu.vn', N'minh@fpt.edu.vn', CAST(N'2016-02-01' AS Date), CAST(N'2016-05-25' AS Date), N'109/QĐ-ĐHFPT	', NULL, 2, CAST(N'2016-02-18 00:00:00.000' AS DateTime))
INSERT [dbo].[Document] ([DocumentId], [CategoryId], [Type], [DocumentName], [DateIssued], [SignedDate], [WhoIssuedRegulation], [Signer], [FromDate], [ToDate], [DocumentNo], [Path], [UserUpload], [TimeUpload]) VALUES (3, 3, 1, N'	Thưởng công trình khoa học của ông Nguyễn Khắc Việt được công bố trên tạp chí khoa học', CAST(N'2016-02-19' AS Date), CAST(N'2016-02-05' AS Date), N'	ngadtt@fpt.edu.vn', N'	ngadtt@fpt.edu.vn', CAST(N'2016-01-01' AS Date), CAST(N'2016-06-01' AS Date), N'	112/QĐ-ĐHFPT', NULL, 3, CAST(N'2016-03-18 00:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Document] OFF
INSERT [dbo].[FormatFile] ([FormartFileId], [FormartFileName]) VALUES (1, N'xlsx')
INSERT [dbo].[FormatFile] ([FormartFileId], [FormartFileName]) VALUES (2, N'pdf')
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([RoleId], [RoleName]) VALUES (1, N'Admin')
INSERT [dbo].[Role] ([RoleId], [RoleName]) VALUES (2, N'HeadDepartment')
INSERT [dbo].[Role] ([RoleId], [RoleName]) VALUES (3, N'User')
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([UserId], [UserName], [FirstAndLastName], [Password], [DepartmentId], [RoleId]) VALUES (1, N'anhbn', N'Bùi Ngọc Anh', N'123456', 2, 1)
INSERT [dbo].[Users] ([UserId], [UserName], [FirstAndLastName], [Password], [DepartmentId], [RoleId]) VALUES (2, N'anhdn', N'Đào Ngọc Anh', N'123456', NULL, NULL)
INSERT [dbo].[Users] ([UserId], [UserName], [FirstAndLastName], [Password], [DepartmentId], [RoleId]) VALUES (3, N'anhdn2008', N'Dao Ngoc Anh', N'123456', NULL, NULL)
INSERT [dbo].[Users] ([UserId], [UserName], [FirstAndLastName], [Password], [DepartmentId], [RoleId]) VALUES (4, N'anhdq', N'Đoàn Quỳnh Anh', N'123456', NULL, NULL)
INSERT [dbo].[Users] ([UserId], [UserName], [FirstAndLastName], [Password], [DepartmentId], [RoleId]) VALUES (5, N'anhdq2', N'Đào Quỳnh Anh', N'123456', NULL, NULL)
INSERT [dbo].[Users] ([UserId], [UserName], [FirstAndLastName], [Password], [DepartmentId], [RoleId]) VALUES (6, N'anhdv', N'Đào Vân Anh', N'123456', NULL, NULL)
INSERT [dbo].[Users] ([UserId], [UserName], [FirstAndLastName], [Password], [DepartmentId], [RoleId]) VALUES (7, N'anhdv2', N'Đoàn Thị Việt Anh', N'123456', NULL, NULL)
INSERT [dbo].[Users] ([UserId], [UserName], [FirstAndLastName], [Password], [DepartmentId], [RoleId]) VALUES (8, N'anhhgq', N'Hoàng Giang Quỳnh Anh', N'123456', NULL, NULL)
INSERT [dbo].[Users] ([UserId], [UserName], [FirstAndLastName], [Password], [DepartmentId], [RoleId]) VALUES (9, N'anhhn', N'Hoàng Ngọc Anh', N'123456', NULL, NULL)
SET IDENTITY_INSERT [dbo].[Users] OFF
ALTER TABLE [dbo].[Department]  WITH CHECK ADD  CONSTRAINT [fk_Department_Head] FOREIGN KEY([HeadDepartment])
REFERENCES [dbo].[Users] ([UserId])
GO
ALTER TABLE [dbo].[Department] CHECK CONSTRAINT [fk_Department_Head]
GO
ALTER TABLE [dbo].[Document]  WITH CHECK ADD  CONSTRAINT [fk_Document_Category] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Category] ([CategoryId])
GO
ALTER TABLE [dbo].[Document] CHECK CONSTRAINT [fk_Document_Category]
GO
ALTER TABLE [dbo].[Document]  WITH CHECK ADD  CONSTRAINT [fk_document_type] FOREIGN KEY([Type])
REFERENCES [dbo].[FormatFile] ([FormartFileId])
GO
ALTER TABLE [dbo].[Document] CHECK CONSTRAINT [fk_document_type]
GO
ALTER TABLE [dbo].[Document]  WITH CHECK ADD  CONSTRAINT [fk_Document_UserUpload] FOREIGN KEY([UserUpload])
REFERENCES [dbo].[Users] ([UserId])
GO
ALTER TABLE [dbo].[Document] CHECK CONSTRAINT [fk_Document_UserUpload]
GO
ALTER TABLE [dbo].[DocumentDetail]  WITH CHECK ADD  CONSTRAINT [fk_Content_Document] FOREIGN KEY([DocumentId])
REFERENCES [dbo].[Document] ([DocumentId])
GO
ALTER TABLE [dbo].[DocumentDetail] CHECK CONSTRAINT [fk_Content_Document]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [fk_User_Department] FOREIGN KEY([DepartmentId])
REFERENCES [dbo].[Department] ([DepartmentId])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [fk_User_Department]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [fk_user_role] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Role] ([RoleId])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [fk_user_role]
GO
USE [master]
GO
ALTER DATABASE [DM] SET  READ_WRITE 
GO
