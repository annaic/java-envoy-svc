package envoy.gen.freemarker;

public class RouteVO {
    private String prefixMatch;
    private String prefixRewrite;
    private String headerNameToMatch;
    private String headerValueToMatch;
    private String clusterName;

    public String getPrefixMatch() {
        return prefixMatch;
    }

    public void setPrefixMatch(String prefixMatch) {
        this.prefixMatch = prefixMatch;
    }

    public String getPrefixRewrite() {
        return prefixRewrite;
    }

    public void setPrefixRewrite(String prefixRewrite) {
        this.prefixRewrite = prefixRewrite;
    }

    public String getHeaderNameToMatch() {
        return headerNameToMatch;
    }

    public void setHeaderNameToMatch(String headerNameToMatch) {
        this.headerNameToMatch = headerNameToMatch;
    }

    public String getHeaderValueToMatch() {
        return headerValueToMatch;
    }

    public void setHeaderValueToMatch(String headerValueToMatch) {
        this.headerValueToMatch = headerValueToMatch;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
}
