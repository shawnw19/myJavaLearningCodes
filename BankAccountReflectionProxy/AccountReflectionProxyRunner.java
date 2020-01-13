package BankAccount_Proxy;

public class AccountReflectionProxyRunner {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("Shawn");
        AccountReflectionProxy firstAccount = new AccountReflectionProxy(myAccount);

        firstAccount.callMethod("deposit", (double) 30);
        firstAccount.callMethod("withdraw", (double) 5.6);
        Object currentBalance = firstAccount.callMethod("getBalance", null);
        System.out.println("The account balance is now: " + currentBalance);
    }
}
