package envoy.gen.freemarker;

public class VHostVO {
    private String name;
    private String [] domains = {};
    private RouteVO[] routes = {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getDomains() {
        return domains;
    }

    public void setDomains(String[] domains) {
        this.domains = domains;
    }

    public RouteVO[] getRoutes() {
        return routes;
    }

    public void setRoutes(RouteVO[] routes) {
        this.routes = routes;
    }
}
