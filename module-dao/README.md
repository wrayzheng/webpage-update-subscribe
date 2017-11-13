# DAO的使用

+ ## 首先初始化配置文件
	### 调用静态函数:
	```
	MySQLDatabaseConnection.initialDatabaseDeploy()
	```
	### 实现对配置文件的初始化。
	### 在函数`initialDatabaseDeploy()`中，首先判断`user.home`目录下有没有`/.wus/db.properties`文件：如果有，从该文件中读出`userName`，`password`，`host`，`database`变量值，用以之后与数据库建立联系；否则，在该路径下默认配置文件，默认配置项对应为：`"root"`，`"mysql"`，`"localhost"`，`3306`，`"mysql"`。
	
+ ## 通过DAO工厂函数生成一个DAO对象
	### 实例代码：
	```
	IUserDAO userDAO = UserDAOFactory.getUserDAOInstance();
	```
	


+ ## 与数据建立联系
	### 在构造DAO对象中首先调用静态工厂函数:
	```
	DatabaseConnectionFactory.getMySQLDatabaseConnection()
	```
	### 实现连接数据库。
	### 源代码如下：
	```
	public ContentDAOProxy() throws Exception{
		dbc=DatabaseConnectionFactory.getMySQLDatabaseConnection();
		...
	}
	```
	### 该函数通过返回一个新的`MySQLDatabaseConnection`对象，在`MySQLDatabaseConnection`对象的构造器中实现对数据库的链接。

+ ## 新建DAO对象
	### 该数据库有3张表：`user`，`url`和`content`。
	### 通过上面生成的`MySQLDatabaseConnection`返回一个`Connection` 对象，以初始化需操作的DAO对象。
	### 源代码如下：
	```
	public ContentDAOProxy() throws Exception{
		dbc=DatabaseConnectionFactory.getMySQLDatabaseConnection();
		dao=new ContentDAO(dbc.getConnection());
	}
	```
	
+ ## 操作数据库
	### 通过函数接口调用相应函数。
	### 示例代码：
	```
	userDAO.doInsert(user);
	userDAO.doUpdatePushTime("xx",xxxx);