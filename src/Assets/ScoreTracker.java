package Assets;

import java.util.ArrayList;
import java.util.List;

public class ScoreTracker {
    protected final List<LiveMatch> liveMatchList;

    public ScoreTracker() {
        this.liveMatchList = new ArrayList<>();
    }

    public List<LiveMatch> getLiveMatchList() {
        return liveMatchList;
    }
}
