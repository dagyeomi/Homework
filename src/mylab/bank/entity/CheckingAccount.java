package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

/** ��� �ѵ��� �ִ� ���� */
public class CheckingAccount extends Account {

    private double withdrawalLimit; // 1ȸ ��� �ѵ�

    public CheckingAccount(String accountNumber, String owner, double initDeposit, double withdrawalLimit) {
        super(accountNumber, owner, initDeposit);
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() { return withdrawalLimit; }

    public void setWithdrawalLimit(double limit) {
        if (limit <= 0) throw new IllegalArgumentException("��� �ѵ��� 0���� Ŀ�� �մϴ�.");
        this.withdrawalLimit = limit;
    }

    /** �ѵ� üũ + �ܾ� üũ */
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > withdrawalLimit) {
            throw new WithdrawalLimitExceededException("��� �ѵ� �ʰ�: �ѵ� " + withdrawalLimit);
        }
        super.withdraw(amount);
    }


    @Override
    public String toString() {
        return super.toString() + String.format(" / �ѵ�: %.2f(üŷ)", withdrawalLimit);
    }
}
