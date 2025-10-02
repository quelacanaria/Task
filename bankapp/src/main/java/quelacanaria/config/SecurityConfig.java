package main.java.quelacanaria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.example.bankapp.service.AccountService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	AccountService accountService;

	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		http
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("register","/images/**","/js/**").permitAll()
						.anyRequest().authenticated()
				)
				.formLogin(form->form
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.successHandler(customSuccessHandler)
						.permitAll()
				)
				.logout(logout->logout
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout")
						.permitAll()
				)
				.headers(header -> header
						.frameOptions(frameOptions -> frameOptions.sameOrigin())
				);
		return http.build();
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
	}


}
