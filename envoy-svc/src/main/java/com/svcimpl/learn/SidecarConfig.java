package com.svcimpl.learn;

import envoy.annotations.*;
import envoy.annotations.filter.listener.LFTypeNames;
import envoy.annotations.filter.listener.ListenerFilter;
import envoy.annotations.filter.network.HttpManager;
import envoy.annotations.filter.network.NetworkFilters;
import envoy.config.*;

public class SidecarConfig {

    @AdminListener(
            address = @Address(host = "0.0.0.0", port = 9901))
    IncomingAdminTraffic admin;


    @Listener(
            address = @Address(host = "0.0.0.0", port = 9211),
            listener_filters = {
                    @ListenerFilter(LFTypeNames.ORIG_DST),
                    @ListenerFilter(LFTypeNames.TLS_INSPECT)},
            netfilter = @NetworkFilters(
                    httpmanager = @HttpManager(
                            @VirtualHost(
                                    name = "local-service",
                                    domains = {"*"},
                                    routes = @Route(cluster_name = "localServiceConnection"))),
                    tls = @ServerTls(apply = true))
    )
    IncomingTraffic serviceEntry;


    /* The localServiceConnection that the proxy sends the message to */
    @Cluster(address = @Address(host = "127.0.0.1", port = 8080),
            discovery = ServiceDiscovery.STATIC)
    Connection localServiceConnection;

    @Listener( address = @Address(host = "127.0.0.1", port = 12345),
    netfilter = @NetworkFilters(
            httpmanager = @HttpManager(
                    @VirtualHost(
                            name = "canonical-service",
                            domains = {"canonical-service", "canonical-service.k8s.cnqr.tech"},
                            routes = @Route(cluster_name = "canonicalServiceConnection")))))
    OutgoingTraffic canonicalServiceTraffic;

    @Cluster(
            address = @Address(host = "canonical-service.k8s.cnqr.tech", port = 80),
            tls = @ClientTls(apply = true, sni = "canonical-service.k8s.cnqr.tech"),
            timeout_s = 2.5)
    Connection canonicalServiceConnection;


    @Cluster(
            address = @Address(host = "mysql.host", port = 3306),
            circuitbreaker = @CircuitBreaker(active = true, maxconnections = 5, queuedepth = 4))
    Connection mysql;

//    @Cluster(address = @Address(host = "127.0.0.1", port = 8080))
//    IncomingTraffic test;
}
