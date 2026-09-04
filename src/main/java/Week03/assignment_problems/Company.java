package Week03.assignment_problems;
public class Company {
    static class Employee
    {
        private
            int empid;
            String ename;
            double salary;
        Employee(int id,String name,double s)
        {
            empid = id;
            ename = name;
            salary = s;
        }
        double getsalary()
        {
            return salary;
        }
    }
    static class Manager extends Employee
    {
        private
            double teamBonus;
        Manager(int id,String name,double s,double bonus)
        {
            super(id, name, s);
            teamBonus = bonus;
        }
        double EffectiveSalary()
        {
            return getsalary()+teamBonus;
        }
    }
    static class Intern extends Employee
    {
        private
            double stipend;
        Intern(int id,String name,double s ,double stipend)
        {
            super(id, name, s);
            this.stipend = stipend;
        }
        double EffectiveSalary()
        {
            return(getsalary()<stipend?getsalary():stipend);
        }
    }
    public static void main(String[] args) {
        Employee e1 = new Employee(101, "Rakesh", 125000.68);
        Manager m1 =new Manager(102, "Ritesh", 250000, 75000);
        Intern s1 = new Intern(103, "Hemanth", 120000, 7000);
        System.out.println("Employee Pay : "+e1.getsalary());
        System.out.println("Manager Effective Pay : "+m1.EffectiveSalary());
        System.out.println("Inter Effective Pay : "+s1.EffectiveSalary());
    }
    
}
