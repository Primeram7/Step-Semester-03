package Week08.assignment_problems;

import java.util.*;

interface PricingPlan {
    double calculatePrice(double basePrice);
}

class DayScholarPlan implements PricingPlan {
    public double calculatePrice(double basePrice) { return basePrice; }
}

class HostellerPlan implements PricingPlan {
    public double calculatePrice(double basePrice) { return basePrice * 0.9; }
}

class StaffPlan implements PricingPlan {
    public double calculatePrice(double basePrice) { return basePrice * 0.8; }
}

class Transaction {
    private double amount;
    private String description;
    private boolean refunded = false;

    public Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public boolean isRefunded() { return refunded; }
    public void markRefunded() { this.refunded = true; }
}

class SmartCard {
    private String cardId;
    private PricingPlan plan;
    private List<Transaction> transactions = new ArrayList<>();
    private boolean blocked = false;

    public SmartCard(String cardId, PricingPlan plan) {
        this.cardId = cardId;
        this.plan = plan;
    }

    public void topUp(double amount) {
        if (blocked) {
            System.out.println("Card blocked. Top-up rejected.");
            return;
        }
        if (amount < 100) {
            System.out.println("Top-up rejected: Minimum ₹100 required.");
            return;
        }
        if (getBalance() + amount > 5000) {
            System.out.println("Top-up rejected: Max balance ₹5,000 exceeded.");
            return;
        }
        transactions.add(new Transaction(amount, "Top-up"));
        System.out.printf("%s topped up with ₹%.2f. Balance: ₹%.2f\n", cardId, amount, getBalance());
    }

    public boolean purchase(String item, double basePrice) {
        if (blocked) {
            System.out.println("Card blocked. Purchase rejected.");
            return false;
        }
        double finalPrice = plan.calculatePrice(basePrice);
        if (getBalance() < finalPrice) {
            System.out.printf("Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).\n", finalPrice, getBalance());
            return false;
        }
        transactions.add(new Transaction(-finalPrice, item));
        System.out.printf("%s purchased for ₹%.2f. Balance: ₹%.2f\n", item, finalPrice, getBalance());
        return true;
    }

    public void refund(Transaction t) {
        if (t.isRefunded()) {
            System.out.println("Refund rejected: " + t.getDescription() + " has already been refunded.");
            return;
        }
        double refundAmount = -t.getAmount();
        transactions.add(new Transaction(refundAmount, "Refund of " + t.getDescription()));
        t.markRefunded();
        System.out.printf("Refund of ₹%.2f for %s processed. Balance: ₹%.2f\n", refundAmount, t.getDescription(), getBalance());
    }

    public double getBalance() {
        double sum = 0;
        for (Transaction t : transactions) {
            sum += t.getAmount();
        }
        return sum;
    }

    public void setBlocked(boolean blocked) { this.blocked = blocked; }
    public String getCardId() { return cardId; }

    public void printMiniStatement() {
        System.out.print("Mini-statement for " + cardId + ": ");
        StringBuilder sb = new StringBuilder();
        double currentBalance = 0;
        for (Transaction t : transactions) {
            sb.append((t.getAmount() >= 0 ? "+" : "") + String.format("%.2f", t.getAmount()) + ", ");
            currentBalance += t.getAmount();
        }
        if (!sb.isEmpty()) sb.setLength(sb.length() - 2);
        System.out.println(sb.toString() + " = ₹" + String.format("%.2f", currentBalance));
    }

    public List<Transaction> getTransactions() { return transactions; }
}

public class AssignmentProblem5 {
    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());
        card.topUp(500);

        card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);
        card.purchase("Expensive Meal", 400);

        Transaction thaliTx = null;
        for (Transaction t : card.getTransactions()) {
            if ("Veg Thali".equals(t.getDescription())) {
                thaliTx = t;
                break;
            }
        }

        if (thaliTx != null) {
            card.refund(thaliTx);
            card.refund(thaliTx);
        }

        card.printMiniStatement();
    }
}
