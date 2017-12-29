package me.lotabout.processor.model;

import javax.lang.model.element.VariableElement;

public class FieldEntryImpl implements FieldEntry {
    private VariableElement self;

    public FieldEntryImpl(VariableElement self) {
        this.self = self;
    }

    @Override
    public String getName() {
        return self.getSimpleName().toString();
    }

    @Override
    public TypeEntry getType() {
        return EntryFactory.of(self.asType());
    }

    @Override
    public String getSetter() {
        return "set" + this.getName().substring(0, 1).toUpperCase() + this.getName().substring(1);
    }

    @Override
    public String getGetter() {
        String prefix = this.getType().isBoolean() ? "is" : "get";
        return prefix + this.getName().substring(0, 1).toUpperCase() + this.getName().substring(1);
    }
}