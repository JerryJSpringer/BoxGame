package ashley.components;

import com.badlogic.ashley.core.Component;
import com.mygdx.game.managers.InputHandler;

/**
 * This class currently contains no functionality as it is to be used as a flag for
 * player controlled entities under Ashley ECS.
 *
 * @author Jerry Springer
 * @version 02 09 2019
 */
public class PlayerComponent implements Component {

	public InputHandler input;

	public PlayerComponent(InputHandler input) {

		this.input = input;
	}
}
