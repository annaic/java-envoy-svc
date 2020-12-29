  - name: ${clusterVO.name}
  <#assign iscb = clusterVO.iscb()>
  <#if iscb>
    circuit_breakers:
      thresholds:
        max_connections: ${clusterVO.circuitBreaker.maxConnections}
        max_pending_requests: ${clusterVO.circuitBreaker.maxPendingRequests}
  </#if>
    connect_timeout: ${clusterVO.timeout_s}s
    type: ${clusterVO.serviceDiscovery}
    dns_lookup_family: V4_ONLY
    lb_policy: ${clusterVO.lbPolicy}
    load_assignment:
      cluster_name: ${clusterVO.name}
      endpoints:
        - lb_endpoints:
            - endpoint:
                address:
                  socket_address:
                    address: ${clusterVO.address.host}
                    port_value: ${clusterVO.address.port}
  <#assign isclienttls = clusterVO.isClientTlsEnabled()>
  <#if isclienttls>
    transport_socket:
      name: envoy.transport_socket.tls
      typed_config:
        '@type': type.googleapis.com/envoy.api.v2.auth.UpstreamTlsContext
        allow_renegotiation: ${clusterVO.clientTls.allow_renegotiation?string('true','false')}
        common_tls_context:
          tls_certificate_sds_secret_configs:
            name: client-certificate
            sds_config:
              path: ${clusterVO.clientTls.client_cert_path}
          tls_params:
            tls_minimum_protocol_version: ${clusterVO.clientTls.min_tls}
          validation_context_sds_secret_config:
            name: client-validation
            sds_config:
              path: ${clusterVO.clientTls.client_validation_path}
        sni: ${clusterVO.clientTls.sni}
  </#if>
