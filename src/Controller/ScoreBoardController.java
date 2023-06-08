package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Assets.LiveMatch;
import Assets.ScoreTracker;
import Assets.Match;

public class ScoreBoardController implements IScoreBoardController {
    private final ScoreTracker scoreBoard;

    public ScoreBoardController(ScoreTracker scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    // Start a game with given match. Return false if any of the teams are already in a match.
    public boolean startMatch(Match match) {
        if (checkTeamsAlreadyInMatch(match)) {
            return false;
        }
        scoreBoard.getLiveMatchList().add(new LiveMatch(match.getHomeTeam(), match.getAwayTeam()));
        return true;
    }

    // Finish the game for the given match. Return true if successful, false otherwise.
    public boolean finishMatch(Match match) {
        return scoreBoard.getLiveMatchList().removeIf(m ->
            m.getHomeTeam().equals(match.getHomeTeam()) && m.getAwayTeam().equals(match.getAwayTeam())
        );
    }

    // Update the score for a live match. Return true if successful, false otherwise.
    public boolean changeScore(LiveMatch liveMatch) {
        List<LiveMatch> liveMatchList = scoreBoard.getLiveMatchList();
        for(int i = 0; i < liveMatchList.size(); i++) {
            if(liveMatchList.get(i).getHomeTeam().equals(liveMatch.getHomeTeam())
                    && liveMatchList.get(i).getAwayTeam().equals(liveMatch.getAwayTeam())) {
                liveMatchList.set(i, liveMatch);
                return true;
            }
        }
        return false;
    }

    // Increase the score for a team. Return true if successful, false otherwise.
    public boolean addScoreToTeam(String teamName) {
        return scoreBoard.getLiveMatchList().stream().peek(m -> {
            if(m.getHomeTeam().equals(teamName)) {
                m.setHomeScore(m.getHomeScore() + 1);
            }
            else if(m.getAwayTeam().equals(teamName)) {
                m.setAwayScore(m.getAwayScore() + 1);
            }
        }).anyMatch(m -> m.getHomeTeam().equals(teamName) || m.getAwayTeam().equals(teamName));
    }

    // Get a sorted summary of all the live matches. The summary is sorted in descending order of total score.
    public List<LiveMatch> listMatch() {
        List<LiveMatch> matchListCopy = new ArrayList<>(scoreBoard.getLiveMatchList());
        return matchListCopy.stream().sorted((o1, o2) -> Integer.compare(o2.getTotalScore(), o1.getTotalScore())).collect(Collectors.toList());
    }

    // Check if any of the teams in the match are already in a live match.
    private boolean checkTeamsAlreadyInMatch(Match match) {
        return scoreBoard.getLiveMatchList().stream().anyMatch(m ->
            m.getHomeTeam().equals(match.getHomeTeam()) || m.getAwayTeam().equals(match.getAwayTeam())
        );
    }
}
