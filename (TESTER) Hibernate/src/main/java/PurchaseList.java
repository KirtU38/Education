import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "PurchaseList")
public class PurchaseList implements Serializable {

    @EmbeddedId
    private PurchaseListCK purchaseListCK;

    private  int price;

    @Column(name = "subscription_date")
    private Timestamp subscriptionDate;

    public PurchaseList() {}

    public PurchaseList(PurchaseListCK purchaseListCK) {
        this.purchaseListCK = purchaseListCK;
    }

    public PurchaseListCK getPurchaseListCK() {
        return purchaseListCK;
    }

    public void setPurchaseListCK(PurchaseListCK purchaseListCK) {
        this.purchaseListCK = purchaseListCK;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Timestamp subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
