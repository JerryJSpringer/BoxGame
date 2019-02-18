package ashley.components;

import com.badlogic.ashley.core.Component;

/**
 * Component representing the possible inputs from the player.
 *
 * @author Jerry Springer
 * @version 02 18 2019
 */
public class InputComponent implements Component {

    public boolean keyLeft,
            keyRight,
            keyUp,
            keyDown,
            keyReload,
            keyShoot,
            keyInventory;
}
