package models;

import business.Business;

public class Course {
    private String name;
    private String description;
    private int length;
    private CourseType courseType;
    private float price;

    public Course(String _name, String _desc, int _length, CourseType _type, float _price) {
        this.setName(_name);
        this.setDescription(_desc);
        this.setLength(_length);
        this.setType(_type);
        this.setPrice(_price);

        new Business().AddCourse(this);
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
        String type = "";
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

        return type;
    }
    public void setType(CourseType _type) {
        this.courseType = _type;
    }
    //endregion

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
        ONSITE,
        REMOTE,
        MIXED;
    }
}
