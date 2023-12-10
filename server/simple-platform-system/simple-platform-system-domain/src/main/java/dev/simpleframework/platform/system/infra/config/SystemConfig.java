package dev.simpleframework.platform.system.infra.config;

import dev.simpleframework.crud.spring.ModelScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "dev.simpleframework.platform.system.infra.data.mapper")
@ModelScan(basePackages = "dev.simpleframework.platform.system.infra.data")
public class SystemConfig {

}
