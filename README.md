# Aop_Log# 写在最前面：

````java
package aop_cjlib	//基于Aspectj的注解方式实现AOP
package springaop	//基于sping实现（注解AspcetJ后发现并不报错误）
package xml_aop_jdk	//基于AspectJ的xml开发AOP
package Bean_Proxy	//Spring类代理
````

# AOP与OOP

​	Aspect Oriented Programming：面向切面编程

​	**OOP（面向对象编程）针对业务处理过程的实体及其属性和行为进行抽象封装，以获得更加清晰高效的逻辑单元划分**。 **而AOP则是针对业务处理过程中的切面进行提取，它所面对的是处理过程中的某个步骤或阶段，以获得逻辑过程中各部分之间低耦合性的隔离效果**。这两种设计思想在**目标上有着本质的差异**。AOP的出现是对OOP的一个很好的补充。面向对象编程中主要支持的思想就是面向接口编程，层与层之间不依赖具体，只依赖抽象。面向对象是纵向结构的，它能够使系统的逻辑单元更加清晰。而面向切面是横切结构的，它针对业务逻辑层的切面进行提取。比如面向切面完成一个功能，这一功能却在每个模块中都有涉及，他就像刀切豆腐一样切进系统一样，能够完成对系统完成同一控制。

# AOP相关术语

​	以下为个人对AOP的一些片面理解，如有不对，欢迎指正。

> Joinpoint

​	连接点，指可以被拦截到的类中的方法。对一个方法增强会先传入要增强的类，类中的所有方法都是可以被拦截（的连接点）。但我们真正需要的可能只是类中的某几个方法。

> Pointcut

​	切点，**“真正”被拦截到的方法**。参考Joinpoint—我们针对类中的某一个方法增强时，并没有“真正”被拦截的方法称为连接点，**“真正”被拦截到的方法**称为切点。

> Adcvice

​	通知，拦截后要做的事情。即要对拦截的方法做什么操作，如：在方法之前增加权限校验，在方法之后打印日志等。

> Targer

​	目标，要被增强的对象。

> Waving

​	织入，将Advice应用到Targer的过程。

> Poxy

​	代理，一个类被AOP织入增强后，就产生了一个代理类。

> Aspect

​	切面，**Pointcut和Adcvice的组合**。切面不包含连接点，举例刀切西瓜—刀切的部分即是切面。这是一个形象的说法存在许多漏洞，**并非西瓜面上的所有方法都是切点，还可能是连接点**，更何况**这个西瓜"切面"并没有Advice**。

​	关于上述的概念源码中有详细的注解，可供阅读。

# AOP小结

​	本次对AOP有一个片面的认识，只是机械实现了AOP增强类中的方法。

​	如果目标类是面向接口编程，则通JDK动态过代理实现。如果目标类没有实现接口，则通过CJLIB动态代理实现，在测试时候可以Debug观察Spring最终交给我们的目标对象的地址，JDK代理与CCJLIB代理在地址上会有明显区别。

​	AOP的实现离不开IOC，想要更好的了解AOP，后续学习从源码层面去看。



# 写在最前面

````xml
<!--本Demo配置了两个日志输出文件：-->
src/main/resources/LogAllLogs		//按照LEVEL生成日志文件
evil_log/src/main/resources/Log		//全部日志文件
````



# 浅谈日志门面

[浅谈日志门面](https://blog.csdn.net/jiangsir_sub/article/details/97522674?ops_request_misc=%25257B%252522request%25255Fid%252522%25253A%252522160800263919215668841035%252522%25252C%252522scm%252522%25253A%25252220140713.130102334..%252522%25257D&request_id=160800263919215668841035&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-97522674.nonecase&utm_term=%E6%97%A5%E5%BF%97%E9%97%A8%E9%9D%A2)

# logback.xml

[logback 常用配置](https://blog.csdn.net/qq_36850813/article/details/83092051?ops_request_misc=%25257B%252522request%25255Fid%252522%25253A%252522160853221916780288763682%252522%25252C%252522scm%252522%25253A%25252220140713.130102334..%252522%25257D&request_id=160853221916780288763682&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_click~default-1-83092051.nonecase&utm_term=logback%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E8%AF%A6%E8%A7%A3)


------------------------------
# 日志学习小结

## 1.logback.xml概述

<img src="https://img-blog.csdn.net/20181016182440618?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM2ODUwODEz/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70" width="40%" align=center/>

​	大体上讲<configuration>标签是logback.xml的最外层标签，子节点有三个appender、logger、root。

## 2.configuration

```xml
<!-- scan:当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。
     scanPeriod:设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。
     debug:当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。 -->
<configuration scan="true" scanPeriod="60 seconds" debug="false">
    
</configuration>
```

## 3.appender

​	<appender>有两个必要属性name和class。name指定appender名称，class指定appender的全限定名。对appender的理解是：输出到控制台还是输出到日志文件？以及日志输出具体策略是什么。下边是本Demo中使用的appender也是常用的几种。

### ConsoleAppender

​	把日志输出到控制台

```xml
<!--控制台输出-->
<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
    <!-- encoder 默认配置为PatternLayoutEncoder -->
    <encoder>
        <!--输出格式-->
        <pattern>
            %d{yyyy-MM-dd HH:mm:ss.SSS} [ %thread ] - [ %-5level ] [ %logger{50} : %line ] - %msg%n
        </pattern>
    </encoder>
</appender>
```

### FileAppender

​	把日志添加到文件中。

```xml
<configuration>  
  <appender name="FILE" class="ch.qos.logback.core.FileAppender">
	<!-- file：被写入的文件名，可以是相对目录，也可以是绝对目录，如果上级目录不存在会自动创建，没有默认值。
	-->
    <file>testFile.log</file>
	<!-- append：如果是 true，日志被追加到文件结尾，如果是 false，清空现存文件，默认是true。
	-->
    <append>true</append>
	<!-- encoder：对记录事件进行格式化。-->
    <encoder>
      <!--输出格式-->
      <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>  
    </encoder>
  </appender>
</configuration>
```

### RollingFileAppender

​	滚动记录文件，先将日志记录到指定文件，当符合某个条件时，将日志记录到其他文件。

​	示例一：

```xml
<!-- 逐级分类再按日期分类->
<!-滚动记录文件，先将日志记录到指定文件，当符合某个条件时，将日志记录到其他文件-->
<appender name="AllLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!-- 指定日志文件的名称 -->
    <file>${LOG_HOME}/Evil-Test.log</file>
    <!--
    当发生滚动时，决定 RollingFileAppender 的行为，涉及文件移动和重命名
    TimeBasedRollingPolicy： 最常用的滚动策略，它根据时间来制定滚动策略，既负责滚动也负责出发滚动。
    -->
    <!--每天生成一个日志文件，保存30天的日志文件 -->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <fileNamePattern>${LOG_HOME}/%d{yyyy-MM-dd}-%i.log</fileNamePattern>
        <maxHistory>30</maxHistory>
        <!--日志文件大小超过这个，大小会归档下标从0开始-->
        <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
            <maxFileSize>10KB</maxFileSize>
        </timeBasedFileNamingAndTriggeringPolicy>
    </rollingPolicy>
    <!--
    日志输出格式：%d表示日期时间，%thread表示线程名，%-5level：级别从左显示5个字符宽度 %logger{50} 表示logger名字最长50个字符，否则按照句点分割。 %msg：日志消息，%n是换行符
    -->
    <layout class="ch.qos.logback.classic.PatternLayout">
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [ %thread ] - [ %-5level ] [ %logger{50} : %line ] 111- %msg%n</pattern>
    </layout>
</appender>
```

​	运行项目先回将日志记录到`<file>${LOG_HOME}/Evil-Test.log</file>`文件下，当该文件符合条件：同一天`Evil-Test.log`超过10KB，则生成新的文件；日志的日期不同，生成新的日志文件。

​	关于`<fileNamePattern>${LOG_HOME}/%d{yyyy-MM-dd}.%i.log</fileNamePattern>`配置，`%i`指的是当日志大小超过配置`<maxFileSize>10MB</maxFileSize>`的大小就会生成新的日志文件，文件名为：`${LOG_HOME}/%d{yyyy-MM-dd}.%i.log`（i从0开始递增），生成新的日志文件。

​	示例二：

```xml
<!-- 2.1 level为 DEBUG 日志，时间滚动输出  -->
    <appender name="DEBUG_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 正在记录的日志文档的路径及文档名 -->
        <file>${logging.path}/debug.log</file>
        <!--日志文档输出格式-->
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
            <charset>UTF-8</charset> <!-- 设置字符集 -->
        </encoder>
        <!-- 日志记录器的滚动策略，按日期，按大小记录 -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- 日志归档 -->
            <fileNamePattern>${logging.path}/debug-%d{yyyy-MM-dd}-%-.log</fileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>100MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
            <!--日志文档保留天数-->
            <maxHistory>15</maxHistory>
        </rollingPolicy>
        <!-- 此日志文档只记录debug级别的 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <!--level:设置过滤级别-->
            <level>info</level>
            <!--onMatch:用于配置符合过滤条件的操作-->
            <onMatch>ACCEPT</onMatch>
            <!--onMismatch:用于配置不符合过滤条件的操作-->
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>
```

​	**LevelFilter：** 将过滤器的日志级别配置为INFO，所有INFO级别的日志交给appender处理，非INFO级别的日志，被过滤掉。

## 4.Logger与Root

Logger：

````xml
 <logger>用来设置某一个包或者具体的某一个类的日志打印级别、
        以及指定<appender>。<logger>仅有一个name属性，
        一个可选的level和一个可选的addtivity属性。
        name:用来指定受此logger约束的某一个包或者具体的某一个类。
        level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，
              还有一个特俗值INHERITED或者同义词NULL，代表强制执行上级的级别。
              如果未设置此属性，那么当前logger将会继承上级的级别。
        addtivity:是否向上级logger传递打印信息。默认是true。
  <!-- logback为java中的包 -->   
  <logger name="logback"/>   
  <!--logback.LogbackDemo：类的全路径 -->   
  <logger name="logback.LogbackDemo" level="INFO" additivity="false">
````

root：

```xml
root是特殊的<logger>元素，是根logger。只有一个level属性，应为已经被命名为"root".
level:设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，不能设置为INHERITED或者同义词NULL。默认是DEBUG。

root与logger是父子关系，没有特别定义则默认为root，任何一个类只会和一个logger对应，要么是定义的logger，要么是root，判断的关键在于找到这个logger，然后判断这个logger的appender和level.
<root>可以包含零个或多个<appender-ref>元素，标识这个appender将会添加到这个loger。
<root level="trace">
    <appender-ref ref="CONSOLE" />
    <appender-ref ref="AllLog"/>
</root>
```







