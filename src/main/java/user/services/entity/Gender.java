package user.services.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "gender")
@Table(name = "gender")
public class Gender {

    @Id
    private Integer id;

    private String type;

    @OneToOne(mappedBy = "gender")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
