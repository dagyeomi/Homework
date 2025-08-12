package mylab.bank.entity;

/** 이자율이 있는 저축 계좌 */
public class SavingsAccount extends Account {

    private double interestRate; // 연 이자율(예: 0.02 = 2%)

    public SavingsAccount(String accountNumber, String owner, double initDeposit, double interestRate) {
        super(accountNumber, owner, initDeposit);
        this.interestRate = interestRate;
    }

    public double getInterestRate() { return interestRate; }

    public void setInterestRate(double interestRate) {
        if (interestRate < 0) throw new IllegalArgumentException("이자율은 음수일 수 없습니다.");
        this.interestRate = interestRate;
    }

    /** 단순 이자 적용 */
    public void applyInterest() {
        balance += balance * interestRate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" / 이자율: %.2f%%(저축)", interestRate * 100);
    }
}
