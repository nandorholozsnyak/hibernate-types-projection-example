# hibernate-types-projection-example
Repository for providing a problem based on the following article:  
https://vladmihalcea.com/dto-projection-jpa-query/ (Bootstrapping JPA declaratively section)

Article says that for Spring boot users who bootstraps JPA declaratively if they would like to use the DTO projections with only the class name in their JPQL queries, their job is that only to do one of the following stuff:

**Copied from the article:**  
"Since Hibernate ORM 5.4.9, you can pass the hibernate.integrator_provider declaratively, either via the JPA persistence.xml configuration file:

```
<property
    name="hibernate.integrator_provider"
    value="com.vladmihalcea.hibernate.integrator.ClassImportIntegrator">
</property>
```

or, using the application.properties Spring Boot configuration file:

```
hibernate.integrator_provider=com.vladmihalcea.hibernate.integrator.ClassImportIntegrator
```
"

In my case I use application.yml but that does not change a lot on the scenario, and with the version I'm using of the hibernate-types the class moved to another package to the fully qualified name is:

```com.vladmihalcea.hibernate.type.util.ClassImportIntegrator```

My application.yml content in this scenario:
```
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/example
    username: example
    password: example
  jpa:
    properties:
      hibernate.integrator_provider: com.vladmihalcea.hibernate.type.util.ClassImportIntegrator
    hibernate:
      ddl-auto: create-drop
```

When I start the application the following exception comes up:
```
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: The IntegratorProvider class [class com.vladmihalcea.hibernate.type.util.ClassImportIntegrator] could not be instantiated!
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1796) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:595) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:517) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:323) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:321) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1108) ~[spring-context-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:868) ~[spring-context-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550) ~[spring-context-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:141) ~[spring-boot-2.2.3.RELEASE.jar:2.2.3.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:747) ~[spring-boot-2.2.3.RELEASE.jar:2.2.3.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397) ~[spring-boot-2.2.3.RELEASE.jar:2.2.3.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:315) ~[spring-boot-2.2.3.RELEASE.jar:2.2.3.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1226) ~[spring-boot-2.2.3.RELEASE.jar:2.2.3.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1215) ~[spring-boot-2.2.3.RELEASE.jar:2.2.3.RELEASE]
	at co.rodnan.example.HibernateProjectionExampleApplication.main(HibernateProjectionExampleApplication.java:10) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49) ~[spring-boot-devtools-2.2.3.RELEASE.jar:2.2.3.RELEASE]
Caused by: java.lang.IllegalArgumentException: The IntegratorProvider class [class com.vladmihalcea.hibernate.type.util.ClassImportIntegrator] could not be instantiated!
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.loadSettingInstance(EntityManagerFactoryBuilderImpl.java:1424) ~[hibernate-core-5.4.10.Final.jar:5.4.10.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.applyIntegrationProvider(EntityManagerFactoryBuilderImpl.java:441) ~[hibernate-core-5.4.10.Final.jar:5.4.10.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.buildBootstrapServiceRegistry(EntityManagerFactoryBuilderImpl.java:371) ~[hibernate-core-5.4.10.Final.jar:5.4.10.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.<init>(EntityManagerFactoryBuilderImpl.java:200) ~[hibernate-core-5.4.10.Final.jar:5.4.10.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.<init>(EntityManagerFactoryBuilderImpl.java:168) ~[hibernate-core-5.4.10.Final.jar:5.4.10.Final]
	at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:52) ~[spring-orm-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:365) ~[spring-orm-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:391) ~[spring-orm-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:378) ~[spring-orm-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:341) ~[spring-orm-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1855) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1792) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	... 21 common frames omitted
Caused by: java.lang.InstantiationException: com.vladmihalcea.hibernate.type.util.ClassImportIntegrator
	at java.base/java.lang.Class.newInstance(Class.java:571) ~[na:na]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.loadSettingInstance(EntityManagerFactoryBuilderImpl.java:1420) ~[hibernate-core-5.4.10.Final.jar:5.4.10.Final]
	... 32 common frames omitted
Caused by: java.lang.NoSuchMethodException: com.vladmihalcea.hibernate.type.util.ClassImportIntegrator.<init>()
	at java.base/java.lang.Class.getConstructor0(Class.java:3350) ~[na:na]
	at java.base/java.lang.Class.newInstance(Class.java:556) ~[na:na]
	... 33 common frames omitted
```

It says that there is no empty constructor for the ClassImportIntegrator class, there is only one which accepts a list of classes.

One workaround could be your own implementation of the Integrator interface:
```
public class ClassImportIntegrator implements Integrator {

    private final List<? extends Class> classImportList;

    public ClassImportIntegrator() {
        this.classImportList = List.of(PostDTO.class);
    }

    @Override
    public void integrate(
            Metadata metadata,
            SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {
        for (Class classImport : classImportList) {
            metadata.getImports().put(
                    classImport.getSimpleName(),
                    classImport.getName()
            );
        }
    }

    @Override
    public void disintegrate(
            SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {
    }
}
```

It provides an empty constructor which will initiate the list with fix classes (this could be reworked to do it by reflection but the bootstrap process would be a bit slower)
Then we set the ```hibernate.integrator_provider:``` property to the following: ```co.rodnan.example.ClassImportIntegrator```  
We think that we are ready to go but not unfortunately...

We are getting the following exception:
```
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is java.lang.ClassCastException: class co.rodnan.example.ClassImportIntegrator cannot be cast to class org.hibernate.jpa.boot.spi.IntegratorProvider (co.rodnan.example.ClassImportIntegrator and org.hibernate.jpa.boot.spi.IntegratorProvider are in unnamed module of loader 'app')
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1796) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:595) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:517) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:323) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:321) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1108) ~[spring-context-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:868) ~[spring-context-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:550) ~[spring-context-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:141) ~[spring-boot-2.2.3.RELEASE.jar:2.2.3.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:747) ~[spring-boot-2.2.3.RELEASE.jar:2.2.3.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397) ~[spring-boot-2.2.3.RELEASE.jar:2.2.3.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:315) ~[spring-boot-2.2.3.RELEASE.jar:2.2.3.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1226) ~[spring-boot-2.2.3.RELEASE.jar:2.2.3.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1215) ~[spring-boot-2.2.3.RELEASE.jar:2.2.3.RELEASE]
	at co.rodnan.example.HibernateProjectionExampleApplication.main(HibernateProjectionExampleApplication.java:10) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49) ~[spring-boot-devtools-2.2.3.RELEASE.jar:2.2.3.RELEASE]
Caused by: java.lang.ClassCastException: class co.rodnan.example.ClassImportIntegrator cannot be cast to class org.hibernate.jpa.boot.spi.IntegratorProvider (co.rodnan.example.ClassImportIntegrator and org.hibernate.jpa.boot.spi.IntegratorProvider are in unnamed module of loader 'app')
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.applyIntegrationProvider(EntityManagerFactoryBuilderImpl.java:441) ~[hibernate-core-5.4.10.Final.jar:5.4.10.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.buildBootstrapServiceRegistry(EntityManagerFactoryBuilderImpl.java:371) ~[hibernate-core-5.4.10.Final.jar:5.4.10.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.<init>(EntityManagerFactoryBuilderImpl.java:200) ~[hibernate-core-5.4.10.Final.jar:5.4.10.Final]
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.<init>(EntityManagerFactoryBuilderImpl.java:168) ~[hibernate-core-5.4.10.Final.jar:5.4.10.Final]
	at org.springframework.orm.jpa.vendor.SpringHibernateJpaPersistenceProvider.createContainerEntityManagerFactory(SpringHibernateJpaPersistenceProvider.java:52) ~[spring-orm-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.createNativeEntityManagerFactory(LocalContainerEntityManagerFactoryBean.java:365) ~[spring-orm-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.buildNativeEntityManagerFactory(AbstractEntityManagerFactoryBean.java:391) ~[spring-orm-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.afterPropertiesSet(AbstractEntityManagerFactoryBean.java:378) ~[spring-orm-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean.afterPropertiesSet(LocalContainerEntityManagerFactoryBean.java:341) ~[spring-orm-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1855) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1792) ~[spring-beans-5.2.3.RELEASE.jar:5.2.3.RELEASE]
	... 21 common frames omitted
```

A ClassCastException, interesting, now after knowing this we just could make an implementation of the IntegratorProvider interface like this:

```
public class ClassImportIntegratorProvider implements IntegratorProvider {
    
    @Override
    public List<Integrator> getIntegrators() {
        return List.of(new ClassImportIntegrator());
    }
}
```

And then in the application.yml we just have to change the ```hibernate.integrator_provider``` property to the following: ```co.rodnan.example.ClassImportIntegratorProvider``` and then everything would work flawlessly.

Another IntegratorProvider could be implemented to get the required DTO classes dynamically or by just putting them here.
