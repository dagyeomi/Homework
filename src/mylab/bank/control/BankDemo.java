package mylab.bank.control;

import mylab.bank.entity.Bank;
import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class BankDemo {

    public static void main(String[] args) {
        Bank bank = new Bank();

        // ���� ����
        String accKim = bank.createCheckingAccount("��ö��", 500_000, 300_000); // �ѵ� 30��
        String accLee = bank.createSavingsAccount("�̿���", 1_000_000, 0.02);

        bank.printAllAccounts();

        try {
            // ���� �����/��ü
            bank.deposit(accKim, 100_000);
            bank.withdraw(accKim, 250_000);
            bank.transfer(accLee, accKim, 200_000);

            // ������ ���̾�׷��� alt �帧 �׽�Ʈ
            // 1) �ѵ� �ʰ�
            bank.withdraw(accKim, 500_000);

        } catch (WithdrawalLimitExceededException e) {
            System.out.println("[�ѵ� �ʰ�] " + e.getMessage());
            try {
                // 2) �ܾ� ����
                bank.withdraw(accLee, 10_000_000);
            } catch (InsufficientBalanceException ibe) {
                System.out.println("[�ܾ� ����] " + ibe.getMessage());
                try {
                    // 3) �������� �ʴ� ����
                    bank.withdraw("ACC-9999", 10_000);
                } catch (AccountNotFoundException anf) {
                    System.out.println("[���� ����] " + anf.getMessage());
                } catch (Exception ignored) { /* �������� ���� */ }
            } catch (Exception ignored) { /* �������� ���� */ }
        } catch (AccountNotFoundException e) {
            System.out.println("[���� ����] " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("[�ܾ� ����] " + e.getMessage());
        }

        System.out.println();
        bank.printAllAccounts();
        System.out.println("���� ����");
    }
}
