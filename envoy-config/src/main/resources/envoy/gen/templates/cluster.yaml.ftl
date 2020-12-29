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
