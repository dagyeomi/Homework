package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

/** Bank�� Account���� �����ֱ⸦ ����(�ռ�) */
public class Bank {

    private final List<Account> accounts = new ArrayList<>();
    private int nextAccountNumber = 1;

    private String generateAccountNumber() {
        return String.format("ACC-%04d", nextAccountNumber++);
    }

    public String createSavingsAccount(String owner, double initDeposit, double interestRate) {
        String accNo = generateAccountNumber();
        accounts.add(new SavingsAccount(accNo, owner, initDeposit, interestRate));
        return accNo;
    }

    public String createCheckingAccount(String owner, double initDeposit, double withdrawalLimit) {
        String accNo = generateAccountNumber();
        accounts.add(new CheckingAccount(accNo, owner, initDeposit, withdrawalLimit));
        return accNo;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account a : accounts) {
            if (a.getAccountNumber().equalsIgnoreCase(accountNumber)) return a;
        }
        throw new AccountNotFoundException(accountNumber);
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        findAccount(accountNumber).deposit(amount);
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account a = findAccount(accountNumber);
        // CheckingAccount�� �ѵ� �˻� ����(�������̵�)
        if (a instanceof CheckingAccount) {
            ((CheckingAccount) a).withdraw(amount);
        } else {
            a.withdraw(amount);
        }
    }

    public void transfer(String fromAcc, String toAcc, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account from = findAccount(fromAcc);
        Account to = findAccount(toAcc);

        // ���(���� �߻� ����)
        if (from instanceof CheckingAccount) {
            ((CheckingAccount) from).withdraw(amount);
        } else {
            from.withdraw(amount);
        }
        // �Ա�
        to.deposit(amount);
    }

    public void printAllAccounts() {
        System.out.println("=== ���� ���� ���� ��� ===");
        for (Account a : accounts) System.out.println(a);
        System.out.println("�� ���� ��: " + accounts.size());
    }
}
