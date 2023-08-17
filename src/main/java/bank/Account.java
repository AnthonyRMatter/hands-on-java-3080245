package bank;

import bank.exceptions.AmountException;

public class Account {
  
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance){
    setId(id);
    setType(type);
    setBalanace(balance);
  }


  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalanace() {
    return this.balance;
  }

  public void setBalanace(double balanace) {
    this.balance = balanace;
  }
  
  public void deposit(double amount) throws AmountException{
    
    if(amount < 1){
      throw new AmountException("The minimum deposit is 1.00");
    }
    else{
      double newBalance = balance + amount;
      setBalanace(newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    }

  }

  public void withdraw(double amount) throws AmountException{

    if(amount < 0){
      throw new AmountException("The withdrawl amount must be greater than 0.");
    }
  }

}
