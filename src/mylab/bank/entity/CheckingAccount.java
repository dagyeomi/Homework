package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

/** 출금 한도가 있는 계좌 */
public class CheckingAccount extends Account {

    private double withdrawalLimit; // 1회 출금 한도

    public CheckingAccount(String accountNumber, String owner, double initDeposit, double withdrawalLimit) {
        super(accountNumber, owner, initDeposit);
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() { return withdrawalLimit; }

    public void setWithdrawalLimit(double limit) {
        if (limit <= 0) throw new IllegalArgumentException("출금 한도는 0보다 커야 합니다.");
        this.withdrawalLimit = limit;
    }

    /** 한도 체크 + 잔액 체크 */
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > withdrawalLimit) {
            throw new WithdrawalLimitExceededException("출금 한도 초과: 한도 " + withdrawalLimit);
        }
        super.withdraw(amount);
    }


    @Override
    public String toString() {
        return super.toString() + String.format(" / 한도: %.2f(체킹)", withdrawalLimit);
    }
}
