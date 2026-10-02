package Week08.class_problems;

import java.util.*;

interface Capability {
    String getName();
    boolean setValue(Object value);
    Object getValue();
}

class PowerCapability implements Capability {
    private boolean state = false;
    public String getName() { return "Power"; }
    public boolean setValue(Object value) {
        if (value instanceof Boolean) {
            this.state = (Boolean) value;
            return true;
        }
        return false;
    }
    public Object getValue() { return state; }
}

class BrightnessCapability implements Capability {
    private int level = 0;
    public String getName() { return "Brightness"; }
    public boolean setValue(Object value) {
        if (value instanceof Integer && (Integer) value >= 0 && (Integer) value <= 100) {
            this.level = (Integer) value;
            return true;
        }
        return false;
    }
    public Object getValue() { return level; }
}

class TemperatureCapability implements Capability {
    private int temp = 20;
    public String getName() { return "Temperature"; }
    public boolean setValue(Object value) {
        if (value instanceof Integer && (Integer) value >= 16 && (Integer) value <= 30) {
            this.temp = (Integer) value;
            return true;
        }
        return false;
    }
    public Object getValue() { return temp; }
}

class Device {
    private String name;
    private Map<String, Capability> capabilities = new HashMap<>();

    public Device(String name) { this.name = name; }
    public String getName() { return name; }

    public void addCapability(Capability c) {
        capabilities.put(c.getName(), c);
    }

    public boolean setCapabilityValue(String capName, Object value) {
        Capability c = capabilities.get(capName);
        if (c != null) {
            if (c.setValue(value)) {
                return true;
            } else {
                System.out.println("Rejected: " + name + " " + capName + " value out of range.");
                return false;
            }
        }
        return false;
    }

    public boolean hasCapability(String capName) {
        return capabilities.containsKey(capName);
    }
}

class Scene {
    private String sceneName;
    private List<SceneStep> steps = new ArrayList<>();

    public Scene(String sceneName) { this.sceneName = sceneName; }
    public void addStep(String capName, Object value) {
        steps.add(new SceneStep(capName, value));
    }

    public void execute(List<Device> devices) {
        System.out.println("Scene '" + sceneName + "' started.");
        int actions = 0;
        for (SceneStep step : steps) {
            for (Device d : devices) {
                if (d.setCapabilityValue(step.capName, step.value)) {
                    String valStr = step.value instanceof Boolean ?
                        ((Boolean) step.value ? "ON" : "OFF") : step.value.toString();
                    System.out.println(d.getName() + ": " + step.capName + " set to " + valStr + ".");
                    actions++;
                }
            }
        }
        System.out.println("Scene '" + sceneName + "' completed: " + actions + " actions applied.");
    }

    private static class SceneStep {
        String capName;
        Object value;
        SceneStep(String cn, Object v) { this.capName = cn; this.value = v; }
    }
}

public class Problem3 {
    public static void main(String[] args) {
        Device ac = new Device("Lab AC");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        List<Device> devices = Arrays.asList(ac, lights, projector);

        Scene lectureMode = new Scene("Lecture Mode");
        lectureMode.addStep("Power", true);
        lectureMode.addStep("Brightness", 40);
        lectureMode.addStep("Temperature", 24);

        lectureMode.execute(devices);

        ac.setCapabilityValue("Temperature", 12);

        System.out.println("Projector: Brightness capability added.");
        projector.addCapability(new BrightnessCapability());
        if (projector.setCapabilityValue("Brightness", 70)) {
            System.out.println("Projector: brightness set to 70%.");
        }
    }
}
