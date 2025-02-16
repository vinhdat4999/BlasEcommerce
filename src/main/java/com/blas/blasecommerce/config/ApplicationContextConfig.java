package com.blas.blasecommerce.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.blas.blasecommerce.dao.AuthenticationDAO;
import com.blas.blasecommerce.dao.CartDAO;
import com.blas.blasecommerce.dao.CategoryDAO;
import com.blas.blasecommerce.dao.OrderDAO;
import com.blas.blasecommerce.dao.ProductDAO;
import com.blas.blasecommerce.dao.ProductImageDAO;
import com.blas.blasecommerce.dao.ReceiverInfoDAO;
import com.blas.blasecommerce.dao.UserDAO;
import com.blas.blasecommerce.dao.impl.AuthenticationDAOImpl;
import com.blas.blasecommerce.dao.impl.CartDAOImpl;
import com.blas.blasecommerce.dao.impl.CategoryDAOImpl;
import com.blas.blasecommerce.dao.impl.OrderDAOImpl;
import com.blas.blasecommerce.dao.impl.ProductDAOImpl;
import com.blas.blasecommerce.dao.impl.ProductImageDAOImpl;
import com.blas.blasecommerce.dao.impl.ReceiverInfoDAOImpl;
import com.blas.blasecommerce.dao.impl.UserDAOImpl;

@Configuration
@ComponentScan("com.blas.blasecommerce.*")
@EnableTransactionManagement
// Load to Environment.
@PropertySource("classpath:ds-hibernate-cfg.properties")
public class ApplicationContextConfig {
 
   // Lưu trữ các giá thuộc tính load bởi @PropertySource.
   @Autowired
   private Environment env;
 
   @Bean
   public ResourceBundleMessageSource messageSource() {
       ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
       // Load property in message/validator.properties
       rb.setBasenames(new String[] { "messages/validator" });
       return rb;
   }
 
   @Bean(name = "viewResolver")
   public InternalResourceViewResolver getViewResolver() {
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
       viewResolver.setPrefix("/WEB-INF/pages/");
       viewResolver.setSuffix(".jsp");
       return viewResolver;
   }
    
  
   // Cấu hình để Upload.
   @Bean(name = "multipartResolver")
   public CommonsMultipartResolver multipartResolver() {
       CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        
       // Set Max Size...
       // commonsMultipartResolver.setMaxUploadSize(...);
        
       return commonsMultipartResolver;
   }
 
   @Bean(name = "dataSource")
   public DataSource getDataSource() {
       DriverManagerDataSource dataSource = new DriverManagerDataSource();
       dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
       dataSource.setUrl(env.getProperty("ds.url"));
       dataSource.setUsername(env.getProperty("ds.username"));
       dataSource.setPassword(env.getProperty("ds.password"));
       return dataSource;
   }
 
   @Autowired
   @Bean(name = "sessionFactory")
   public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
       Properties properties = new Properties();
 
  
       // Xem: ds-hibernate-cfg.properties
       properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
       properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
       properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
        
 
       LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
  
       // Package chứa các entity class.
       factoryBean.setPackagesToScan(new String[] { "com.blas.blasecommerce.entity" });
       factoryBean.setDataSource(dataSource);
       factoryBean.setHibernateProperties(properties);
       factoryBean.afterPropertiesSet();
       //
       SessionFactory sf = factoryBean.getObject();
       return sf;
   }
 
   @Autowired
   @Bean(name = "transactionManager")
   public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
       HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
 
       return transactionManager;
   }
 
   @Bean(name = "productDAO")
   public ProductDAO getProductDAO() {
       return new ProductDAOImpl();
   }
   
   @Bean(name = "userDAO")
   public UserDAO getUserDAO() {
       return new UserDAOImpl();
   }
   	
   @Bean(name = "cartDAO")
   public CartDAO getCartDAO() {
       return new CartDAOImpl();
   }
 
   @Bean(name = "orderDAO")
   public OrderDAO getOrderDAO() {
       return new OrderDAOImpl();
   }
   
   @Bean(name = "receiverInfoDAO")
   public ReceiverInfoDAO getReceiverInfoDAO() {
	   return new ReceiverInfoDAOImpl();
   }
   
   @Bean(name = "authenticationDAO")
   public AuthenticationDAO getAuthenticationDAO() {
	   return new AuthenticationDAOImpl();
   }
   
   @Bean(name = "productImageDAO")
   public ProductImageDAO getProductImageDAO() {
	   return new ProductImageDAOImpl();
   }
   
   @Bean(name = "categoryDAO")
   public CategoryDAO getCategoryDAO() {
	   return new CategoryDAOImpl();
   }
 
}