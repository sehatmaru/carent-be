package xcode.biz.config

import javax.sql.DataSource
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@MapperScan("xcode.biz.domain.mapper")
class MyBatisConfig {

    @Bean
    fun sqlSessionFactory(dataSource: DataSource): SqlSessionFactory {
        val sessionFactory = SqlSessionFactoryBean()
        sessionFactory.setDataSource(dataSource)
        sessionFactory.getObject()?.configuration?.isMapUnderscoreToCamelCase = true

        return sessionFactory.`object`!!
    }
}
