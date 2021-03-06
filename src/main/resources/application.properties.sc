mybatis-plus.global-config.id-type = 1
mybatis-plus.global-config.key-generator = com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator
spring.datasource.druid.driver-class-name = oracle.jdbc.OracleDriver

spring.datasource.druid.url = jdbc:oracle:thin:@r16670.longhu.net:1521:FSSCRPT1
spring.datasource.druid.username = fsscreport
spring.datasource.druid.password = LsY6kPWYuJZf1SsY

#spring.datasource.druid.url = LAPTOP-G39CQ7BA.lhwork.net:1521:orcl
#spring.datasource.druid.username = fsscreport
#spring.datasource.druid.password = 123456

spring.datasource.druid.initial-size = 10
spring.datasource.druid.min-idle = 10
spring.datasource.druid.max-active = 20
spring.datasource.druid.validation-query = select * from dual

server.port = 8082
server.servlet.context-path = /fssc_rptdata

xxl.job.admin.addresses = http://m7-hlgw-paas-job.longhu.net
xxl.job.accessToken = e8o2m735
xxl.job.executor.appname = fsscRptData
xxl.job.executor.port = 8080
xxl.job.executor.logpath = /home/lhadmin/jarFile/jobhandler
xxl.job.executor.logretentiondays = -1
xxl.job.executor.ip = 
xxl.job.executor.address = 

kettel.host1 = 10.12.182.40
kettel.host2 = 10.12.182.39
kettel.port = 9081

kettel.job.path=job