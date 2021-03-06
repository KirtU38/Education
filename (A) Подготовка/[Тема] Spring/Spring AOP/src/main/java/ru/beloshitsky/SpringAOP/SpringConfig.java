package ru.beloshitsky.SpringAOP;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootConfiguration
@EnableAspectJAutoProxy
public class SpringConfig {
}
// @EnableAspectJAutoProxy нужно ставить в Config классе, но и без этой аннотации вроде всё работает
