package People;

public class Sailor extends Human {
    private String rank;

    public Sailor(String name, int age, String rank) {
        super(name, age);
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return super.toString() + ", rank: " + rank;
    }
}