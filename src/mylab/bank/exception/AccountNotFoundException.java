package mylab.bank.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String accountNumber) {
        super("���¸� ã�� �� �����ϴ�: " + accountNumber);
    }
}
