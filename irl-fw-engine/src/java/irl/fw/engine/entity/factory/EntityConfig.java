package irl.fw.engine.entity.factory;

import irl.fw.engine.entity.EntityId;

/**
 * TODO bigpopakap Javadoc this class
 *
 * @author bigpopakap
 * @since 11/14/15
 */
public class EntityConfig {

    private EntityId id = null;
    private EntityDisplayConfig displayConfig = new EntityDisplayConfig();

    public EntityConfig() {
        //do nothing... this is sort of a builder class
    }

    public EntityId getId() {
        return id;
    }

    public EntityConfig setId(EntityId id) {
        if (getId() != null) {
            throw new UnsupportedOperationException("Can't re-set the ID");
        } else {
            this.id = id;
        }
        return this;
    }

    public EntityDisplayConfig getDisplayConfig() {
        return displayConfig;
    }

    public EntityConfig display(EntityDisplayConfig displayConfig) {
        this.displayConfig = displayConfig;
        return this;
    }

}
