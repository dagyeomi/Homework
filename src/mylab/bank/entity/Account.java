package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public class Account {

    private final String accountNumber;
    private final String ownerName;
    protected double balance; // 하위 클래스에서 사용

    public Account(String accountNumber, String ownerName, double initDeposit) {
        if (initDeposit < 0) throw new IllegalArgumentException("초기 입금은 음수일 수 없습니다.");
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initDeposit;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("입금액은 0보다 커야 합니다.");
        balance += amount;
    }

    /** 기본 출금 규칙: 잔액 부족 시 예외 */
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) throw new IllegalArgumentException("출금액은 0보다 커야 합니다.");
        if (balance < amount) {
            throw new InsufficientBalanceException("잔액 부족: 현재 잔액 " + balance);
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s / 잔액: %.2f", accountNumber, ownerName, balance);
    }
}
