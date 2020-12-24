package envoy.annotations;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
@Repeatable(VirtualHosts.class)
public @interface VirtualHost {
    String name();
    String [] domains() default {};
    Route [] routes() default {};
}

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@interface VirtualHosts{
    VirtualHost[] value();
}
