package mylab.bank.control;

import mylab.bank.entity.Bank;
import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class BankDemo {

    public static void main(String[] args) {
        Bank bank = new Bank();

        // 계좌 생성
        String accKim = bank.createCheckingAccount("김철수", 500_000, 300_000); // 한도 30만
        String accLee = bank.createSavingsAccount("이영희", 1_000_000, 0.02);

        bank.printAllAccounts();

        try {
            // 정상 입출금/이체
            bank.deposit(accKim, 100_000);
            bank.withdraw(accKim, 250_000);
            bank.transfer(accLee, accKim, 200_000);

            // 시퀀스 다이어그램의 alt 흐름 테스트
            // 1) 한도 초과
            bank.withdraw(accKim, 500_000);

        } catch (WithdrawalLimitExceededException e) {
            System.out.println("[한도 초과] " + e.getMessage());
            try {
                // 2) 잔액 부족
                bank.withdraw(accLee, 10_000_000);
            } catch (InsufficientBalanceException ibe) {
                System.out.println("[잔액 부족] " + ibe.getMessage());
                try {
                    // 3) 존재하지 않는 계좌
                    bank.withdraw("ACC-9999", 10_000);
                } catch (AccountNotFoundException anf) {
                    System.out.println("[계좌 없음] " + anf.getMessage());
                } catch (Exception ignored) { /* 도달하지 않음 */ }
            } catch (Exception ignored) { /* 도달하지 않음 */ }
        } catch (AccountNotFoundException e) {
            System.out.println("[계좌 없음] " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("[잔액 부족] " + e.getMessage());
        }

        System.out.println();
        bank.printAllAccounts();
        System.out.println("데모 종료");
    }
}
