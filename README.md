# mongodb多租户与mysql多租户--数据源切换

## mongodb切换数据源

###### 主要实现在mongodbTenant文件夹下--具体测试类为controller文件夹下的TestController类

* 1 添加加依赖(要注意springboot的版本，版本太低下面DynamicMongoTemplate可能不行)
    ```xml
     <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
     </dependency>
    ```
* 2 MongoContext文件添加所有数据源,并且提供数据源切换方法：MongoContext.setMongoDbFactory(数据库名称)
* 3 MongoProperty主要作用是设置默认的初始数据。其实是读取application.yml配置文件中mongo下的数据， 也可以不要该文件，然后在MongoContext文件中直接获取application.yml中的默认值
* 4 DynamicMongoTemplate文件主要实现切换数据源，不需要任何修改，这个是框架自实现
* 5 可以写拦截器在请求时就进行数据源切换，否则就要在每次数据源切换时都要调用方法：MongoContext.setMongoDbFactory(数据库名称)切换数据源

###### 优化添加注解切换租户(数据源)

* 1 annotation文件夹下的SwitchMongoDB注解就是数据源切换注解-使用方法：@SwitchMongoDB(value = "数据库名称")
* 2 DynamicDataSourceAspect文件为数据源切换注解的实现切面，若需要修改注解实现可以在这里进行修改 使用切面需要添加注解
    ```xml
     <!--添加aop切面，自定义注解使用Aspect-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
    ```
* 3 使用就是在需要切换数据源的方法上加上该注解

###### 注意

* 1 现在的多租户是分库的形式实现的
* 2 上面提到的切换数据源实际上是切换数据库，并且所使用到的数据库名称其实就是， MongoContext文件添加所有数据源时使用的map的key，该key就是数据库名称，并在在该文件中添加所有数据源时会自动创建key对应的数据库

## mysql数据库切换数据源

###### 主要实现在mysqlTenant文件夹下
* 1 DynamicDataSourceConfiguration文件添加所有数据源，并且也是会自动创建所有数据库
* 2 DynamicDataSource使用数据源，框架自动切换固定的类，没有修改
* 3 DataSourceContextHolder提供切换数据源接口：DataSourceContextHolder.setDataSource(数据库名称)--与mongodb说明一样
* 4 优化与mongodb一样，都是添加了切换注解，也在mysqlTenant文件夹下DataSource注解，切面为DataSourceAspect--@DataSource(value="数据库名称")
* 5 测试案例在controller文件夹的TestMysqlController类