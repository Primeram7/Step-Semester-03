package Week08.class_problems;

import java.util.*;

interface ScoringRule {
    double calculate(int idea, int execution, int presentation);
}

class InnovationScoring implements ScoringRule {
    @Override
    public double calculate(int idea, int execution, int presentation) {
        return (idea * 0.5) + (execution * 0.3) + (presentation * 0.2);
    }
}

class OpenScoring implements ScoringRule {
    @Override
    public double calculate(int idea, int execution, int presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    private String name;
    public Student(String name) { this.name = name; }
    public String getName() { return name; }
}

class Team {
    private String teamName;
    private List<Student> members;
    private String track;
    private Project project;
    private Score score;

    public Team(String teamName, List<Student> members, String track) {
        if (members.size() < 2 || members.size() > 4) {
            throw new IllegalArgumentException("A team must have 2 to 4 members.");
        }
        this.teamName = teamName;
        this.members = members;
        this.track = track;
    }

    public String getTeamName() { return teamName; }
    public String getTrack() { return track; }
    public void submitProject(Project p) { this.project = p; }
    public Project getProject() { return project; }
    public void setScore(Score s) { this.score = s; }
    public Score getScore() { return score; }
    public int getMemberCount() { return members.size(); }
}

class Project {
    private String projectName;
    public Project(String projectName) { this.projectName = projectName; }
    public String getProjectName() { return projectName; }
}

class Score {
    private int idea, execution, presentation;
    public Score(int idea, int execution, int presentation) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
    }
    public int getIdea() { return idea; }
    public int getExecution() { return execution; }
    public int getPresentation() { return presentation; }
}

class Hackathon {
    private String state = "Open"; // Open -> Judging -> Published
    private Map<String, Team> teams = new HashMap<>();
    private Set<Student> registeredStudents = new HashSet<>();
    private Map<String, ScoringRule> trackRules = new HashMap<>();

    public Hackathon() {
        trackRules.put("Innovation", new InnovationScoring());
        trackRules.put("Open", new OpenScoring());
    }

    public void registerTeam(String teamName, List<Student> members, String track) {
        if (!state.equals("Open")) {
            System.out.println("Registration closed.");
            return;
        }
        for (Student s : members) {
            if (registeredStudents.contains(s)) {
                System.out.println("Registration failed: Student " + s.getName() + " is already in a team.");
                return;
            }
        }
        try {
            Team team = new Team(teamName, members, track);
            teams.put(teamName, team);
            registeredStudents.addAll(members);
            System.out.println("Team " + teamName + " registered (" + members.size() + " members, " + track + " track).");
        } catch (IllegalArgumentException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    public void submitProject(String teamName, String projectName) {
        Team team = teams.get(teamName);
        if (team == null) return;
        team.submitProject(new Project(projectName));
        System.out.println("Project '" + projectName + "' submitted by " + teamName + ".");
    }

    public void scoreProject(String teamName, int idea, int execution, int presentation) {
        Team team = teams.get(teamName);
        if (team == null) return;
        team.setScore(new Score(idea, execution, presentation));
        System.out.println("Score recorded for '" + team.getProject().getProjectName() + "'.");
    }

    public void publishResults() {
        this.state = "Published";
        System.out.println("Results published.");
        for (Team team : teams.values()) {
            if (team.getScore() != null) {
                double finalScore = trackRules.get(team.getTrack()).calculate(
                    team.getScore().getIdea(), team.getScore().getExecution(), team.getScore().getPresentation()
                );
                System.out.printf("Team %s Final score: %.2f\n", team.getTeamName(), finalScore);
            }
        }
    }

    public void updateScore(String teamName, int idea, int execution, int presentation) {
        if (state.equals("Published")) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }
        scoreProject(teamName, idea, execution, presentation);
    }
}

public class Problem1 {
    public static void main(String[] args) {
        Hackathon h = new Hackathon();

        // 1. ByteBusters
        List<Student> members1 = Arrays.asList(new Student("Asha"), new Student("Ravi"), new Student("Neha"));
        h.registerTeam("ByteBusters", members1, "Innovation");

        // 2. SoloCoder (Should fail)
        List<Student> members2 = Arrays.asList(new Student("Kiran"));
        h.registerTeam("SoloCoder", members2, "Open");

        // 3. Submission
        h.submitProject("ByteBusters", "SmartAttend");

        // 4. Scoring
        h.scoreProject("ByteBusters", 8, 7, 9);

        // 5. Publishing
        h.publishResults();

        // 6. Attempt change
        h.updateScore("ByteBusters", 10, 7, 9);
    }
}
