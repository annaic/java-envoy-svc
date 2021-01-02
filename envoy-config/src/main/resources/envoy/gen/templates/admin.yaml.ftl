node:
  id: ${r"${HOSTNAME}"}
  cluster: ${admin.service}
admin:
  access_log_path: ${admin.access_log_path}
  address:
    socket_address:
      address: ${admin.address.host}
      port_value: ${admin.address.port}
static_resources: