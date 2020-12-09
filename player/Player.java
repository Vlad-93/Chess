package player;

import game.GameResult;

public class Player {
    private String playerName;
    private int wins = 0;
    private int draws = 0;
    private int looses = 0;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void writeResult(PlayerResult result) {
        switch(result) {
            case win:
                wins++;
                break;
            case draw:
                draws++;
                break;
            case loos:
                looses++;
        }
    }

    public Player(String playerName) {
        this.playerName = playerName;
    }
}
