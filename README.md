# seckill

模拟秒杀系统实现

来自慕课网《SpringBoot构建电商基础秒杀项目》
地址：https://www.imooc.com/learn/1079

改进：
* 运用@ControllerAdvice和@ExceptionHandler进行全局异常捕获，不用继承基类
* 运用@Valid对实体进行参数校验，简化代码

* 前端优化倒计时格式，将总秒数改为 X天X时X分X秒 的形式
* 登录成功后可以返回之前查看的页面
