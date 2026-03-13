import Model.User;
import controller.AuthenticationUI;
import controller.StockController;
import repository.JsonStockRepository;
import repository.JsonUserRepository;
import repository.StockRepository;
import repository.UserRepository;
import service.AuthenticationService;
import service.StockService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    UserRepository userRepo = new JsonUserRepository();
    StockRepository stockRepo = new JsonStockRepository();

    AuthenticationService authService = new AuthenticationService(userRepo);
    StockService stockService = new StockService(stockRepo);

    AuthenticationUI authUI = new AuthenticationUI(authService);
    StockController stockController = new StockController(stockService);

    Scanner scanner = new Scanner(System.in);

    while(true){
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký");
        System.out.println("0. Thoát ");

        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            User user = authUI.login();
            if (user != null) {
                System.out.println("Đăng nhập thành công");
                stockController.start();
            }
            else {
                System.out.println("Đăng nhập thất bại");
            }
        }
        else if (choice == 2) {
            authUI.register();
        }
        else {
            return;
        }
    }
}
