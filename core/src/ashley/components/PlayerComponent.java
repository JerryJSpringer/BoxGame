package ashley.components;

import com.badlogic.ashley.core.Component;

/**
 * A component that is used as a flag for player controlled entities under Ashley ECS.
 * Additionally holds a player ID that can differentiate between different characters.
 *
 * @author Jerry Springer
 * @version 02 09 2019
 */
public class PlayerComponent implements Component {

	public int playerId;

	public PlayerComponent(final int playerId) {
		this.playerId = playerId;
	}
}
