package models.datastructures;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

/**
 * Data structure for database ranking (table scores)
 */
public record DataScores(LocalDateTime gameTime, String playerName, String word, String missedLetters,
                         int timeSeconds) {

    public DataScores(LocalDateTime gameTime, String playerName, String word, String missedLetters, int timeSeconds) {
        this.gameTime = gameTime;
        this.playerName = playerName;
        this.word = word;
        this.missedLetters = missedLetters;
        this.timeSeconds = timeSeconds;

    }


    public LocalDateTime getGameTime() {return gameTime; }

    public String getPlayerName(){return playerName; }

    public String getGuessWord(){return word; }

    public String getMissingLetters(){return missedLetters; }

    public int getTimeSeconds(){
        LocalDateTime endTime = LocalDateTime.now();
        Temporal startTime = null;
        Duration duration = Duration.between(startTime, endTime);
        return (int) duration.getSeconds();
    }

    /**public String getGameTime() {
    } */
}
