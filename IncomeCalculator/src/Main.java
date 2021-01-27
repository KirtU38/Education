import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Main {
    private static int minIncome = 200000; // Минимальная прибыль компании
    private static int maxIncome = 900000; // Максимальная прибыль компании

    private static int officeRentCharge = 140000; // Плата за аренду офиса
    private static int telephonyCharge = 12000; // Плата за телефон
    private static int internetAccessCharge = 7200; // Плата за интернет

    private static int assistantSalary = 45000; // Зарплата ассистента
    private static int financeManagerSalary = 90000; // Зарплата финансового менеджера

    private static double mainTaxPercent = 0.24; // Налог на доход 24%
    private static double managerPercent = 0.15; // Процент от дохода который получает менеджер 15%

    private static double minInvestmentsAmount = 100000; // Минимальный размер инвестиции

    public static void main(String[] args) {

        // pureIncomeAfterTax  = minInvestmentsAmount = 100.000
        // pureIncomeBeforeTax = 100.000 \ (1 - mainTaxPercent) = 131.579
        // incomeResult = (131.579 + calculateFixedCharges()) / (1-managerPercent) =500.916,4
        // Ответ: 500.917

        double pureIncomeBeforeTax = minInvestmentsAmount / (1 - mainTaxPercent);

        double result = (calculateFixedCharges() + pureIncomeBeforeTax) / (1 - managerPercent);
        System.out.println("Минимальная прибыль для инвестирования: " + Math.ceil(result));

        while (true) {

            System.out.println("Введите сумму доходов компании за месяц " +
                    "(от 200 до 900 тысяч рублей): "); // Выводит текст
            int income = (new Scanner(System.in)).nextInt(); // Переменная дохода компании, инпут от пользователя

            if (!checkIncomeRange(income)) { // Проверка входит ли сумма в границы от 200 до 900 тыс
                continue;
            }

            double managerSalary = income * managerPercent; // Зарплата менеджера с процентами


            double pureIncome = income - managerSalary -
                    calculateFixedCharges(); // Чистый доход до вычета налогов

            double taxAmount = mainTaxPercent * pureIncome; // Вычисление налога на доход

            double pureIncomeAfterTax = pureIncome - taxAmount; // Чистый доход после налогов

            boolean canMakeInvestments = pureIncomeAfterTax >=
                    minInvestmentsAmount; // Проверяет возможность инвестирования

            System.out.println("Зарплата менеджера: " + managerSalary);
            System.out.println("Общая сумма налогов: " +
                    (taxAmount > 0 ? taxAmount : 0));
            System.out.println("Компания может инвестировать: " +
                    (canMakeInvestments ? "да" : "нет"));
            System.out.println("Чистый после налогов " + pureIncomeAfterTax);
            if (pureIncome < 0) {
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");
            }
        }
    }

    private static boolean checkIncomeRange(int income) { // Метод проверяет входит ли сумма в границы
        if (income < minIncome) { // Проверка если прибыль меньше нижней границы
            System.out.println("Доход меньше нижней границы");
            return false;
        }
        if (income > maxIncome) { // Проверка если прибыль больше верхней границы
            System.out.println("Доход выше верхней границы");
            return false;
        }
        return true;
    }

    private static int calculateFixedCharges() { // Метод суммирует все фиксированные расходы в одно число
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary;
    }
}
