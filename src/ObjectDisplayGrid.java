import java.io.IOException;

public class ObjectDisplayGrid {
    private int gameHeight = -1;
    private int width = -1;
    private int topHeight = -1;

//    public ObjectDisplayGrid(int _gameHeight, int _width, int _topHeight) {
//        gameHeight = _gameHeight;
//        width = _width;
//        topHeight = _topHeight;
//    }

    public void getObjectDisplayGrid(int _gameHeight, int _width, int _topHeight) {
        System.out.println("ObjectDisplayGrid gameHeight: " + _gameHeight + " width: " + _width + " topHeight: " + _topHeight);
//      if (this.gameHeight < 0 && this.width < 0 && this.topHeight < 0) {
//            ObjectDisplayGrid displayGrid = new ObjectDisplayGrid(_gameHeight, _width, _topHeight);
//            return displayGrid;
//        }
//
//        if (this.gameHeight != _gameHeight || this.width != _width || this.topHeight != _topHeight) {
//            throw new IOException("Existing object parameters don't match input parameters.");
//        }
//        return this;
    }

    public void setTopMessageHeight(int _topHeight) {
        this.topHeight = _topHeight;
    }
}
