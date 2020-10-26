package game.display;

import java.io.IOException;

public class ObjectDisplayGrid {
    private int gameHeight;
    private int width;
    private int topHeight;
    private int bottomHeight;

    public ObjectDisplayGrid(int _gameHeight, int _width, int _topHeight, int _bottomHeight) {
        gameHeight = _gameHeight;
        width = _width;
        topHeight = _topHeight;
        bottomHeight = _bottomHeight;
    }

    public ObjectDisplayGrid getObjectDisplayGrid(int _gameHeight, int _width, int _topHeight, int _bottomHeight) {
        if (this.gameHeight == _gameHeight && this.width == _width && this.topHeight == _topHeight && this.bottomHeight == _bottomHeight) {
            return this;
        }
        ObjectDisplayGrid grid = new ObjectDisplayGrid(_gameHeight, _width, _topHeight, _bottomHeight);
        return grid;
    }

    public void setTopMessageHeight(int _topHeight) {
        this.topHeight = _topHeight;
    }
}
