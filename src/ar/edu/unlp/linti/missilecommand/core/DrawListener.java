package ar.edu.unlp.linti.missilecommand.core;

import java.util.List;

public interface DrawListener
{
	void drawEvent(List<GameObject> gameObjects, int level, Score score);
	void drawNextLevelEvent(List<GameObject> gameObjects, int level, Score score);
	void drawGameOverEvent(List<GameObject> gameObjects, int level, Score score);
	void showMainScreen(List<GameObject> gameObjects, int level, Score score);
}
