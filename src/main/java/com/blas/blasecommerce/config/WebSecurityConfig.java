package com.blas.blasecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.blas.blasecommerce.authentication.AuthenticationService;

@Configuration
//@EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// CĂ¡c User trong Database
		auth.userDetailsService(authenticationService);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests()
				.antMatchers("/product", "/orderList", "/order", "/accountInfo", "/cart", "/checkout", "/desItem",
						"/incItem", "/shoppingCartRemoveProduct", "/shipping", "/shipping-to")//
				.access("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')");

		http.authorizeRequests().antMatchers("/editProduct").access("hasRole('ROLE_ADMIN')");

		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		http.authorizeRequests().and().formLogin()//

				// Submit URL cá»§a trang login
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login")//
				.defaultSuccessUrl("/")//
				.failureUrl("/login?error=true")//
				.usernameParameter("username")//
				.passwordParameter("password")

				// Cáº¥u hĂ¬nh cho Logout Page.
				// (Sau khi logout, chuyá»ƒn tá»›i trang home)
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

	}
}
