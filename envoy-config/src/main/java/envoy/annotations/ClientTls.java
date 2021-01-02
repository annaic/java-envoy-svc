package envoy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface ClientTls {
    boolean apply() default false;
    String sni() default "";
    TLS min_tls() default TLS.TLSv1_2;
    boolean allow_renegotiation() default true;
    String client_cert_path() default "/etc/envoy/xds/sds-client-certificate.yaml";
    String client_validation_path() default "/etc/envoy/xds/sds-client-validation.yaml";
}
