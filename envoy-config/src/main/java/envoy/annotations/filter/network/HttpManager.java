package envoy.annotations.filter.network;

import envoy.annotations.ServerTls;
import envoy.annotations.VirtualHost;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface HttpManager {
    boolean apply() default false;
    String name() default "envoy.filters.network.http_connection_manager";
    String typed_config() default
            "type.googleapis.com/envoy.config.filter.network.http_connection_manager.v2.HttpConnectionManager";
    VirtualHost[] value() default {};
    String stat_prefix() default "ingress_http";
    ServerTls tls() default @ServerTls;
}

