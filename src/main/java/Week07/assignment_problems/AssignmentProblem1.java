package Week07.assignment_problems;

abstract class Shape {
    private static int counter = 0;
    private final String shapeId;

    public Shape() {
        this.shapeId = "SHAPE-" + (++counter);
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        // Base implementation if needed, but subclasses will typically override
    }

    public void scale(double xFactor, double yFactor) {
        // Base implementation if needed
    }

    public String getShapeId() {
        return this.shapeId;
    }

    public static void printArea(Shape s) {
        System.out.println("Area of " + s.getShapeId() + ": " + s.calculateArea());
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        super();
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double factor) {
        this.radius *= factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        // For a circle, uneven scaling makes it an ellipse, but for this problem
        // we'll assume average scaling or just call the single factor one as per hint.
        scale((xFactor + yFactor) / 2.0);
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        super();
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double factor) {
        this.side *= factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        // As per hint: call single factor twice or handle dimensions.
        // Since it's a square, if we scale x and y differently it's a rectangle,
        // but we must maintain SquareShape. We'll use average.
        scale((xFactor + yFactor) / 2.0);
    }
}

public class AssignmentProblem1 {
    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        System.out.println("Circle Area: " + c.calculateArea());

        SquareShape sq = new SquareShape(4.0);
        System.out.println("Square Area: " + sq.calculateArea());

        sq.scale(2.0);
        System.out.println("Scaled Square Area: " + sq.calculateArea());

        Shape.printArea(c);
        Shape.printArea(sq);
    }
}
