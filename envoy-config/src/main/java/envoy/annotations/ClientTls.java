package envoy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface ClientTls {
    String sni() default "";
    TLS min_tls() default TLS.v12;
    boolean allow_renegotiation() default true;
    String client_cert_path() default "/etc/envoy/xds/sds-client-validation.yaml";
    String client_validation_path() default "/etc/envoy/xds/sds-client-validation.yaml";
}
