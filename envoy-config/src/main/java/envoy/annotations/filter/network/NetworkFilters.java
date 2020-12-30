package envoy.annotations.filter.network;

import envoy.annotations.ServerTls;

public @interface NetworkFilters {
    HttpManager httpmanager() default @HttpManager();
    ServerTls tls() default @ServerTls;
}
