package mds.multigame.manager;

import mds.multigame.model.Player;

public class PlayerManager {

    private static final PlayerManager instance = new PlayerManager();

    private Player player;


    public static PlayerManager getInstance()
    {
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
