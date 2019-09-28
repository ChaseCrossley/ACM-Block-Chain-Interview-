import java.util.Scanner;

public class Race {
    private Scanner input;
    private String name;
    private Candidate[] candidates;
    private String quitStatement;
    private int totalVotes;

    public Race() {
        input = new Scanner(System.in);
        totalVotes =0;
        name = setPreliminaryRaceInformation();
        candidates = setPreliminaryCandidateInformation();
    }

    public String getName() {
        return name;
    }

    public String getQuitStatement() {
        return quitStatement;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    // gathers information regarding the race
    private String setPreliminaryRaceInformation(){
        System.out.println("Welcome!");
        System.out.print("What would you like to name this voter poll today?: ");
        String raceName = null;
       while (raceName == null) {
           String potentialName = input.nextLine().trim();
           if (!potentialName.equals("")) {
               raceName = potentialName;
           } else {
               System.out.print("Sorry, you cannot have a blank name.\nWhat would you like to name this voter poll today?: ");
           }
       }
       return raceName;
    }

    // gathers information on each candidate
    private Candidate[] setPreliminaryCandidateInformation(){
        int numberOfCandidates = 0;

        // find the number of candidates
        while (numberOfCandidates == 0) {
            System.out.print("Please enter the number of candidates with a whole number: ");
            try {
                numberOfCandidates = Integer.parseInt(input.next());
                if (numberOfCandidates != 0) {
                    System.out.println("Thank you!");
                }
            } catch (Exception e){
                System.out.println("Sorry, that is not a valid input.");
            }
        }

        // allocates names to the candidates
         Candidate[] preliminaryCandidateInformation = new Candidate[numberOfCandidates];
        for (int index = 0; index < preliminaryCandidateInformation.length; index++) {
            System.out.print("Please enter the name of candidate " + (index + 1) + " please: ");
            preliminaryCandidateInformation[index] = new Candidate(input.next());
            System.out.println("Thank you, " + preliminaryCandidateInformation[index].getName());
        }
        return preliminaryCandidateInformation;
    }

    // gets the information to be able to start the polls and
    public void setQuitStatement(){
        System.out.println("The polls are about to begin");
        String quitStatement = null;
        do {
            if (quitStatement != null){
                System.out.println("It looks like your stop phrases were not the same please try again");
            }
            System.out.println("What would you like your stop phrase to be? This can be entered during the polls to end the polls and a winner will be declared");
            quitStatement = input.next();
            System.out.println("Please confirm your quit statement by typing it once more");
        } while (!quitStatement.equals(input.next()));
       this.quitStatement = quitStatement;
    }

    // declares winner when the getQuitStatement has been entered
    public void runPolls(){
        String userRequest;
        do{
            printVotingExplanation();
            printCandidateNumbers();
           userRequest = input.next();
            if (userRequest.toLowerCase().equals("status")){
                printRaceStatus();
            } else if (!getQuitStatement().equals(userRequest)){ // Case when user votes
               try{ // ensures that the input given is a valid input, numbers that dont exceed the number of candidates or other strings
                   int potentialCandidateNum = Integer.parseInt(userRequest);
                   placeVote(potentialCandidateNum);
                } catch (Exception e){
                    System.out.println("Sorry, that is not a valid input.");
                }
            }else{ // quite statement has been entered and a winner is declared
                nameWinner();
            }
            System.out.println();
        }  while (!getQuitStatement().equals(userRequest));
    }

    // allocates a vote to the candidate the user picked
    private void placeVote(int candidateNumber) {
        candidates[candidateNumber -1 ].incrementVote(); // index in array is offSet by 1
        System.out.println("Thank you for voting! Have a great rest of your day");
        totalVotes++;
    }

    // explains to the user how to use the voting system
    private void printVotingExplanation() {
        System.out.println("Welcome to the " + getName() + " voter polls! " +
                "Down bellow will be a list of all the candidates followed by a number." +
                "Please enter the letter that corresponds with the candidate you would like to vote for." +
                "After you hit enter your vote will be casted and that is it, you are done." +
                "If you would like to see the status of the race enter \"Status\" and a list of of candidates will be shown with their number of votes");
    }

    // prints the name of the candidate and the number they are a associated with
    private void printCandidateNumbers(){
        for (int index = 0; index< candidates.length; index++){
                System.out.println(candidates[index].getName() + ": " + (index+1));
            }
            System.out.println();
    }

    // Declares the winner to the user
    private void nameWinner(){
        System.out.println( findWinner().getName() + " is the winner of the " + name + " race!!");
        System.out.println("Congratulations!!!");
    }

    // Shows the user the number of votes for each candidate, in the order they were entered
    private void printRaceStatus(){
        for (int index = 0; index < candidates.length; index++ ){
            System.out.println(candidates[index].toString());
        }
        System.out.println("Total votes: " + getTotalVotes());
    }

    // finds the winner of the race
    private Candidate findWinner(){
        Candidate winner = candidates[0];
        for (Candidate runner : candidates) {
            if (runner.getVotes() > winner.getVotes()){
                winner = runner;
            }
        }
        return winner;
    }

}



