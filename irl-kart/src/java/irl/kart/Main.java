package irl.kart;

import irl.fw.engine.beacon.beacons.ArrowKeyBeacon;
import irl.fw.engine.events.AddBody;
import irl.fw.engine.events.PhysicalEvent;
import irl.fw.engine.simulation.Simulator;
import irl.fw.engine.world.World;
import irl.fw.engine.world.WorldBuilder;
import irl.kart.bodies.TestBody;

/**
 * TODO bigpopakap Javadoc this class
 *
 * @author bigpopakap
 * @since 11/1/15
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //create the karts and the beacon to talk to them
        final String KART_1_ID = "kart1";
        ArrowKeyBeacon beacon = new ArrowKeyBeacon(KART_1_ID);

        //create the world and engine
        World world = new WorldBuilder().build();
        Simulator<PhysicalEvent> worldSim = new Simulator<>(world);

        //TODO this should move somewhere more generic
        //set up a process to add new bodies whenever a new kart is detected
        worldSim.getEventQueue().mergeIn(
            beacon.updates()
                .distinct(update -> update.getExternalId())
                .map(update -> new AddBody(
                    new TestBody(update.getExternalId(), beacon),
                    update.getState()
                ))
        );

        //start the engine and beacons
        new Thread(worldSim).start();
        new Thread(beacon).start();

        //kill it after a little bit
        Thread.sleep(6000);
        beacon.stop();
        worldSim.stop();
    }

}
