package irl.fw.physics.bodies;

import irl.fw.physics.events.UpdateBody;
import rx.Observable;

/**
 * TODO bigpopakap Javadoc this class
 *
 * @author bigpopakap
 * @since 10/29/15
 */
public interface Body {

    Observable<UpdateBody> updates(String bodyId);

}
