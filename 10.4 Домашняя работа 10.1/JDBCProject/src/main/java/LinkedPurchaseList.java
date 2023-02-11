import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {
    @EmbeddedId
    private LinkedPurchaseListKey id;

    public LinkedPurchaseListKey getId() {
        return id;
    }

    public void setId(LinkedPurchaseListKey id) {
        this.id = id;
    }
//    @Column(name = "student_id", insertable = false, updatable = false)
//    private int studentId;
//
//    @Column(name = "course_id", insertable = false, updatable = false)
//    private int courseId;
}