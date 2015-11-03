package irl.fw.beacon.beacons;

import irl.fw.beacon.Beacon;
import irl.fw.beacon.BeaconUpdate;
import irl.fw.shared.bodies.PhysicalState;
import irl.util.concurrent.Looper;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * TODO bigpopakap Javadoc this class
 *
 * @author bigpopakap
 * @since 11/1/15
 */
public class AsyncRandomBeacon extends Looper implements Beacon {

    private final String[] externalIds;
    private volatile Subject<BeaconUpdate, BeaconUpdate> positions;
    private volatile int iteration = 0;

    public AsyncRandomBeacon(String... externalIds) {
        this.externalIds = externalIds;
        positions = PublishSubject.<BeaconUpdate>create().toSerialized();
    }

    public Observable<BeaconUpdate> updates() {
        return positions;
    }

    @Override
    protected void loopIteration() {
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            //do nothing
        }

        for (String externalId : externalIds) {
            BeaconUpdate update = new BeaconUpdate(
                externalId,
                new PhysicalState(externalId + "-pos-" + iteration++)
            );

            //TODO remove
            System.out.println("Publishing update " + update);

            positions.onNext(update);
        }
    }

}
