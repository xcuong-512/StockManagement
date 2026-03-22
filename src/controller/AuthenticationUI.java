package controller;

import Model.User;
import service.AuthenticationService;
import java.util.Scanner;

public class AuthenticationUI {
    private AuthenticationService authService;
    private Scanner scanner;

    public AuthenticationUI(AuthenticationService authService) {
        this.authService = authService;
        scanner = new Scanner(System.in);
    }

    public User login(){
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        return authService.login(username, password);
    }

    public void register(){
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        authService.register(username, password);
        System.out.println("Registration successful");
    }
}
