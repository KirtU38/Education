import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseListCK linkedPurchaseListCK;

    public LinkedPurchaseList() {}

    public LinkedPurchaseList(LinkedPurchaseListCK linkedPurchaseListCK) {
        this.linkedPurchaseListCK = linkedPurchaseListCK;
    }

    public LinkedPurchaseListCK getKey() {
        return linkedPurchaseListCK;
    }

    public void setKey(LinkedPurchaseListCK linkedPurchaseListCK) {
        this.linkedPurchaseListCK = linkedPurchaseListCK;
    }
}
