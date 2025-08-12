package mylab.bank.entity;

/** �������� �ִ� ���� ���� */
public class SavingsAccount extends Account {

    private double interestRate; // �� ������(��: 0.02 = 2%)

    public SavingsAccount(String accountNumber, String owner, double initDeposit, double interestRate) {
        super(accountNumber, owner, initDeposit);
        this.interestRate = interestRate;
    }

    public double getInterestRate() { return interestRate; }

    public void setInterestRate(double interestRate) {
        if (interestRate < 0) throw new IllegalArgumentException("�������� ������ �� �����ϴ�.");
        this.interestRate = interestRate;
    }

    /** �ܼ� ���� ���� */
    public void applyInterest() {
        balance += balance * interestRate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" / ������: %.2f%%(����)", interestRate * 100);
    }
}
