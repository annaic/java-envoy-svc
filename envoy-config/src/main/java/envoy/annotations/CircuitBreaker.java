package envoy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface CircuitBreaker {
    boolean active() default false;
    int maxconnections() default Integer.MAX_VALUE;
    int queuedepth() default Integer.MAX_VALUE;
}
