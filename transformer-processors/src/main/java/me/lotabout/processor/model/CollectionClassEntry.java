package me.lotabout.processor.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

import me.lotabout.processor.util.StringUtil;

class CollectionClassEntry extends AbstractClassEntry {
    public CollectionClassEntry(TypeMirror raw) {
        super(raw);
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public boolean isCollection() {
        return true;
    }

    @Override
    public boolean isMap() {
        return false;
    }

    @Override public boolean ableToTransformDirectlyTo(TypeEntry from) {
        return false;
    }

    @Override
    public boolean ableToTransformByTransformerTo(TypeEntry to) {
        if (!(to instanceof CollectionClassEntry)) {
            return false;
        }
        // check inner types could be transformed
        TypeEntry innerClassOfA = getBoundedClass(this).get(0);
        TypeEntry innerClassOfB = getBoundedClass((CollectionClassEntry)to).get(0);
        return innerClassOfA.ableToTransformByTransformerTo(innerClassOfB);
    }

    private static List<TypeEntry> getBoundedClass(CollectionClassEntry clazz) {
        return ((DeclaredType)clazz.getRaw()).getTypeArguments()
                .stream()
                .map(EntryFactory::of)
                .collect(Collectors.toList());
    }

    @Override public String transformTo(TypeEntry to, String value) {
        assert this.ableToTransformDirectlyTo(to) || this.ableToTransformByTransformerTo(to);

        String variable = StringUtil.decapitalize(this.getName());
        TypeEntry innerClassOfA = getBoundedClass(this).get(0);
        TypeEntry innerClassOfB = getBoundedClass((CollectionClassEntry)to).get(0);

        return value + ".stream().map(" + variable + "->"
                + innerClassOfA.transformTo(innerClassOfB, variable)
                + ").collect(Collectors.toList())";
    }
}