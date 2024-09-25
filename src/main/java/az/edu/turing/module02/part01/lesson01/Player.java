package az.edu.turing.module02.part01.lesson01;

public class Player implements Comparable<Player> {
    private final int id;
    private final int age;
    private final String name;
    private final double score;
    private final boolean isFemale;

    public Player(int id, int age, String name, double score, boolean isFemale) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.score = score;
        this.isFemale = isFemale;
    }

    @Override
    public String toString() {
        return "Player{id=%d, age=%d, name='%s', score=%s, isFemale=%s}".formatted(id, age, name, score, isFemale);
    }

    @Override
    public int compareTo(Player that) {
        return Integer.compare(this.id, that.id);
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public boolean isFemale() {
        return isFemale;
    }
}
