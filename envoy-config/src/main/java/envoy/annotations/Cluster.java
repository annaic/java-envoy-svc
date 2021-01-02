package envoy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface Cluster {
    Address address();
    ClientTls tls() default @ClientTls;
    ServiceDiscovery discovery() default ServiceDiscovery.STRICT_DNS;
    LBPolicy lbtype() default LBPolicy.ROUND_ROBIN;
    double timeout_s() default 5;
    CircuitBreaker circuitbreaker() default @CircuitBreaker ;
}
