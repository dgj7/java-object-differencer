package io.dgj7.jod.testonly.model.btree.set;

import io.dgj7.jod.testonly.model.btree.notset.BTree;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;

/**
 * {@link BTree} that also implements {@link SortedSet}.
 */
public class BTreeSortedSet<T extends Comparable<T>> extends BTree<T> implements SortedSet<T> {
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                throw new UnsupportedOperationException("not yet implemented");
            }

            @Override
            public T next() {
                throw new UnsupportedOperationException("not yet implemented");
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T1> T1[] toArray(final T1[] a) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(final Object object) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(final Collection<?> collection) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean retainAll(final Collection<?> collection) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(final Collection<?> collection) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedSet<T> subSet(final T fromElement, final T toElement) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedSet<T> headSet(final T toElement) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedSet<T> tailSet(final T fromElement) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
