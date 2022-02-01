package be.vdab.starwars.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email as username, paswoord as password, true as enabled from gebruikers where email = ?")
                .authoritiesByUsernameQuery("select email as username, id as authority from gebruikers where email = ?");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .mvcMatchers("/images/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin(login -> login.loginPage("/login"));
        http.logout(logout -> logout.logoutSuccessUrl("/"));
        http.authorizeRequests(requests -> requests
                .mvcMatchers("/", "/login", "/gemiddeldeScores")
                .permitAll()
                .mvcMatchers("/filmZoeken/**").authenticated());
    }
}
