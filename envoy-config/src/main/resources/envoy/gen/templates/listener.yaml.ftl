  - name: ${listener.name}
    address:
      socket_address:
        address: ${listener.address.host}
        port_value: ${listener.address.port}
  <#assign typeName = listener.typeName>
  <#if typeName=='envoy.config.OutgoingTraffic'>
    traffic_direction: OUTBOUND
  </#if>
  <#assign lflen = listener.listenerFilters?size>
  <#if lflen gt 0>
    listener_filters:
    <#list listener.listenerFilters as lf>
     - name: ${lf.name}
       typed_config: ${lf.typed_config}
    </#list>
  </#if>
    filter_chains:
    <#assign tpflen = listener.tcpProxyFilters?size>
  <#if tpflen gt 0>
    <#list listener.tcpProxyFilters as tpf>
    - filter_chain_match:
        destination_port: ${tpf.destination_port}
      filters:
      - name: ${tpf.name}
        typed_config:
          "@type": ${tpf.typed_config}
          cluster: ${tpf.cluster_name}
          stat_prefix: ${tpf.stat_prefix}
    </#list>
  </#if>
    - filters:
      - name: envoy.filters.network.http_connection_manager
        typed_config:
          "@type": type.googleapis.com/envoy.config.filter.network.http_connection_manager.v2.HttpConnectionManager
          stat_prefix: ingress_http
          route_config:
            name: ${listener.name}Route
            virtual_hosts:
              - name: ${listener.name}VHost
                domains: ["*"]
                routes:
                  - match: {prefix: "/"}
                    route:
                      auto_host_rewrite: true
                      cluster: canonical_client
          http_filters:
            - name: envoy.filters.http.router
              typed_config: {}