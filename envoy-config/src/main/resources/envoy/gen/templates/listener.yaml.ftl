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
  <#assign http_active = listener.httpManager.active>
  <#if http_active>
    - filters:
      - name: ${listener.httpManager.name}
        typed_config:
          "@type": ${listener.httpManager.typed_config}
          stat_prefix: ${listener.httpManager.stat_prefix}
          route_config:
            name: ${listener.name}Route
            virtual_hosts:
    <#list listener.httpManager.vHosts as vHost>
              - name: ${vHost.name}
                domains:
      <#list vHost.domains as domain>
                - "${domain}"
      </#list>
                routes:
      <#list vHost.routes as route>
                  - match:
                      prefix: ${route.prefixMatch}
                    route:
                      auto_host_rewrite: true
                      cluster: ${route.clusterName}
      </#list>
    </#list>
          http_filters:
            - name: envoy.filters.http.router
              typed_config: {}
    <#assign server_tls_active = listener.httpManager.serverTls.active>
    <#if server_tls_active>
    transport_socket:
      name: ${listener.httpManager.serverTls.name}
      typed_config:
        "@type": ${listener.httpManager.serverTls.typed_config}
        common_tls_context:
          tls_certificate_sds_secret_configs:
            name: server-certificate
            sds_config:
              path: ${listener.httpManager.serverTls.cert_path}
            tls_params:
              tls_minimum_protocol_version: ${listener.httpManager.serverTls.min_tls}
            validation_context_sds_secret_config:
              name: server-validation
              sds_config:
                path: ${listener.httpManager.serverTls.server_validation_path}
    </#if>
  </#if>