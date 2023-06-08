package MatchTrackerTest;

import Assets.LiveMatch;
import Assets.Match;
import Assets.ScoreTracker;
import Controller.ScoreBoardController;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

// Class for testing ScoreBoardController functionalities
public class ScoreBoardControllerTest {

    // Instance of ScoreBoardController
    private ScoreBoardController scoreBoardController;
    // Instances of Match for testing
    private Match mexicoCanada, mexicoTurkey, spainBrazil, germanyFrance, uruguayItaly, argentinaAustralia;

    // Method to set up testing environment before each test
    @Before
    public void setUp() {
        // Create new ScoreBoardController with a new ScoreTracker
        scoreBoardController = new ScoreBoardController(new ScoreTracker());
        // Create Matches for testing
        mexicoCanada = new Match("Mexico", "Canada");
        mexicoTurkey = new Match("Mexico", "Turkey");
        spainBrazil = new Match("Spain", "Brazil");
        germanyFrance = new Match("Germany", "France");
        uruguayItaly = new Match("Uruguay", "Italy");
        argentinaAustralia = new Match("Argentina", "Australia");
    }

    // Test to check if game starts successfully
    @Test
    public void testStartMatchSuccess() {
        // Start new match and check if successful
        assertTrue(scoreBoardController.startMatch(germanyFrance));
        // Get list of ongoing matches
        List<LiveMatch> liveMatchList = scoreBoardController.listMatch();
        // Ensure list of matches is not empty
        assertFalse(liveMatchList.isEmpty());
        // Check match summary for first match
        assertEquals("Germany - France : 0 - 0", liveMatchList.get(0).getMatchSummary());
    }

    // Test to check if starting a game returns false when a team is already playing a match
    @Test
    public void testStartMatchReturnsFalseWhenAnyTeamHasMatch() {
        // Start new match and check if successful
        assertTrue(scoreBoardController.startMatch(mexicoCanada));
        // Try to start match with team already playing, should fail
        assertFalse(scoreBoardController.startMatch(mexicoTurkey));
    }

    // Test to check if a game finishes successfully
    @Test
    public void testFinishMatchSuccess() {
        // Start new match
        scoreBoardController.startMatch(germanyFrance);
        // Ensure list of matches is not empty
        assertFalse(scoreBoardController.listMatch().isEmpty());
        // Finish the match
        scoreBoardController.finishMatch(germanyFrance);
        // Ensure list of matches is now empty
        assertTrue(scoreBoardController.listMatch().isEmpty());
    }

    // Test to check if finishing a game fails for a non-existing match
    @Test
    public void testFinishMatchFailForNonExistingMatch() {
        // Start new match
        scoreBoardController.startMatch(mexicoCanada);
        // Ensure list of matches is not empty
        assertFalse(scoreBoardController.listMatch().isEmpty());
        // Attempt to finish non-existing match, should fail
        assertFalse(scoreBoardController.finishMatch(mexicoTurkey));
    }

    // Test to check if the score updates correctly
    @Test
    public void testChangeScore() {
        // Start new match
        scoreBoardController.startMatch(germanyFrance);
        // Update the score for the match
        scoreBoardController.changeScore(new LiveMatch("Germany", 2, "France", 2));
        // Check match summary for updated score
        assertEquals("Germany - France : 2 - 2", scoreBoardController.listMatch().get(0).getMatchSummary());
    }

    // Test to check if the score increases correctly for a team
    @Test
    public void testAddScoreToTeam() {
        // Start new match
        scoreBoardController.startMatch(germanyFrance);
        // Increase score for Mexico
        scoreBoardController.addScoreToTeam("France");
        // Check match summary for updated score
        assertEquals("Germany - France : 0 - 1", scoreBoardController.listMatch().get(0).getMatchSummary());
    }

    // Test to check if the summary returns correctly
    @Test
    public void testlistMatches() {
        // Start several new matches
        scoreBoardController.startMatch(mexicoCanada);
        scoreBoardController.startMatch(spainBrazil);
        scoreBoardController.startMatch(germanyFrance);
        scoreBoardController.startMatch(uruguayItaly);
        scoreBoardController.startMatch(argentinaAustralia);

        // Update scores for matches
        scoreBoardController.changeScore(new LiveMatch("Mexico", 0, "Canada", 5));
        scoreBoardController.changeScore(new LiveMatch("Spain", 10, "Brazil", 2));
        scoreBoardController.changeScore(new LiveMatch("Germany", 2, "France", 2));
        scoreBoardController.changeScore(new LiveMatch("Uruguay", 6, "Italy", 6));
        scoreBoardController.changeScore(new LiveMatch("Argentina", 3, "Australia", 1));

        // Get list of matches
        List<LiveMatch> actual = scoreBoardController.listMatch();

        // Prepare expected list of matches
        List<LiveMatch> expected = Arrays.asList(
            new LiveMatch("Spain", 10,"Brazil", 2),
            new LiveMatch("Uruguay", 6, "Italy", 6),
            new LiveMatch("Mexico", 0, "Canada", 5),
            new LiveMatch("Germany", 2, "France", 2),
            new LiveMatch("Argentina", 3, "Australia", 1)
        );

        // Check size of returned list matches expected
        assertEquals(expected.size(), actual.size());

        // Check match summary for each match
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i).getMatchSummary(), actual.get(i).getMatchSummary());
        }
    } 
}
