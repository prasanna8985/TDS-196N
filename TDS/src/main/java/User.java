import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private double amountWithdrawn;
    private boolean filedTaxReturnsForThreeYears;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAmountWithdrawn() {
        return amountWithdrawn;
    }

    public void setAmountWithdrawn(double amountWithdrawn) {
        this.amountWithdrawn = amountWithdrawn;
    }

    public boolean isFiledTaxReturnsForThreeYears() {
        return filedTaxReturnsForThreeYears;
    }

    public void setFiledTaxReturnsForThreeYears(boolean filedTaxReturnsForThreeYears) {
        this.filedTaxReturnsForThreeYears = filedTaxReturnsForThreeYears;
    }
}
