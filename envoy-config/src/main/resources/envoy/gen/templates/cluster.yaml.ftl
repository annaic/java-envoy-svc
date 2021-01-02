  - name: ${cluster.name}
  <#assign iscb = cluster.iscb()>
  <#if iscb>
    circuit_breakers:
      thresholds:
        max_connections: ${cluster.circuitBreaker.maxConnections}
        max_pending_requests: ${cluster.circuitBreaker.maxPendingRequests}
  </#if>
    connect_timeout: ${cluster.timeout_s}s
    type: ${cluster.serviceDiscovery}
    dns_lookup_family: V4_ONLY
    lb_policy: ${cluster.lbPolicy}
    load_assignment:
      cluster_name: ${cluster.name}
      endpoints:
        - lb_endpoints:
            - endpoint:
                address:
                  socket_address:
                    address: ${cluster.address.host}
                    port_value: ${cluster.address.port}
  <#assign isclienttls = cluster.isClientTlsEnabled()>
  <#if isclienttls>
    transport_socket:
      name: envoy.transport_socket.tls
      typed_config:
        '@type': type.googleapis.com/envoy.api.v2.auth.UpstreamTlsContext
        allow_renegotiation: ${cluster.clientTls.allow_renegotiation?string('true','false')}
        common_tls_context:
          tls_certificate_sds_secret_configs:
            name: client-certificate
            sds_config:
              path: ${cluster.clientTls.client_cert_path}
          tls_params:
            tls_minimum_protocol_version: ${cluster.clientTls.min_tls}
          validation_context_sds_secret_config:
            name: client-validation
            sds_config:
              path: ${cluster.clientTls.client_validation_path}
        sni: ${cluster.clientTls.sni}
  </#if>
