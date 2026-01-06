package models;

import business.Business;

public class Course {
    private int id;
    private String name;
    private String description;
    private int length;
    private String courseType;
    private float price;
    private int id_client;

    public Course(String _name, String _desc, int _length, String _type, float _price) {
        this.setName(_name);
        this.setDescription(_desc);
        this.setLength(_length);
        this.setType(_type);
        this.setPrice(_price);

        new Business().addCourse(this);
    }

    public Course(int _id, String _name, String _desc, int _length, String _type, float _price, int _id_client) {
        this.id = _id;
        this.setName(_name);
        this.setDescription(_desc);
        this.setLength(_length);
        this.setType(_type);
        this.setPrice(_price);
        this.setClient(_id_client);

        new Business().addCourse(this);
    }

    //region getters and setters
    public String getName() { return this.name; }
    public void setName(String _name) { this.name = _name; }

    public String getDescription() { return this.description; }
    public void setDescription(String _desc) { this.description = _desc; }

    public int getLength() { return this.length; }
    public void setLength(int _length) { this.length = _length; }

    public float getPrice() { return this.price; }
    public void setPrice(float _price) { this.price = _price; }

    /**
     * Get the course type as a String.
     */
    public String getType() {
        /*String type = "";
        switch(courseType) {
            case ONSITE:
                type = "On-site";
                break;
            case REMOTE:
                type = "Remote";
                break;
            case MIXED:
                type = "Mixed";
                break;
        }

        return type;*/
        return this.courseType;
    }
    public void setType(String _type) {
        this.courseType = _type;
    }

    public int getClient() {
        return this.id_client;
    }
    public void setClient(int _client) { this.id_client = _client; }
    //endregion

    /**
     * ToString formatted
     * @param size Size of the length.
     */
    public String toStringF(int size) {
        String desc = this.description;
        if (desc.length() > 20)
            desc = desc.substring(0, 17)+"...";
        return "%-"+size+"s | "+"%-5s | "+desc;
    }

    @Override
    public String toString() {
        return "- "+name+" | "+description+" | "+length+" days | "+this.getType()+" | "+price+" €";
    }

    /**
     * Enum used to differentiate a course's type.
     */
    public enum CourseType {
        ONSITE(0),
        REMOTE(1),
        MIXED(2);

        private final int value;
        CourseType(int _value) {
            this.value = _value;
        }

        public static CourseType getType(int _value) {
            return CourseType.values()[_value];
        }

        public int getType() {
            return this.value;
        }
    }
}
