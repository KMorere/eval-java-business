package models;

import business.Business;

public class Course {
    private int id;
    private String name;
    private String description;
    private int length;
    private CourseType courseType;
    private float price;
    private int id_client;

    public Course(String _name, String _desc, int _length, CourseType _type, float _price) {
        this.setName(_name);
        this.setDescription(_desc);
        this.setLength(_length);
        this.setType(_type);
        this.setPrice(_price);

        Business.getInstance().addCourse(this);
    }

    public Course(int _id, String _name, String _desc, int _length, CourseType _type, float _price, int _id_client) {
        this.id = _id;
        this.setName(_name);
        this.setDescription(_desc);
        this.setLength(_length);
        this.setType(_type);
        this.setPrice(_price);
        this.setClient(_id_client);

        Business.getInstance().addCourse(this);
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

    public CourseType getType() {
        return this.courseType;
    }
    public void setType(CourseType _type) {
        this.courseType = _type;
    }

    public int getClient() {
        return this.id_client;
    }
    public void setClient(int _client) { this.id_client = _client; }
    //endregion

    /**
     * ToString formatted
     * @param size Size of the alignment.
     */
    public String toStringF(int size) {
        String desc = this.description;
        if (desc.length() > 20)
            desc = desc.substring(0, 17)+"...";
        return "%-"+size+"s | "+"%-5s | "+desc;
    }

    @Override
    public String toString() {
        return "- "+name+" | "+description+" | "+length+" days | "+this.getType().getName()+" | "+price+" €";
    }

    /**
     * Enum used to differentiate a course's type.
     */
    public enum CourseType {
        ONSITE("On-site"),
        REMOTE("Remote"),
        MIXED("Mixed");

        public final String label;
        CourseType(String _label) {
            this.label = _label;
        }
        public String getName() {
            return this.label;
        }

        public static CourseType getValue(String _label) {
            for (CourseType c : values()) {
                if (java.util.Objects.equals(c.label, _label))
                    return c;
            }
            return null;
        }
    }
}
