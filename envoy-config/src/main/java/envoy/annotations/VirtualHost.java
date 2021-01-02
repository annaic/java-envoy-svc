package envoy.annotations;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
@Repeatable(VirtualHosts.class)
public @interface VirtualHost {
    String name();
    String [] domains() default {};
    Route [] routes() default {};
}

@Target({ElementType.FIELD})
@interface VirtualHosts{
    VirtualHost[] value();
}
