package ashley.components;

import box2dLight.Light;
import com.badlogic.ashley.core.Component;

/**
 * @author Jerry Springer
 * @version 02 12 2019
 */
public class LightComponent implements Component {

	public Light light;

	public LightComponent(Light light) {
		this.light = light;
	}
}
