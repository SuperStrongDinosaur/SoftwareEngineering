package config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.InternalResourceViewResolver

@Configuration
@EnableWebMvc
@ComponentScan("controller")
//@Import(JdbcDaoContextConfiguration::class)
open class WebConfig : WebMvcConfigurer {
    @Bean
    open fun configureInternalResourceViewResolver(): InternalResourceViewResolver {
        val resolver = InternalResourceViewResolver()
        resolver.setPrefix("WEB-INF/views/")
        resolver.setSuffix(".jsp")
        return resolver
    }
}
