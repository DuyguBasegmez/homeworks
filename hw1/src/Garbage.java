public class Garbage {
    public String name;
    public String type;

    public Garbage(String garbageName, String garbageType) {
        this.name = garbageName;
        this.type = garbageType;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Object obj){
        return this == obj;
    }
}