package envoy.annotations.filter.network;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface NetworkFilters {
    HttpManager httpmanager() default @HttpManager();
    TCPProxy[] tcp_filters() default {};
}
