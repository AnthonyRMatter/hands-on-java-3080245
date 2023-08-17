package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {
  
  private Scanner scanner;

  public static void main(String[] args){
    System.out.println("Welcome to Globe Bank International!");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if(customer != null){
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }

  private Customer authenticateUser(){
    System.out.println("Please enter your username");
    String username = scanner.next();

    System.out.println("Please enter your password");
    String password = scanner.next();

    Customer customer = null;
    try{
      customer = Authenticator.login(username, password);
    }catch(LoginException l){
      System.out.println("There was an error: " + l.getMessage());
    }

    return customer;
  }

  private void showMenu(Customer customer, Account account){

    int selection = 0;

    while(selection != 4 && customer.isAuthenticated()){
      System.out.println("================================================");
      System.out.println("Please select one of the following options: ");
      System.out.println("1: Deposit");
      System.out.println("2: Withdrawl");
      System.out.println("3: Check Balance");
      System.out.println("4: Exit");
      System.out.println("================================================");

      selection = scanner.nextInt();
      double amount = 0;

      switch(selection){
        case 1:
          System.out.println("How much would you like to deposit?");
          amount = scanner.nextDouble();
          try{
            account.deposit(amount);
          }catch(AmountException a){
            System.out.println(a.getMessage());
            System.out.println("Please try again.");
          }
          break;

        case 2:
          System.out.println("How much would you like to withdrawl?");
          amount = scanner.nextDouble();
          account.withdraw(amount);
          break;

        case 3:
          System.out.println("Current balance: " + account.getBalanace());
          break;

        case 4:
          Authenticator.logout(customer);
          System.out.println("Thanks for banking at Globe Bank International!");

        default:
          System.out.println("Invalid option. Please try again");
          break;
      }
    }
  }
}