package main.java.lt.jgreibus.model;

public class Component {
    public String name;
    public String id;
    public String identifier;
    public String documentation;
    public String version;

    public Component(String name, String id, String identifier, String documentation, String version) {
        this.name = name;
        this.id = id;
        this.identifier = identifier;
        this.documentation = documentation;
        this.version = version;
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

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
