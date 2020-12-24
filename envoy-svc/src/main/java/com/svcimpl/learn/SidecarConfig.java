package com.svcimpl.learn;

import envoy.annotations.*;
import envoy.config.*;

public class SidecarConfig {
    @Address(host = "0.0.0.0", port = 9901)
    IncomingAdminTraffic admin;

    /* The address for the Listener */
    @Address(host = "0.0.0.0", port = 9211)
    /* This listener endpoint is configured with TLS */
    @ServerTls
    /* This is a Http connection so our network filter is HttpConnectionManager */
    @HttpConnection(
            @VirtualHost(
                    name = "local-service",
                    domains = {"*"},
                    routes = @Route(cluster_name = "localServiceConnection")))
    /* This is the main service entry point */
    IncomingTraffic serviceEntry;

    /* The localServiceConnection that the proxy sends the message to */
    @Cluster(address = @Address(host = "127.0.0.1", port = 8080),
            discovery = ServiceDiscovery.STATIC)
    Connection localServiceConnection;

    @Address(host = "127.0.0.1", port = 12345)
    @HttpConnection(
            @VirtualHost(
                    name = "canonical-service",
                    domains = {"canonical-service", "canonical-service.k8s.cnqr.tech"},
                    routes = @Route(cluster_name = "canonicalServiceConnection")))
    OutgoingTraffic canonicalServiceTraffic;

    @Cluster(address = @Address(host = "canonical-service.k8s.cnqr.tech", port = 80),
            tls = UpstreamTLSType.CLIENT, timeout_s = 2.5)
    @ClientTls(sni = "canonical-service.k8s.cnqr.tech")
    Connection canonicalServiceConnection;


    @Cluster(address = @Address(host = "mysql.host", port = 3306),
            circuitbreaker = @CircuitBreaker(active = true, maxconnections = 5, queuedepth = 4))
    Connection mysql;
}
