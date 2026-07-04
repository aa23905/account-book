# 📒 Account Book - 个人记账系统

一个基于 Spring Boot + MyBatis + MySQL 的个人记账 REST API 后端项目。

## 功能特性

| 功能 | 说明 |
  |------|------|
| ✅ 分类管理 | 收支分类的 CRUD，支持 INCOME / EXPENSE 类型 |
| ✅ 交易记录 | 记账流水 CRUD，含金额、分类、日期、备注 |
| ✅ 分页查询 | 支持交易记录分页 + 按分类过滤分页 |
| ✅ 按分类统计 | GROUP BY + JOIN 统计各分类收支汇总 |
| ✅ JWT 认证 | 用户注册/登录，BCrypt 密码加密，JWT Token 鉴权 |
| ✅ 统一响应 | ApiResponse 统一包装返回格式 |
| ✅ 全局异常处理 | @ControllerAdvice 统一拦截异常 |
| ✅ 日志系统 | 控制台 + 文件日志，按日期滚动，保留 7 天 |
