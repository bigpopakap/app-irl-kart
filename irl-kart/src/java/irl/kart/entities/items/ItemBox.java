package irl.kart.entities.items;

import irl.fw.engine.entity.EntityId;
import irl.fw.engine.entity.VirtualEntity;
import irl.fw.engine.entity.state.EntityState;
import irl.fw.engine.events.EngineEvent;
import irl.fw.engine.events.RemoveEntity;
import irl.fw.engine.geometry.Angle;
import irl.fw.engine.geometry.ImmutableShape;
import irl.util.callbacks.Callback;
import irl.util.callbacks.Callbacks;
import irl.util.reactiveio.Pipe;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * TODO bigpopakap Javadoc this class
 *
 * @author bigpopakap
 * @since 11/11/15
 */
public class ItemBox extends VirtualEntity {

    public static final Angle INIT_ROT = Angle.deg(45);
    public static final Angle ROTATION_SPEED = Angle.rad(-Math.PI);
    public static final ImmutableShape SHAPE = new ImmutableShape(
        ImmutableShape.Type.RECTANGLE,
        new Rectangle2D.Double(0, 0, 15, 15)
    );

    private final Callbacks onRemove;
    private final ArrayList<Item> availableItems;
    private final Pipe<EngineEvent> eventQueue;

    public ItemBox(EntityId engineId, EntityState initState,
                   Pipe<EngineEvent> eventQueue) {
        this(engineId, initState, eventQueue, null);
    }

    public ItemBox(EntityId engineId, EntityState initState,
                   Pipe<EngineEvent> eventQueue, Callback onRemove) {
        super(engineId, initState);

        availableItems = new ArrayList<>();
        this.eventQueue = eventQueue;
        availableItems.add(new ShellItem(this.eventQueue));

        this.onRemove = new Callbacks();
        this.onRemove.add(onRemove);
    }

    public Item getRandomItem() {
        int randIndex = (int) (availableItems.size() * Math.random());
        return availableItems.get(randIndex);
    }

    public void remove() {
        eventQueue.mergeIn(new RemoveEntity(getEngineId()));
        onRemove.run();
    }

}
