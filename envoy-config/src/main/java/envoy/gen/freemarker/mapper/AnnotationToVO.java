package envoy.gen.freemarker.mapper;

import envoy.annotations.*;
import envoy.annotations.filter.listener.ListenerFilter;
import envoy.annotations.filter.network.HttpManager;
import envoy.annotations.filter.network.TCPProxy;
import envoy.gen.freemarker.TCPProxyVO;
import envoy.gen.freemarker.*;

public class AnnotationToVO {

    public static ClusterVO mapCluster(Cluster cluster, String name){
        ClusterVO cvo = new ClusterVO();
        cvo.setName(name);
        cvo.setTimeout_s(cluster.timeout_s());
        cvo.setLbPolicy(cluster.lbtype().name());
        cvo.setServiceDiscovery(cluster.discovery().name());
        AddressVO addressVO = new AddressVO();
        addressVO.setHost(cluster.address().host());
        addressVO.setPort(cluster.address().port());
        cvo.setAddress(addressVO);
        CircuitBreaker circuitbreaker = cluster.circuitbreaker();
        cvo.setiscb(circuitbreaker.active());
        if(circuitbreaker.active()){
            CircuitBreakerVO circuitBreakerVO = new CircuitBreakerVO();
            circuitBreakerVO.setMaxConnections(circuitbreaker.maxconnections());
            circuitBreakerVO.setMaxPendingRequests(circuitbreaker.queuedepth());
            cvo.setCircuitBreaker(circuitBreakerVO);
        }
        ClientTls tls = cluster.tls();
        if(tls.apply()){
            cvo.setIsClientTlsEnabled(true);
            ClientTlsVO tlsVO = new ClientTlsVO();
            tlsVO.setMin_tls(tls.min_tls().name());
            tlsVO.setAllow_renegotiation(tls.allow_renegotiation());
            if("".equals(tls.sni())){
                tlsVO.setSni(cluster.address().host());
            }else{
                tlsVO.setSni(tls.sni());
            }
            tlsVO.setClient_validation_path(tls.client_validation_path());
            tlsVO.setClient_cert_path(tls.client_cert_path());
            cvo.setClientTls(tlsVO);
        }


        return cvo;
    }

    public static ListenerVO mapListener(Listener listener, String name, String trafficDirection) {
        ListenerVO listenerVO = new ListenerVO();
        listenerVO.setName(name);
        listenerVO.setTypeName(trafficDirection);
        AddressVO addressVO = new AddressVO();
        addressVO.setHost(listener.address().host());
        addressVO.setPort(listener.address().port());
        listenerVO.setAddress(addressVO);
        HttpManager httpmanager = listener.netfilter().httpmanager();
        HttpManagerVO httpManagerVO = new HttpManagerVO();
        if(httpmanager.apply()){
            httpManagerVO.setActive(true);
            httpManagerVO.setName(httpmanager.name());
            httpManagerVO.setTyped_config(httpmanager.typed_config());
            httpManagerVO.setStat_prefix(httpmanager.stat_prefix());
            ServerTlsVO serverTlsVO = new ServerTlsVO();
            serverTlsVO.setActive(httpmanager.tls().apply());
            serverTlsVO.setCert_path(httpmanager.tls().cert_path());
            serverTlsVO.setMin_tls(httpmanager.tls().min_tls().name());
            serverTlsVO.setServer_validation_path(httpmanager.tls().server_validation_path());
            serverTlsVO.setName(httpmanager.tls().name());
            serverTlsVO.setTyped_config(httpmanager.tls().typed_config());
            httpManagerVO.setServerTls(serverTlsVO);
            VirtualHost[] virtualHosts = httpmanager.value();
            if(virtualHosts.length > 0){
                VHostVO [] vHostVOS = new VHostVO[virtualHosts.length];
                for(int i = 0; i < vHostVOS.length; i++){
                    VHostVO vHostVO = new VHostVO();
                    vHostVO.setName(virtualHosts[i].name());
                    vHostVO.setDomains(virtualHosts[i].domains());
                    Route [] routes = virtualHosts[i].routes();
                    if(routes.length > 0){
                        RouteVO [] routeVOS = new RouteVO[routes.length];
                        for(int j = 0; j < routeVOS.length; j++){
                            RouteVO routeVO = new RouteVO();
                            routeVO.setPrefixMatch(routes[j].prefix_match());
                            routeVO.setPrefixRewrite(routes[j].prefix_rewrite());
                            routeVO.setHeaderNameToMatch(routes[j].header_match_name());
                            routeVO.setHeaderValueToMatch(routes[j].header_match_value());
                            routeVO.setClusterName(routes[j].cluster_name());
                            routeVOS[j] = routeVO;
                        }
                        vHostVO.setRoutes(routeVOS);
                    }
                    vHostVOS[i] = vHostVO;
                }
                httpManagerVO.setvHosts(vHostVOS);
            }
        }else{
            httpManagerVO.setActive(false);
        }
        listenerVO.setHttpManager(httpManagerVO);
        ListenerFilter[] listenerFilters = listener.listener_filters();
        if(listenerFilters.length > 0){
            ListenerFilterVO[] listenerFilterVOS = new ListenerFilterVO[listenerFilters.length];
            for(int i = 0; i < listenerFilters.length; i++){
                ListenerFilterVO vo = new ListenerFilterVO();
                vo.setName(listenerFilters[i].value().getValue());
                String type_cfg = listenerFilters[i].typed_config();
                if("".equals(type_cfg)){
                    vo.setTyped_config("{}");
                }else{
                    vo.setTyped_config(type_cfg);
                }
                listenerFilterVOS[i] = vo;
            }
            listenerVO.setListenerFilters(listenerFilterVOS);
        }

        TCPProxy[] tcpProxies = listener.netfilter().tcp_filters();
        if(tcpProxies.length > 0){
            TCPProxyVO [] tcpProxyVOS = new TCPProxyVO[tcpProxies.length];
            for(int i =0; i < tcpProxyVOS.length; i++){
                TCPProxyVO proxyVO = new TCPProxyVO();
                proxyVO.setName(tcpProxies[i].name());
                proxyVO.setCluster_name(tcpProxies[i].cluster_name());
                proxyVO.setTyped_config(tcpProxies[i].typed_config());
                proxyVO.setStat_prefix(tcpProxies[i].stat_prefix());
                proxyVO.setDestination_port(tcpProxies[i].destination_port());
                tcpProxyVOS[i] = proxyVO;
            }
            listenerVO.setTcpProxyFilters(tcpProxyVOS);
        }
        return listenerVO;
    }

    public static AdminVO mapAdmin(Admin admin, String name) {
        AdminVO adminVO = new AdminVO();
        adminVO.setService(admin.service());
        adminVO.setAccess_log_path(admin.access_log_path());
        AddressVO addressVO = new AddressVO();
        addressVO.setHost(admin.address().host());
        addressVO.setPort(admin.address().port());
        adminVO.setAddress(addressVO);
        return adminVO;
    }
}
