package envoy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface ServerTls {
    String cert_path() default "/etc/envoy/xds/sds-server-certificate.yaml";
    String server_validation_path() default "/etc/envoy/xds/sds-server-validation.yaml";
    TLS min_tls() default TLS.v12;
}
