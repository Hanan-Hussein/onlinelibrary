package ke.hanan.onlinelibrarysystem.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Action implements EnumClass<String> {

    DISABLE("disable"),
    CREATION("creation");

    private String id;

    Action(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Action fromId(String id) {
        for (Action at : Action.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}