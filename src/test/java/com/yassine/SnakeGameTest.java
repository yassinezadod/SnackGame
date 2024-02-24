package com.yassine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.yassine.Direction;

import java.awt.Point;

import org.junit.Test;

public class SnakeGameTest {
	@Test
    public void testDirectionChange() {
        SnakeGame game = new SnakeGame();

       
        assertEquals(Direction.RIGHT, game.getDirection());

      
        game.setDirection(Direction.UP);
        assertEquals(Direction.UP, game.getDirection());

      
        game.setDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, game.getDirection());

    
        game.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, game.getDirection());
    }
	

}
