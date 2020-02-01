 ### Guard权限管理框架

Guard是用于SpringMVC环境的权限管理框架，通过简单的注解甚至代码规范的情况下无需指定任何注解即可开箱即用的权限框架。

 # Demo使用说明
直接用运行位于guard-demo模块下的Application主函数即可，
访问地址：http://localhost:8080/order 或 http://localhost:8080/insurance
header中请添加key=user, value可取值：zhangsan/lisi/wangsu/zhaoliu/weihua,分别对应的权限见resource文件夹中的几个json设置，也可更改后查看变化