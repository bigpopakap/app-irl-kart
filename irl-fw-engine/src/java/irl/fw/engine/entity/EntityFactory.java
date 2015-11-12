package irl.fw.engine.entity;

import irl.util.universe.UniverseElementFactory;

/**
 * TODO bigpopakap Javadoc this class
 *
 * @author bigpopakap
 * @since 11/11/15
 */
public interface EntityFactory<T extends Entity> extends UniverseElementFactory<T> {

    @Override
    default T create(String id) {
        return create(new EntityId(id));
    }

    T create(EntityId engineId);

}
