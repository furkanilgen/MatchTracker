// Required imports
import Controller.ScoreBoardController;
import Assets.LiveMatch;
import Assets.Match;
import Assets.ScoreTracker;

public class MatchTracker {
    public static void main(String[] args) {
        // Create a scoreboard controller instance 
        ScoreBoardController scoreBoardController = new ScoreBoardController(new ScoreTracker());

        // Start games with given team pairs
        startGames(scoreBoardController);

        // Update scores for the started games
        updateScores(scoreBoardController);

        // Display the live scores
        displayScores(scoreBoardController, "LIVE SCORES");

        // Finish the game between Mexico and Canada
        scoreBoardController.finishMatch(new Match("Mexico", "Canada"));

        // Increase scores for Germany and France
        increaseScores(scoreBoardController);

        // Display the live scores again after score updates
        displayScores(scoreBoardController, "LIVE SCORES");
    }

    private static void startGames(ScoreBoardController scoreBoardController) {
        // Start games by creating new match instances
        scoreBoardController.startMatch(new Match("Mexico", "Canada"));
        scoreBoardController.startMatch(new Match("Spain", "Brazil"));
        scoreBoardController.startMatch(new Match("Germany", "France"));
        scoreBoardController.startMatch(new Match("Uruguay", "Italy"));
        scoreBoardController.startMatch(new Match("Argentina", "Australia"));
    }

    private static void updateScores(ScoreBoardController scoreBoardController) {
        // Update the live match scores
        scoreBoardController.changeScore(new LiveMatch("Mexico", 0, "Canada", 5));
        scoreBoardController.changeScore(new LiveMatch("Spain", 10, "Brazil", 2));
        scoreBoardController.changeScore(new LiveMatch("Germany", 2, "France", 2));
        scoreBoardController.changeScore(new LiveMatch("Uruguay", 6, "Italy", 6));
        scoreBoardController.changeScore(new LiveMatch("Argentina", 3, "Australia", 1));
    }

    private static void displayScores(ScoreBoardController scoreBoardController, String title) {
        // Print the title and iterate over the summary to display each match's summary
        System.out.println(title);
        for (LiveMatch liveMatch : scoreBoardController.listMatch()) {
            System.out.println(liveMatch.getMatchSummary());
        }
    }

    private static void increaseScores(ScoreBoardController scoreBoardController) {
        // Increase the score of given teams
        scoreBoardController.addScoreToTeam("France");   
    }
}
