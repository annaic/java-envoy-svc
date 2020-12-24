package envoy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface Cluster {
    Address address();
    UpstreamTLSType tls() default UpstreamTLSType.NONE;
    ServiceDiscovery discovery() default ServiceDiscovery.STRICT_DNS;
    LBPolicy value() default LBPolicy.ROUND_ROBIN;
    double timeout_s() default 5;
    CircuitBreaker circuitbreaker() default @CircuitBreaker ;
}
