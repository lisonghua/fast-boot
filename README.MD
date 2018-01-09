# Fast Boot

基于Spring Cloud实现微服务平台，支持服务注册、发现、健康检查、网关、断路器、负载均衡等功能。

## 工程结构说明：
![](docs/Fast-Boot-reference.jpg)

## 模块说明：

### fast-boot
  所有模块的父工程，全局通用配置。

### fast-boot-core
  基于fast-boot开发非微服务时核心依赖工程

### fast-boot-sample
  基于fast-boot开发的应用示例程序

### fast-cloud-core
  开发基于fast-cloud微服务应用核心依赖工程

### fast-cloud-dependencies
  开发微服务的依赖工程
### fast-api-gateway
  微服务网关应用

### fast-config-server
  微服务配置中心应用
### fast-hystrix-server
  基于hystrix微服务健康检查
  
### fast-monitor-server
  微服务监控应用
  
### fast-register-server
  微服务注册和发现应用
### fast-turbine
  基于turbine实现集群环境微服务健康检查应用

### fast-boot-security:
  基于fast-boot开发的权限管理应用
#### 整合 Spring Security 提供一下功能：

* [x] 前后端分离

* [x] 权限认证 
 
* [x] 权限管理
 
* [x] 用户管理 

* [x] 角色管理 

* [x] 日志管理

#### 页面效果：

![用户管理](resources/sanji-boot-security-user.png)

![角色管理](resources/sanji-boot-security-role.png)

![权限管理](resources/sanji-boot-security-sec.png)

![权限编辑](resources/sanji-boot-security-sec-add.png)

![日志管理](resources/sanji-boot-security-log.png)

### archetype
