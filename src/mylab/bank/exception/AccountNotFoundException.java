package mylab.bank.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String accountNumber) {
        super("계좌를 찾을 수 없습니다: " + accountNumber);
    }
}
