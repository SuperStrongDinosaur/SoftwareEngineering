package config

import org.springframework.context.annotation.*
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("controller")
@Import(JdbcDaoContextConfiguration::class)
open class WebConfig : WebMvcConfigurerAdapter() {

    @Bean
    open fun configureInternalResourceViewResolver(): InternalResourceViewResolver {
        val resolver = InternalResourceViewResolver()
        resolver.setPrefix("WEB-INF/views/")
        resolver.setSuffix(".jsp")
        return resolver
    }

}

