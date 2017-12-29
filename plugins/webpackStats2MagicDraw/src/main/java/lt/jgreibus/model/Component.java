package main.java.lt.jgreibus.model;

public class Component {
    public String name;
    public String id;
    public String identifier;

    public Component(String name, String id, String identifer) {
        this.name = name;
        this.id = id;
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
