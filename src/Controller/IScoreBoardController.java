package Controller;

import java.util.List;

import Assets.LiveMatch;
import Assets.Match;

public interface IScoreBoardController {
    boolean startMatch(Match match);
    boolean finishMatch(Match match);
    boolean changeScore(LiveMatch liveMatch);
    boolean addScoreToTeam(String teamName);
    List<LiveMatch> listMatch();
}
