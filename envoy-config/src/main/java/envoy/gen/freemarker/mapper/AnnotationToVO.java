package envoy.gen.freemarker.mapper;

import envoy.annotations.CircuitBreaker;
import envoy.annotations.ClientTls;
import envoy.annotations.Cluster;
import envoy.annotations.Listener;
import envoy.annotations.filter.listener.ListenerFilter;
import envoy.gen.freemarker.*;
import org.checkerframework.checker.units.qual.C;

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

        return listenerVO;
    }
}
