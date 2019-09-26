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
        name = preliminaryRaceInformation();
        candidates = preliminaryCandidateInformation();
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public String getQuitStatement() {
        return quitStatement;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    private String preliminaryRaceInformation(){
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

    private Candidate[] preliminaryCandidateInformation(){
        int numberOfCandidates = 0;

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

         Candidate[] preliminaryCandidateInformation = new Candidate[numberOfCandidates];
        for (int index = 0; index < preliminaryCandidateInformation.length; index++) {
            System.out.print("Please enter the name of candidate " + (index + 1) + " please: ");
            preliminaryCandidateInformation[index] = new Candidate(input.next());
            System.out.println("Thank you, " + preliminaryCandidateInformation[index].getName());
        }
        return preliminaryCandidateInformation;
    }

    public void startPolls(){
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

    public void runPolls(){
        String userRequest;
        do{
            printVotingExplanation();
            printCandidateNumbers();
           userRequest = input.next();
            if (userRequest.toLowerCase().equals("status")){
                printRaceStatus();
            } else if (!getQuitStatement().equals(userRequest)){
               try{
                   int potentialCandidateNum = Integer.parseInt(userRequest);
                   placeVote(potentialCandidateNum);
                } catch (Exception e){
                    System.out.println("Sorry, that is not a valid input.");
                }
            }else{
                System.out.println( findWinner().getName() + " is the winner of the " + name + " race!!");
                System.out.println("Congratulations!!!");
            }
            System.out.println();
        }  while (!getQuitStatement().equals(userRequest));
    }

    private void placeVote(int candidateNumber) {
        candidates[candidateNumber -1 ].incrementVote(); // index in array is offSet by 1
        System.out.println("Thank you for voting! Have a great rest of your day");
        totalVotes++;
    }

    private void printVotingExplanation() {
        System.out.println("Welcome to the " + getName() + " voter polls!");
        System.out.println("Down bellow will be a list of all the candidates followed by a number.");
        System.out.println("Please enter the letter that corresponds with the candidate you would like to vote for.");
        System.out.println("After you hit enter your vote will be casted and that is it, you are done.");
        System.out.println("If you would like to see the status of the race enter \"Status\" and a list of of candidates will be shown with their number of votes");
    }

    private void printCandidateNumbers(){
        for (int index = 0; index< candidates.length; index++){
                System.out.println(candidates[index].getName() + ": " + (index+1));
            }
            System.out.println();
    }

    private void printRaceStatus(){
        for (int index = 0; index < candidates.length; index++ ){
            System.out.println(candidates[index].toString());
        }
        System.out.println("Total votes: " + getTotalVotes());
    }
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



