PS C:\xampp\htdocs\Proyecto_LP_III>  c:; cd 'c:\xampp\htdocs\Proyecto_LP_III'; & 'C:\Program Files\Java\jdk-21\bin\java.exe' '@C:\Users\braya\AppData\Local\Temp\cp_apoajo7038nfkq6osdi5eiy18.argfile'
 'proyecto.lp.iii.api.ApiApplication'

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.7)

2026-07-02T13:31:49.090-05:00  INFO 32384 --- [api] [  restartedMain] proyecto.lp.iii.api.ApiApplication       : Starting ApiApplication using Java 21.0.2 with PID 32384 (C:\xampp\htdocs\Proyecto_LP_III\target\classes started by braya in C:\xampp\htdocs\Proyecto_LP_III)
2026-07-02T13:31:49.093-05:00  INFO 32384 --- [api] [  restartedMain] proyecto.lp.iii.api.ApiApplication       : No active profile set, falling back to 1 default profile: "default"
2026-07-02T13:31:49.178-05:00  INFO 32384 --- [api] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2026-07-02T13:31:49.178-05:00  INFO 32384 --- [api] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2026-07-02T13:31:50.435-05:00  INFO 32384 --- [api] [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2026-07-02T13:31:50.678-05:00  INFO 32384 --- [api] [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 233 ms. Found 56 JPA repository interfaces.
2026-07-02T13:31:54.571-05:00  INFO 32384 --- [api] [  restartedMain] o.s.boot.tomcat.TomcatWebServer          : Tomcat initialized with port 8080 (http)
2026-07-02T13:31:54.632-05:00  INFO 32384 --- [api] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-07-02T13:31:54.633-05:00  INFO 32384 --- [api] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/11.0.22]
2026-07-02T13:31:54.871-05:00  INFO 32384 --- [api] [  restartedMain] b.w.c.s.WebApplicationContextInitializer : Root WebApplicationContext: initialization completed in 5692 ms
2026-07-02T13:31:55.795-05:00  INFO 32384 --- [api] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2026-07-02T13:31:56.410-05:00  INFO 32384 --- [api] [  restartedMain] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@4caa9c4e
2026-07-02T13:31:56.412-05:00  INFO 32384 --- [api] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2026-07-02T13:31:56.496-05:00  INFO 32384 --- [api] [  restartedMain] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
2026-07-02T13:31:56.595-05:00  INFO 32384 --- [api] [  restartedMain] org.hibernate.orm.core                   : HHH000001: Hibernate ORM core version 7.2.19.Final
2026-07-02T13:31:57.281-05:00  INFO 32384 --- [api] [  restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2026-07-02T13:31:57.379-05:00  WARN 32384 --- [api] [  restartedMain] org.hibernate.orm.core                   : HHH000511: The 5.5.5 version for [org.hibernate.dialect.MySQLDialect] is no longer supported, hence certain features may not work properly.The minimum supported version is 8.0.0. Check the community dialects project for available legacy versions.
2026-07-02T13:31:57.382-05:00  WARN 32384 --- [api] [  restartedMain] org.hibernate.orm.deprecation            : HHH90000025: MySQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2026-07-02T13:31:57.400-05:00  INFO 32384 --- [api] [  restartedMain] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
        Database JDBC URL [jdbc:mysql://localhost:3307/bellaaristav3]
        Database driver: MySQL Connector/J
        Database dialect: MySQLDialect
        Database version: 5.5.5
        Default catalog/schema: bellaaristav3/undefined
        Autocommit mode: undefined/unknown
        Isolation level: REPEATABLE_READ [default REPEATABLE_READ]
        JDBC fetch size: none
        Pool: DataSourceConnectionProvider
        Minimum pool size: undefined/unknown
        Maximum pool size: undefined/unknown
2026-07-02T13:31:59.319-05:00  INFO 32384 --- [api] [  restartedMain] org.hibernate.orm.core                   : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2026-07-02T13:31:59.325-05:00  INFO 32384 --- [api] [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2026-07-02T13:31:59.459-05:00  INFO 32384 --- [api] [  restartedMain] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath; If applicable, HQL parser will be used.
2026-07-02T13:31:59.913-05:00  WARN 32384 --- [api] [  restartedMain] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'auditoriaController': Unsatisfied dependency expressed through field 'serviceAuditoria': Error creating bean with name 'auditoriaService': Unsatisfied dependency expressed through field 'repoAuditoria': Error creating bean with name 'auditoriaRepository' defined in proyecto.lp.iii.api.repository.AuditoriaRepository defined in @EnableJpaRepositories declared on ApiApplication: No property 'idTenantsIdTenants' found for type 'Auditoria'
2026-07-02T13:31:59.914-05:00  INFO 32384 --- [api] [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2026-07-02T13:31:59.920-05:00  INFO 32384 --- [api] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2026-07-02T13:31:59.930-05:00  INFO 32384 --- [api] [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
2026-07-02T13:31:59.932-05:00  INFO 32384 --- [api] [  restartedMain] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2026-07-02T13:31:59.945-05:00  INFO 32384 --- [api] [  restartedMain] .s.b.a.l.ConditionEvaluationReportLogger :

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2026-07-02T13:31:59.963-05:00 ERROR 32384 --- [api] [  restartedMain] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'auditoriaController': Unsatisfied dependency expressed through field 'serviceAuditoria': Error creating bean with name 'auditoriaService': Unsatisfied dependency expressed through field 'repoAuditoria': Error creating bean with name 'auditoriaRepository' defined in proyecto.lp.iii.api.repository.AuditoriaRepository defined in @EnableJpaRepositories declared on ApiApplication: No property 'idTenantsIdTenants' found for type 'Auditoria'
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:767) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:748) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:146) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:493) ~[spring-beans-7.0.8.jar:7.0.8]     
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1444) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:602) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:525) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:333) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:371) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:331) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:196) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.instantiateSingleton(DefaultListableBeanFactory.java:1225) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingleton(DefaultListableBeanFactory.java:1191) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:1121) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:994) ~[spring-context-7.0.8.jar:7.0.8]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:621) ~[spring-context-7.0.8.jar:7.0.8]
        at org.springframework.boot.web.server.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:143) ~[spring-boot-web-server-4.0.7.jar:4.0.7]      
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:756) ~[spring-boot-4.0.7.jar:4.0.7]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:445) ~[spring-boot-4.0.7.jar:4.0.7]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:321) ~[spring-boot-4.0.7.jar:4.0.7]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1365) ~[spring-boot-4.0.7.jar:4.0.7]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1354) ~[spring-boot-4.0.7.jar:4.0.7]
        at proyecto.lp.iii.api.ApiApplication.main(ApiApplication.java:14) ~[classes/:na]
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
        at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:52) ~[spring-boot-devtools-4.0.7.jar:4.0.7]
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'auditoriaService': Unsatisfied dependency expressed through field 'repoAuditoria': Error creating bean with name 'auditoriaRepository' defined in proyecto.lp.iii.api.repository.AuditoriaRepository defined in @EnableJpaRepositories declared on ApiApplication: No property 'idTenantsIdTenants' found for type 'Auditoria'
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:767) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:748) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:146) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:493) ~[spring-beans-7.0.8.jar:7.0.8]     
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1444) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:602) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:525) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:333) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:371) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:331) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:201) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:229) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1769) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1658) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:764) ~[spring-beans-7.0.8.jar:7.0.8]
        ... 25 common frames omitted
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'auditoriaRepository' defined in proyecto.lp.iii.api.repository.AuditoriaRepository defined in @EnableJpaRepositories declared on ApiApplication: No property 'idTenantsIdTenants' found for type 'Auditoria'
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1815) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:603) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:525) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:333) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:371) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:331) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:201) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:229) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1769) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1658) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:764) ~[spring-beans-7.0.8.jar:7.0.8]
        ... 39 common frames omitted
Caused by: org.springframework.data.repository.query.QueryCreationException: Cannot create query for method [AuditoriaRepository.findByIdTenantsIdTenants(java.lang.Integer)]; No property 'idTenantsIdTenants' found for type 'Auditoria'
        at org.springframework.data.repository.query.QueryCreationException.create(QueryCreationException.java:109) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.jpa.repository.query.PartTreeJpaQuery.<init>(PartTreeJpaQuery.java:114) ~[spring-data-jpa-4.0.6.jar:4.0.6]
        at org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy$CreateQueryLookupStrategy.resolveQuery(JpaQueryLookupStrategy.java:118) ~[spring-data-jpa-4.0.6.jar:4.0.6]
        at org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy$CreateIfNotFoundQueryLookupStrategy.resolveQuery(JpaQueryLookupStrategy.java:291) ~[spring-data-jpa-4.0.6.jar:4.0.6]  
        at org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy$AbstractQueryLookupStrategy.resolveQuery(JpaQueryLookupStrategy.java:93) ~[spring-data-jpa-4.0.6.jar:4.0.6]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.lookupQuery(QueryExecutorMethodInterceptor.java:114) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.mapMethodsToQuery(QueryExecutorMethodInterceptor.java:102) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.<init>(QueryExecutorMethodInterceptor.java:90) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.repository.core.support.RepositoryFactorySupport.getRepository(RepositoryFactorySupport.java:413) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport.lambda$afterPropertiesSet$1(RepositoryFactoryBeanSupport.java:361) ~[spring-data-commons-4.0.6.jar:4.0.6]    
        at org.springframework.data.util.Lazy.getNullable(Lazy.java:136) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.util.Lazy.get(Lazy.java:114) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport.afterPropertiesSet(RepositoryFactoryBeanSupport.java:370) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean.afterPropertiesSet(JpaRepositoryFactoryBean.java:212) ~[spring-data-jpa-4.0.6.jar:4.0.6]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1862) ~[spring-beans-7.0.8.jar:7.0.8]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1811) ~[spring-beans-7.0.8.jar:7.0.8]
        ... 49 common frames omitted
Caused by: org.springframework.data.core.PropertyReferenceException: No property 'idTenantsIdTenants' found for type 'Auditoria'
        at org.springframework.data.core.SimplePropertyPath.<init>(SimplePropertyPath.java:94) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.core.SimplePropertyPath.create(SimplePropertyPath.java:365) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.core.SimplePropertyPath.create(SimplePropertyPath.java:340) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.core.SimplePropertyPath.lambda$from$0(SimplePropertyPath.java:293) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.util.ConcurrentReferenceHashMap$6.execute(ConcurrentReferenceHashMap.java:387) ~[spring-core-7.0.8.jar:7.0.8]
        at org.springframework.util.ConcurrentReferenceHashMap$Segment.doTask(ConcurrentReferenceHashMap.java:688) ~[spring-core-7.0.8.jar:7.0.8]
        at org.springframework.util.ConcurrentReferenceHashMap.doTask(ConcurrentReferenceHashMap.java:572) ~[spring-core-7.0.8.jar:7.0.8]
        at org.springframework.util.ConcurrentReferenceHashMap.computeIfAbsent(ConcurrentReferenceHashMap.java:381) ~[spring-core-7.0.8.jar:7.0.8]
        at org.springframework.data.core.SimplePropertyPath.from(SimplePropertyPath.java:271) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.core.PropertyPath.from(PropertyPath.java:210) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.core.PropertyPath.from(PropertyPath.java:193) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.repository.query.parser.Part.<init>(Part.java:81) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.repository.query.parser.PartTree$OrPart.lambda$new$0(PartTree.java:260) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179) ~[na:na]
        at java.base/java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:1024) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499) ~[na:na]
        at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682) ~[na:na]
        at org.springframework.data.repository.query.parser.PartTree$OrPart.<init>(PartTree.java:261) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.repository.query.parser.PartTree$Predicate.lambda$new$0(PartTree.java:390) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179) ~[na:na]
        at java.base/java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:1024) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499) ~[na:na]
        at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921) ~[na:na]
        at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234) ~[na:na]
        at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682) ~[na:na]
        at org.springframework.data.repository.query.parser.PartTree$Predicate.<init>(PartTree.java:391) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.repository.query.parser.PartTree.<init>(PartTree.java:104) ~[spring-data-commons-4.0.6.jar:4.0.6]
        at org.springframework.data.jpa.repository.query.PartTreeJpaQuery.<init>(PartTreeJpaQuery.java:108) ~[spring-data-jpa-4.0.6.jar:4.0.6]
        ... 63 common frames omitted