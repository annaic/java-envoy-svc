package envoy.gen.freemarker;

public class ListenerVO {
    private String name;
    String typeName;
    private AddressVO address;
    private ListenerFilterVO [] listenerFilters = {};

    public ListenerFilterVO [] getListenerFilters() {
        return listenerFilters;
    }

    public void setListenerFilters(ListenerFilterVO [] listenerFilters) {
        this.listenerFilters = listenerFilters;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public AddressVO getAddress() {
        return address;
    }

    public void setAddress(AddressVO address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}