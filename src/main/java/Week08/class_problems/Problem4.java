package Week08.class_problems;

import java.util.*;

interface CreditPolicy {
    int getLimit();
}

class RegularPolicy implements CreditPolicy { public int getLimit() { return 24; } }
class HonorsPolicy implements CreditPolicy { public int getLimit() { return 28; } }
class ExchangePolicy implements CreditPolicy { public int getLimit() { return 20; } }

class Student {
    private String name;
    private CreditPolicy policy;
    private int currentCredits;
    private Set<Elective> enrolledElectives = new HashSet<>();

    public Student(String name, CreditPolicy policy, int currentCredits) {
        this.name = name;
        this.policy = policy;
        this.currentCredits = currentCredits;
    }

    public String getName() { return name; }
    public int getCurrentCredits() { return currentCredits; }
    public void addCredits(int c) { this.currentCredits += c; }
    public void removeCredits(int c) { this.currentCredits -= c; }
    public int getCreditLimit() { return policy.getLimit(); }
    public Set<Elective> getEnrolledElectives() { return enrolledElectives; }

    public boolean canEnroll(int electiveCredits) {
        return (currentCredits + electiveCredits) <= getCreditLimit();
    }
}

class Elective {
    private String name;
    private int capacity;
    private int credits;
    private List<Student> enrolledStudents = new ArrayList<>();
    private Queue<Student> waitlist = new LinkedList<>();

    public Elective(String name, int capacity, int credits) {
        this.name = name;
        this.capacity = capacity;
        this.credits = credits;
    }

    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public int getCredits() { return credits; }
    public List<Student> getEnrolledStudents() { return enrolledStudents; }
    public Queue<Student> getWaitlist() { return waitlist; }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    public void enroll(Student s) {
        enrolledStudents.add(s);
    }

    public void drop(Student s) {
        enrolledStudents.remove(s);
    }
}

class EnrollmentService {
    public void enrollStudent(Student s, Elective e) {
        if (s.getEnrolledElectives().contains(e)) {
            System.out.println("Student already enrolled or waitlisted for " + e.getName());
            return;
        }

        if (!s.canEnroll(e.getCredits())) {
            System.out.printf("Enrollment failed: %s would exceed the credit limit (%d/%d).\n",
                s.getName(), (s.getCurrentCredits() + e.getCredits()), s.getCreditLimit());
            return;
        }

        if (!e.isFull()) {
            e.enroll(s);
            s.addCredits(e.getCredits());
            s.getEnrolledElectives().add(e);
            System.out.printf("%s enrolled in %s (credits: %d/%d).\n",
                s.getName(), e.getName(), s.getCurrentCredits(), s.getCreditLimit());
        } else {
            e.getWaitlist().add(s);
            s.getEnrolledElectives().add(e);
            System.out.printf("%s added to waitlist (position %d).\n",
                s.getName(), e.getWaitlist().size());
        }
    }

    public void dropStudent(Student s, Elective e) {
        if (!e.getEnrolledStudents().contains(s)) {
            System.out.println("Student not enrolled in " + e.getName());
            return;
        }
        e.drop(s);
        s.removeCredits(e.getCredits());
        System.out.printf("%s dropped %s (credits: %d/%d).\n",
            s.getName(), e.getName(), s.getCurrentCredits(), s.getCreditLimit());

        promoteWaitlist(e);
    }

    private void promoteWaitlist(Elective e) {
        while (!e.getWaitlist().isEmpty() && !e.isFull()) {
            Student next = e.getWaitlist().peek();
            if (next.canEnroll(e.getCredits())) {
                e.getWaitlist().poll();
                e.enroll(next);
                next.addCredits(e.getCredits());
                System.out.printf("%s promoted from waitlist and enrolled in %s (credits: %d/%d).\n",
                    next.getName(), e.getName(), next.getCurrentCredits(), next.getCreditLimit());
            } else {
                e.getWaitlist().poll();
                System.out.println("Waitlist promotion failed for " + next.getName() + " due to credit limit.");
            }
        }
    }
}

public class Problem4 {
    public static void main(String[] args) {
        EnrollmentService service = new EnrollmentService();
        Elective cloud = new Elective("Cloud Computing", 2, 4);

        Student asha = new Student("Asha", new RegularPolicy(), 20);
        Student ravi = new Student("Ravi", new HonorsPolicy(), 22);
        Student neha = new Student("Neha", new ExchangePolicy(), 12);
        Student kiran = new Student("Kiran", RegularPolicy.class.getDeclaredConstructor().newInstance() == null ? null : new RegularPolicy(), 22);
        // Correcting Kiran's init
        Student kiranFixed = new Student("Kiran", new RegularPolicy(), 22);

        service.enrollStudent(asha, cloud);
        service.enrollStudent(ravi, cloud);
        service.enrollStudent(neha, cloud);
        service.enrollStudent(kiranFixed, cloud);

        service.dropStudent(asha, cloud);
    }
}
