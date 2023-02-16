/* Trigger Testing
by Adrian Noa
*/

-------STEP 1: execute first to create database------------
use master

DECLARE @DBNAME VARCHAR(15) set @DBNAME =		'SWTool'

		DECLARE @Sql varchar(max) = 
		'DROP DATABASE IF EXISTS ' + @DBNAME
		+ 
		' CREATE DATABASE ' + @DBNAME + ' ALTER DATABASE ' + @DBNAME + ' SET MULTI_USER WITH ROLLBACK  IMMEDIATE '


EXECUTE(@Sql)
--------------------------------------------------------------


---------STEP 2: forward engineer model from erwin on to TriggerTest database--------------------

/* the Forward Engineering "errors" are Delete and Drop sql statements. Ignore them and continue */

---------------------------- then execute next line -----------------------------------------------
use SWTool;
--------------------------------------------------------------------------------------------------



---------STEP 3: Look at data in target and Audit tables --------------------

select * from users.account








