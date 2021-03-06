USE [master]
GO
/****** Object:  Database [DM_DataBase]    Script Date: 02/22/2016 23:45:28 ******/
CREATE DATABASE [DM_DataBase] ON  PRIMARY 
( NAME = N'DM_DataBase', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\DM_DataBase.mdf' , SIZE = 2048KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'DM_DataBase_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\DM_DataBase_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [DM_DataBase] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DM_DataBase].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DM_DataBase] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [DM_DataBase] SET ANSI_NULLS OFF
GO
ALTER DATABASE [DM_DataBase] SET ANSI_PADDING OFF
GO
ALTER DATABASE [DM_DataBase] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [DM_DataBase] SET ARITHABORT OFF
GO
ALTER DATABASE [DM_DataBase] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [DM_DataBase] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [DM_DataBase] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [DM_DataBase] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [DM_DataBase] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [DM_DataBase] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [DM_DataBase] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [DM_DataBase] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [DM_DataBase] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [DM_DataBase] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [DM_DataBase] SET  DISABLE_BROKER
GO
ALTER DATABASE [DM_DataBase] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [DM_DataBase] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [DM_DataBase] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [DM_DataBase] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [DM_DataBase] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [DM_DataBase] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [DM_DataBase] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [DM_DataBase] SET  READ_WRITE
GO
ALTER DATABASE [DM_DataBase] SET RECOVERY SIMPLE
GO
ALTER DATABASE [DM_DataBase] SET  MULTI_USER
GO
ALTER DATABASE [DM_DataBase] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [DM_DataBase] SET DB_CHAINING OFF
GO
USE [DM_DataBase]
GO
/****** Object:  Table [dbo].[Room]    Script Date: 02/22/2016 23:45:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Room](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[roomName] [varchar](50) NOT NULL,
	[description] [varchar](50) NULL,
 CONSTRAINT [PK_Room] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Role]    Script Date: 02/22/2016 23:45:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[roleName] [varchar](50) NOT NULL,
	[description] [varchar](50) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Role] ON
INSERT [dbo].[Role] ([id], [roleName], [description]) VALUES (1, N'Admin', N'Admin')
INSERT [dbo].[Role] ([id], [roleName], [description]) VALUES (2, N'Manager', N'Manager')
INSERT [dbo].[Role] ([id], [roleName], [description]) VALUES (3, N'User', N'User')
SET IDENTITY_INSERT [dbo].[Role] OFF
/****** Object:  Table [dbo].[CatergoryDocument]    Script Date: 02/22/2016 23:45:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CatergoryDocument](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[roomID] [int] NOT NULL,
	[catergoryName] [varchar](50) NOT NULL,
	[description] [varchar](50) NULL,
 CONSTRAINT [PK_CatergoryDocument] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Account]    Script Date: 02/22/2016 23:45:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[roleID] [int] NOT NULL,
	[mail] [varchar](100) NOT NULL,
	[passwords] [varchar](50) NOT NULL,
	[fullName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Account] ON
INSERT [dbo].[Account] ([id], [roleID], [mail], [passwords], [fullName]) VALUES (4, 1, N'admin', N'admin', N'Admin')
SET IDENTITY_INSERT [dbo].[Account] OFF
/****** Object:  Table [dbo].[RoomAccount]    Script Date: 02/22/2016 23:45:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RoomAccount](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[accountID] [int] NOT NULL,
	[roomID] [int] NOT NULL,
 CONSTRAINT [PK_Table_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Document]    Script Date: 02/22/2016 23:45:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Document](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[catergoryID] [int] NOT NULL,
	[description] [varchar](50) NULL,
	[signer] [varchar](50) NULL,
	[fromDate] [date] NULL,
	[toDate] [date] NULL,
	[writer] [varchar](50) NULL,
	[seriDocument] [varchar](50) NULL,
	[link] [varchar](50) NOT NULL,
	[text] [varchar](5000) NULL,
 CONSTRAINT [PK_Document] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[KeyWords]    Script Date: 02/22/2016 23:45:30 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[KeyWords](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[keyWord] [varchar](100) NOT NULL,
	[documentID] [int] NOT NULL,
 CONSTRAINT [PK_KeyWords] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_CatergoryDocument_Room]    Script Date: 02/22/2016 23:45:30 ******/
ALTER TABLE [dbo].[CatergoryDocument]  WITH CHECK ADD  CONSTRAINT [FK_CatergoryDocument_Room] FOREIGN KEY([roomID])
REFERENCES [dbo].[Room] ([id])
GO
ALTER TABLE [dbo].[CatergoryDocument] CHECK CONSTRAINT [FK_CatergoryDocument_Room]
GO
/****** Object:  ForeignKey [FK_Account_Role]    Script Date: 02/22/2016 23:45:30 ******/
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([roleID])
REFERENCES [dbo].[Role] ([id])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
/****** Object:  ForeignKey [FK_RoomAccount_Account]    Script Date: 02/22/2016 23:45:30 ******/
ALTER TABLE [dbo].[RoomAccount]  WITH CHECK ADD  CONSTRAINT [FK_RoomAccount_Account] FOREIGN KEY([accountID])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[RoomAccount] CHECK CONSTRAINT [FK_RoomAccount_Account]
GO
/****** Object:  ForeignKey [FK_RoomAccount_Room]    Script Date: 02/22/2016 23:45:30 ******/
ALTER TABLE [dbo].[RoomAccount]  WITH CHECK ADD  CONSTRAINT [FK_RoomAccount_Room] FOREIGN KEY([roomID])
REFERENCES [dbo].[Room] ([id])
GO
ALTER TABLE [dbo].[RoomAccount] CHECK CONSTRAINT [FK_RoomAccount_Room]
GO
/****** Object:  ForeignKey [FK_Document_CatergoryDocument]    Script Date: 02/22/2016 23:45:30 ******/
ALTER TABLE [dbo].[Document]  WITH CHECK ADD  CONSTRAINT [FK_Document_CatergoryDocument] FOREIGN KEY([catergoryID])
REFERENCES [dbo].[CatergoryDocument] ([id])
GO
ALTER TABLE [dbo].[Document] CHECK CONSTRAINT [FK_Document_CatergoryDocument]
GO
/****** Object:  ForeignKey [FK_KeyWords_Document]    Script Date: 02/22/2016 23:45:30 ******/
ALTER TABLE [dbo].[KeyWords]  WITH CHECK ADD  CONSTRAINT [FK_KeyWords_Document] FOREIGN KEY([documentID])
REFERENCES [dbo].[Document] ([id])
GO
ALTER TABLE [dbo].[KeyWords] CHECK CONSTRAINT [FK_KeyWords_Document]
GO
