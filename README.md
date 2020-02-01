 # Guard权限管理框架

 ## 目的
 Guard可以通过注解实现用户对接口数据的过滤及访问控制。
 开发阶段，只需要定义资源（Resource）及访问接口需要的权限（Permission）；
 用户、角色、权限三者的关系可以根据配置控制，这种配置是事后的，可以json、配置中心、管理平台完成。
 
 ## 原理介绍
 
 ### @Resource注解
 Resource注解定义资源，它的含义与Restful提倡的资源同义，一般置于Controller类上，以表示该类中定义的接口都需要在该资源上拥有权限，如下代码：
 ```
 @Resource("order")
 @RestController
 @RequestMapping("order")
 public class OrderController{
 //省略方法
 }
 ```
 
 ### @PermissionType, @Create, @Retrieve, @Update, @Delete, @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
 严格模式下（通过guard.strict.mode=true设定），自定识别SpringMVC定义的@GetMapping, @PostMapping, @PutMapping, @DeleteMapping，
 即，不需要任何额外注解，使用@GetMapping注解即表示该接口为获取数据接口，所需权限类型就是Retrieve，只要用户拥有当前资源下的Retrieve权限即可访问，
 @PostMapping, @PutMapping, @DeleteMapping三个注解以此类推。如：
 ```
 @GetMapping("order/id/{id}")
 public Response<Order> findById(@PathVariable String id){
   return null;
 }
 ```
 上面方法的定义做到了无任何侵入性，没有任何额外代码，接口规范的情况下，当然首选以上方式。
 
 但非严格模式下（通过guard.strict.mode=false设定，或默认不配置），采用PostMapping定义的接口也可能是获取数据，此时需要开发者自己显式声明，如：
 ```
 @Retrieve
 @PostMapping("order/search")
 public Response<Order> findById(@RequestBody SearchBean searchBean){
   return null;
 }
 ```

 ## Demo使用说明
直接用运行位于guard-demo模块下的Application主函数即可，
访问地址：http://localhost:8080/order 或 http://localhost:8080/insurance
header中请添加key=user, value可取值：zhangsan/lisi/wangsu/zhaoliu/weihua,分别对应的权限见resource文件夹中的几个json设置，也可更改后查看变化