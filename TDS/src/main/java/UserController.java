import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(authenticatedUser);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam double amount, @RequestParam boolean filedTax, @RequestParam String username, @RequestParam String password) {
        User user = userService.authenticate(username, password);
        double amountToWithdraw = amount;

        if (user.getAmountWithdrawn() + amount <= 2000000) {
            return ResponseEntity.ok("Amount to withdraw: " + amountToWithdraw);
        } else if (user.getAmountWithdrawn() + amount <= 10000000) {
            if (filedTax) {
                amountToWithdraw -= amount * 0.02;
            }
            return ResponseEntity.ok("Amount to withdraw after tax: " + amountToWithdraw);
        } else {
            if (filedTax) {
                amountToWithdraw -= amount * 0.05;
            } else {
                amountToWithdraw -= amount * 0.02;
            }
            return ResponseEntity.ok("Amount to withdraw after tax: " + amountToWithdraw);
        }
    }
}
