public class Candidate{

    //fields
    private int votes;
    private String name;

    public Candidate(String name){
        super();
        votes = 0;
        this.name = name;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public void incrementVote (){
        votes++;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Total Number of votes: " + votes;
    }
}
