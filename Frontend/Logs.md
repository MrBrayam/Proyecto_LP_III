Error: org.hibernate.TransientPropertyValueException: Instance of 'proyecto.lp.iii.api.entity.Usuarios' references an unsaved transient instance of 'proyecto.lp.iii.api.entity.Tenants' (persist the transient instance) [proyecto.lp.iii.api.entity.Usuarios.id_tenants -> proyecto.lp.iii.api.entity.Tenants]

[belleza@spring api]$ java -Xmx256M -XX:MaxMetaspaceSize=128M -jar api-0.0.1-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.7)

2026-07-10T14:28:05.288Z  INFO 100774 --- [api] [           main] proyecto.lp.iii.api.ApiApplication       : Starting ApiApplication v0.0.1-SNAPSHOT using Java 17.0.19 with PID 100774 (/home/belleza/public_html/api/api-0.0.1-SNAPSHOT.jar started by belleza in /home/belleza/public_html/api)
2026-07-10T14:28:05.293Z  INFO 100774 --- [api] [           main] proyecto.lp.iii.api.ApiApplication       : No active profile set, falling back to 1 default profile: "default"
2026-07-10T14:28:06.358Z  INFO 100774 --- [api] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2026-07-10T14:28:06.610Z  INFO 100774 --- [api] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 236 ms. Found 56 JPA repository interfaces.
2026-07-10T14:28:08.327Z  INFO 100774 --- [api] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat initialized with port 2451 (http)
2026-07-10T14:28:08.352Z  INFO 100774 --- [api] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-07-10T14:28:08.353Z  INFO 100774 --- [api] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/11.0.22]
2026-07-10T14:28:08.400Z  INFO 100774 --- [api] [           main] b.w.c.s.WebApplicationContextInitializer : Root WebApplicationContext: initialization completed in 3002 ms
2026-07-10T14:28:08.804Z  INFO 100774 --- [api] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2026-07-10T14:28:09.064Z  INFO 100774 --- [api] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@6ac9b66b
2026-07-10T14:28:09.067Z  INFO 100774 --- [api] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2026-07-10T14:28:09.106Z  INFO 100774 --- [api] [           main] org.hibernate.orm.jpa                    : HHH008540: Processing PersistenceUnitInfo [name: default]
2026-07-10T14:28:09.176Z  INFO 100774 --- [api] [           main] org.hibernate.orm.core                   : HHH000001: Hibernate ORM core version 7.2.19.Final
2026-07-10T14:28:09.882Z  INFO 100774 --- [api] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2026-07-10T14:28:09.969Z  WARN 100774 --- [api] [           main] org.hibernate.orm.core                   : HHH000511: The 5.5.5 version for [org.hibernate.dialect.MySQLDialect] is no longer supported, hence certain features may not work properly.The minimum supported version is 8.0.0. Check the community dialects project for available legacy versions.
2026-07-10T14:28:09.974Z  WARN 100774 --- [api] [           main] org.hibernate.orm.deprecation            : HHH90000025: MySQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2026-07-10T14:28:09.992Z  INFO 100774 --- [api] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
        Database JDBC URL [jdbc:mysql://localhost:3306/belleza_arista]
        Database driver: MySQL Connector/J
        Database dialect: MySQLDialect
        Database version: 5.5.5
        Default catalog/schema: belleza_arista/undefined
        Autocommit mode: undefined/unknown
        Isolation level: REPEATABLE_READ [default REPEATABLE_READ]
        JDBC fetch size: none
        Pool: DataSourceConnectionProvider
        Minimum pool size: undefined/unknown
        Maximum pool size: undefined/unknown
2026-07-10T14:28:12.319Z  INFO 100774 --- [api] [           main] org.hibernate.orm.core                   : HHH000489: No JTA platformavailable (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2026-07-10T14:28:12.329Z  INFO 100774 --- [api] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2026-07-10T14:28:12.472Z  INFO 100774 --- [api] [           main] o.s.d.j.r.query.QueryEnhancerFactories   : Hibernate is in classpath;If applicable, HQL parser will be used.
2026-07-10T14:28:15.888Z  WARN 100774 --- [api] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view isenabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view todisable this warning
2026-07-10T14:28:16.064Z  WARN 100774 --- [api] [           main] .s.a.UserDetailsServiceAutoConfiguration :

Using generated security password: f43464e9-896b-4fca-9453-eb5aa9820a82

This generated password is for development use only. Your security configuration must be updated before running your application in production.

2026-07-10T14:28:16.081Z  INFO 100774 --- [api] [           main] r$InitializeUserDetailsManagerConfigurer : Global AuthenticationManager configured with UserDetailsService bean with name inMemoryUserDetailsManager
2026-07-10T14:28:16.739Z  INFO 100774 --- [api] [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 1 endpoint beneath base path '/actuator'
2026-07-10T14:28:16.930Z  WARN 100774 --- [api] [           main] ion$DefaultTemplateResolverConfiguration : Cannot find template location: classpath:/templates/ (please add some templates, check your Thymeleaf configuration, or set spring.thymeleaf.check-template-location=false)
2026-07-10T14:28:17.324Z  INFO 100774 --- [api] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat started on port 2451 (http) with context path '/'
2026-07-10T14:28:17.336Z  INFO 100774 --- [api] [           main] proyecto.lp.iii.api.ApiApplication       : Started ApiApplication in 12.772 seconds (process running for 13.43)
2026-07-10T14:28:26.156Z  INFO 100774 --- [api] [nio-2451-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-07-10T14:28:26.157Z  INFO 100774 --- [api] [nio-2451-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-07-10T14:28:26.159Z  INFO 100774 --- [api] [nio-2451-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms
Hibernate: select r1_0.idregistro,r1_0.access_token,r1_0.apellidos,r1_0.cliente_id,r1_0.email,r1_0.estado,r1_0.llave_secreta,r1_0.nombres from registros r1_0 where (r1_0.estado=1)
Hibernate: select t1_0.id_tenants,t1_0.correo,t1_0.direccion_fiscal,t1_0.estado,t1_0.fecha_registro,t1_0.nombre_comercial,t1_0.razon_social,t1_0.ruc,t1_0.telefono,t1_0.tipo_negocio from tenants t1_0 where (t1_0.estado=1)
Hibernate: select u1_0.id_usuarios,u1_0.apellidos_usuario,u1_0.contraseña,u1_0.correo,u1_0.estado,u1_0.id_tenants,u1_0.nombre_usuario,u1_0.numero_documento,u1_0.tipo_usuario from usuarios u1_0 where (u1_0.estado=1)
Hibernate: select t1_0.id_tenants,t1_0.correo,t1_0.direccion_fiscal,t1_0.estado,t1_0.fecha_registro,t1_0.nombre_comercial,t1_0.razon_social,t1_0.ruc,t1_0.telefono,t1_0.tipo_negocio from tenants t1_0 where t1_0.id_tenants=? and (t1_0.estado=1)
Hibernate: select t1_0.id_tenants,t1_0.correo,t1_0.direccion_fiscal,t1_0.estado,t1_0.fecha_registro,t1_0.nombre_comercial,t1_0.razon_social,t1_0.ruc,t1_0.telefono,t1_0.tipo_negocio from tenants t1_0 where t1_0.id_tenants=? and (t1_0.estado=1)
Hibernate: select t1_0.id_tenants,t1_0.correo,t1_0.direccion_fiscal,t1_0.estado,t1_0.fecha_registro,t1_0.nombre_comercial,t1_0.razon_social,t1_0.ruc,t1_0.telefono,t1_0.tipo_negocio from tenants t1_0 where t1_0.id_tenants=? and (t1_0.estado=1)
Hibernate: select t1_0.id_tenants,t1_0.correo,t1_0.direccion_fiscal,t1_0.estado,t1_0.fecha_registro,t1_0.nombre_comercial,t1_0.razon_social,t1_0.ruc,t1_0.telefono,t1_0.tipo_negocio from tenants t1_0 where t1_0.id_tenants=? and (t1_0.estado=1)
Hibernate: select t1_0.id_tenants,t1_0.correo,t1_0.direccion_fiscal,t1_0.estado,t1_0.fecha_registro,t1_0.nombre_comercial,t1_0.razon_social,t1_0.ruc,t1_0.telefono,t1_0.tipo_negocio from tenants t1_0 where t1_0.id_tenants=? and (t1_0.estado=1)
Hibernate: select t1_0.id_tenants,t1_0.correo,t1_0.direccion_fiscal,t1_0.estado,t1_0.fecha_registro,t1_0.nombre_comercial,t1_0.razon_social,t1_0.ruc,t1_0.telefono,t1_0.tipo_negocio from tenants t1_0 where (t1_0.estado=1)
Hibernate: insert into tenants (correo,direccion_fiscal,estado,fecha_registro,nombre_comercial,razon_social,ruc,telefono,tipo_negocio) values (?,?,?,?,?,?,?,?,?)
Hibernate: select u1_0.id_usuarios,u1_0.apellidos_usuario,u1_0.contraseña,u1_0.correo,u1_0.estado,u1_0.id_tenants,u1_0.nombre_usuario,u1_0.numero_documento,u1_0.tipo_usuario from usuarios u1_0 where (u1_0.estado=1)
2026-07-10T14:28:36.920Z  WARN 100774 --- [api] [nio-2451-exec-4] org.hibernate.orm.action                 : HHH90032003: Attempting tosave one or more entities that have a non-nullable association with an unsaved transient entity.
The unsaved transient entity must be saved in an operation prior to saving these dependent entities.
        Unsaved transient entity: [proyecto.lp.iii.api.entity.Tenants with null id]
        Dependent entities: [[proyecto.lp.iii.api.entity.Usuarios with null id]]
        Non-nullable associations: [proyecto.lp.iii.api.entity.Usuarios.id_tenants]
org.springframework.dao.InvalidDataAccessApiUsageException: org.hibernate.TransientPropertyValueException: Instance of 'proyecto.lp.iii.api.entity.Usuarios' references an unsaved transient instance of 'proyecto.lp.iii.api.entity.Tenants' (persist the transient instance) [proyecto.lp.iii.api.entity.Usuarios.id_tenants -> proyecto.lp.iii.api.entity.Tenants]
        at org.springframework.orm.jpa.EntityManagerFactoryUtils.convertJpaAccessExceptionIfPossible(EntityManagerFactoryUtils.java:479)
        at org.springframework.orm.jpa.hibernate.HibernateExceptionTranslator.translateExceptionIfPossible(HibernateExceptionTranslator.java:112)
        at org.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:223)
        at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.translateExceptionIfPossible(AbstractEntityManagerFactoryBean.java:577)
        at org.springframework.dao.support.ChainedPersistenceExceptionTranslator.translateExceptionIfPossible(ChainedPersistenceExceptionTranslator.java:61)
        at org.springframework.dao.support.DataAccessUtils.translateIfNecessary(DataAccessUtils.java:346)
        at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:157)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
        at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:166)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
        at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:222)
        at jdk.proxy2/jdk.proxy2.$Proxy200.save(Unknown Source)
        at proyecto.lp.iii.api.service.jpa.UsuariosService.guardar(UsuariosService.java:30)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:569)
        at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:359)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:190)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:158)
        at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:82)
        at proyecto.lp.iii.api.aspect.AuditAspect.aroundGuardar(AuditAspect.java:46)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:569)
        at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:648)
        at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:630)
        at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
        at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:133)
        at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:371)
        at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:130)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
        at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:96)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
        at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:719)
        at proyecto.lp.iii.api.service.jpa.UsuariosService$$SpringCGLIB$$0.guardar(<generated>)
        at proyecto.lp.iii.api.controller.PageController.crearTenant(PageController.java:562)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:569)
        at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:359)
        at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:715)
        at proyecto.lp.iii.api.controller.PageController$$SpringCGLIB$$0.crearTenant(<generated>)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:569)
        at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:252)
        at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:184)
        at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:934)
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:853)
        at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:86)
        at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963)
        at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:866)
        at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1000)
        at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:903)
        at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:649)
        at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:874)
        at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:710)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:128)
        at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:107)
        at proyecto.lp.iii.api.security.JwtFilter.doFilter(JwtFilter.java:45)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:107)
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:108)
        at org.springframework.security.web.FilterChainProxy.lambda$doFilterInternal$3(FilterChainProxy.java:235)
        at org.springframework.security.web.ObservationFilterChainDecorator$FilterObservation$SimpleFilterObservation.lambda$wrap$1(ObservationFilterChainDecorator.java:493)
        at org.springframework.security.web.ObservationFilterChainDecorator$AroundFilterObservation$SimpleAroundFilterObservation.lambda$wrap$1(ObservationFilterChainDecorator.java:354)
        at org.springframework.security.web.ObservationFilterChainDecorator.lambda$wrapSecured$0(ObservationFilterChainDecorator.java:86)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:132)
        at org.springframework.security.web.access.intercept.AuthorizationFilter.doFilter(AuthorizationFilter.java:101)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.wrapFilter(ObservationFilterChainDecorator.java:244)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.doFilter(ObservationFilterChainDecorator.java:231)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:141)
        at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:126)
        at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:120)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.wrapFilter(ObservationFilterChainDecorator.java:244)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.doFilter(ObservationFilterChainDecorator.java:231)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:141)
        at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:100)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.wrapFilter(ObservationFilterChainDecorator.java:244)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.doFilter(ObservationFilterChainDecorator.java:231)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:141)
        at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:181)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.wrapFilter(ObservationFilterChainDecorator.java:244)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.doFilter(ObservationFilterChainDecorator.java:231)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:141)
        at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.wrapFilter(ObservationFilterChainDecorator.java:244)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.doFilter(ObservationFilterChainDecorator.java:231)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:141)
        at proyecto.lp.iii.api.security.JwtFilter.doFilter(JwtFilter.java:45)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.wrapFilter(ObservationFilterChainDecorator.java:244)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.doFilter(ObservationFilterChainDecorator.java:231)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:141)
        at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:110)
        at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:96)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.wrapFilter(ObservationFilterChainDecorator.java:244)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.doFilter(ObservationFilterChainDecorator.java:231)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:141)
        at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.wrapFilter(ObservationFilterChainDecorator.java:244)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.doFilter(ObservationFilterChainDecorator.java:231)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:141)
        at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)
        at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.wrapFilter(ObservationFilterChainDecorator.java:244)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.doFilter(ObservationFilterChainDecorator.java:231)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:141)
        at org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(SecurityContextHolderFilter.java:82)
        at org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(SecurityContextHolderFilter.java:69)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.wrapFilter(ObservationFilterChainDecorator.java:244)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.doFilter(ObservationFilterChainDecorator.java:231)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:141)
        at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:62)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.wrapFilter(ObservationFilterChainDecorator.java:244)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.doFilter(ObservationFilterChainDecorator.java:231)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:141)
        at org.springframework.security.web.session.DisableEncodeUrlFilter.doFilterInternal(DisableEncodeUrlFilter.java:42)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.wrapFilter(ObservationFilterChainDecorator.java:244)
        at org.springframework.security.web.ObservationFilterChainDecorator$AroundFilterObservation$SimpleAroundFilterObservation.lambda$wrap$0(ObservationFilterChainDecorator.java:337)
        at org.springframework.security.web.ObservationFilterChainDecorator$ObservationFilter.doFilter(ObservationFilterChainDecorator.java:228)
        at org.springframework.security.web.ObservationFilterChainDecorator$VirtualFilterChain.doFilter(ObservationFilterChainDecorator.java:141)
        at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:237)
        at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:195)
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113)
        at org.springframework.web.filter.ServletRequestPathFilter.doFilter(ServletRequestPathFilter.java:52)
        at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113)
        at org.springframework.web.filter.CompositeFilter.doFilter(CompositeFilter.java:74)
        at org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration$CompositeFilterChainProxy.doFilter(WebSecurityConfiguration.java:317)
        at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:355)
        at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:272)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:107)
        at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:107)
        at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:107)
        at org.springframework.web.filter.ServerHttpObservationFilter.doFilterInternal(ServerHttpObservationFilter.java:110)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:107)
        at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:199)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:107)
        at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:165)
        at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:77)
        at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
        at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:113)
        at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:83)
        at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:72)
        at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:341)
        at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:397)
        at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
        at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:1272)
        at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1801)
        at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
        at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:946)
        at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:480)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:57)
        at java.base/java.lang.Thread.run(Thread.java:840)
Caused by: java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException: Instance of 'proyecto.lp.iii.api.entity.Usuarios' references an unsaved transient instance of 'proyecto.lp.iii.api.entity.Tenants' (persist the transient instance) [proyecto.lp.iii.api.entity.Usuarios.id_tenants -> proyecto.lp.iii.api.entity.Tenants]
        at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:143)
        at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:168)
        at org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:174)
        at org.hibernate.internal.SessionImpl.fireMerge(SessionImpl.java:812)
        at org.hibernate.internal.SessionImpl.merge(SessionImpl.java:785)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:569)
        at org.springframework.orm.jpa.ExtendedEntityManagerCreator$ExtendedEntityManagerInvocationHandler.invoke(ExtendedEntityManagerCreator.java:360)
        at jdk.proxy2/jdk.proxy2.$Proxy150.merge(Unknown Source)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:569)
        at org.springframework.orm.jpa.SharedEntityManagerCreator$SharedEntityHandlerInvocationHandler.invokeMethod(SharedEntityManagerCreator.java:239)
        at org.springframework.orm.jpa.SharedEntityManagerCreator$SharedEntityManagerInvocationHandler.invoke(SharedEntityManagerCreator.java:383)
        at jdk.proxy2/jdk.proxy2.$Proxy150.merge(Unknown Source)
        at proyecto.lp.iii.api.repository.CustomRepositoryImpl.save(CustomRepositoryImpl.java:22)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:569)
        at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:359)
        at org.springframework.data.repository.core.support.RepositoryMethodInvoker$RepositoryFragmentMethodInvoker.lambda$new$0(RepositoryMethodInvoker.java:278)
        at org.springframework.data.repository.core.support.RepositoryMethodInvoker.doInvoke(RepositoryMethodInvoker.java:169)
        at org.springframework.data.repository.core.support.RepositoryMethodInvoker.invoke(RepositoryMethodInvoker.java:158)
        at org.springframework.data.repository.core.support.RepositoryComposition$RepositoryFragments.invoke(RepositoryComposition.java:545)
        at org.springframework.data.repository.core.support.RepositoryComposition.invoke(RepositoryComposition.java:290)
        at org.springframework.data.repository.core.support.RepositoryFactorySupport$ImplementationMethodExecutionInterceptor.invoke(RepositoryFactorySupport.java:690)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.doInvoke(QueryExecutorMethodInterceptor.java:171)
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.invoke(QueryExecutorMethodInterceptor.java:146)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
        at org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:69)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
        at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:133)
        at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:371)
        at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:130)
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)
        at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:135)
        ... 162 more
Caused by: org.hibernate.TransientPropertyValueException: Instance of 'proyecto.lp.iii.api.entity.Usuarios' references an unsaved transient instance of 'proyecto.lp.iii.api.entity.Tenants' (persist the transient instance) [proyecto.lp.iii.api.entity.Usuarios.id_tenants -> proyecto.lp.iii.api.entity.Tenants]
        at org.hibernate.action.internal.UnresolvedEntityInsertActions.checkNoUnresolvedActionsAfterOperation(UnresolvedEntityInsertActions.java:112)
        at org.hibernate.engine.spi.ActionQueue.checkNoUnresolvedActionsAfterOperation(ActionQueue.java:455)
        at org.hibernate.internal.SessionImpl.checkNoUnresolvedActionsAfterOperation(SessionImpl.java:582)
        at org.hibernate.internal.SessionImpl.fireMerge(SessionImpl.java:802)
        ... 199 more