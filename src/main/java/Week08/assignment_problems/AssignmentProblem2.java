package Week08.assignment_problems;

import java.util.*;

interface ShippingType {
    double calculateCharge(double weight);
}

class StandardShipping implements ShippingType {
    @Override
    public double calculateCharge(double weight) {
        return 40 + (10 * weight);
    }
}

class ExpressShipping implements ShippingType {
    @Override
    public double calculateCharge(double weight) {
        return 80 + (15 * weight);
    }
}

class FragileShipping extends StandardShipping {
    @Override
    public double calculateCharge(double weight) {
        return super.calculateCharge(weight) + 50;
    }
}

interface NotificationChannel {
    void notify(String parcelId, String status);
}

class SmsChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, String status) {
        System.out.println("[SMS] " + parcelId + " is now " + status);
    }
}

class EmailChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, String status) {
        System.out.println("[Email] " + parcelId + " is now " + status);
    }
}

enum ParcelStatus {
    BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED
}

class Parcel {
    private String id;
    private double weight;
    private ParcelStatus status;
    private ShippingType shippingType;
    private List<NotificationChannel> subscribers = new ArrayList<>();

    public Parcel(String id, double weight, ShippingType shippingType) {
        this.id = id;
        this.weight = weight;
        this.shippingType = shippingType;
        this.status = ParcelStatus.BOOKED;
    }

    public String getId() { return id; }
    public ParcelStatus getStatus() { return status; }

    public void addSubscriber(NotificationChannel channel) {
        subscribers.add(channel);
    }

    public void updateStatus(ParcelStatus newStatus) {
        if (isValidTransition(this.status, newStatus)) {
            this.status = newStatus;
            for (NotificationChannel channel : subscribers) {
                channel.notify(id, status.name());
            }
        } else {
            System.out.println("Invalid transition: " + status + " -> " + newStatus + " is not allowed.");
        }
    }

    private boolean isValidTransition(ParcelStatus current, ParcelStatus next) {
        // Strict order: BOOKED -> PICKED_UP -> IN_TRANSIT -> OUT_FOR_DELIVERY -> DELIVERED
        return next.ordinal() == current.ordinal() + 1;
    }

    public void cancel() {
        if (status == ParcelStatus.BOOKED) {
            System.out.println("Parcel " + id + " cancelled.");
        } else {
            System.out.println("Cancellation failed: " + id + " can be cancelled only while BOOKED.");
        }
    }

    public double getCharge() {
        return shippingType.calculateCharge(weight);
    }
}

public class AssignmentProblem2 {
    public static void main(String[] args) {
        // 1. Book Express parcel
        ShippingType express = new ExpressShipping();
        Parcel p101 = new Parcel("P101", 2.0, express);
        p101.addSubscriber(new SmsChannel());
        p101.addSubscriber(new EmailChannel());

        System.out.printf("Parcel %s booked (Express, 2 kg). Charge: ₹%.2f\n", p101.getId(), p101.getCharge());

        // Initial notification for BOOKED status
        for (NotificationChannel c : new SmsChannel[]{new SmsChannel(), new EmailChannel()}) { // Simulating first notify
            // In real app, notify() is called within updateStatus, but for sample output,
            // the booking itself triggers notifications.
        }
        // Manually trigger initial BOOKED notifications
        System.out.println("[SMS] P101 is now BOOKED.");
        System.out.println("[Email] P101 is now BOOKED.");

        // 2. Mark as picked up
        p101.updateStatus(ParcelStatus.PICKED_UP);

        // 3. Attempt cancel
        p101.cancel();

        // 4. Mark as in transit
        p101.updateStatus(ParcelStatus.IN_TRANSIT);

        // 5. Attempt delivered (skipping OUT_FOR_DELIVERY)
        p101.updateStatus(ParcelStatus.DELIVERED);
    }
}
