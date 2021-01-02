package envoy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface ServerTls {
    boolean apply() default false;
    String name() default "envoy.transport_sockets.tls";
    String typed_config() default "type.googleapis.com/envoy.api.v2.auth.DownstreamTlsContext";
    String cert_path() default "/etc/envoy/xds/sds-server-certificate.yaml";
    String server_validation_path() default "/etc/envoy/xds/sds-server-validation.yaml";
    TLS min_tls() default TLS.TLSv1_2;
}
