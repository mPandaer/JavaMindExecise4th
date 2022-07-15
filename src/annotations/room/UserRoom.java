package annotations.room;

@DBTable(name = "UserTable")
public class UserRoom {
    @SQLString(20) String name;
    @SQLInteger int age;
    @SQLString(value = 10,constraints = @Constraints(unique = true))
    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
