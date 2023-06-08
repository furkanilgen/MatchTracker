package Assets;

public class LiveMatch extends Match{
    private int homeScore;
    private int awayScore;

    public LiveMatch(String homeTeam, String awayTeam) {
        super(homeTeam, awayTeam);
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public LiveMatch(String homeTeam, int homeScore, String awayTeam, int awayScore) {
        super(homeTeam, awayTeam);
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public String getMatchSummary() {
        return homeTeam + " - " + awayTeam + " : " + homeScore + " - " + awayScore;
    }

    public Integer getTotalScore() {
        return homeScore + awayScore;
    }
}
