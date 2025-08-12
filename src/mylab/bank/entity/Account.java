package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public class Account {

    private final String accountNumber;
    private final String ownerName;
    protected double balance; // ���� Ŭ�������� ���

    public Account(String accountNumber, String ownerName, double initDeposit) {
        if (initDeposit < 0) throw new IllegalArgumentException("�ʱ� �Ա��� ������ �� �����ϴ�.");
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initDeposit;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("�Աݾ��� 0���� Ŀ�� �մϴ�.");
        balance += amount;
    }

    /** �⺻ ��� ��Ģ: �ܾ� ���� �� ���� */
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) throw new IllegalArgumentException("��ݾ��� 0���� Ŀ�� �մϴ�.");
        if (balance < amount) {
            throw new InsufficientBalanceException("�ܾ� ����: ���� �ܾ� " + balance);
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s / �ܾ�: %.2f", accountNumber, ownerName, balance);
    }
}
