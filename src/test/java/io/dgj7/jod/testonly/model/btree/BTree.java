package io.dgj7.jod.testonly.model.btree;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class BTree<T extends Comparable<T>> implements SortedSet<T> {
    private final Comparator<T> forward = Comparable::compareTo;
    private final Comparator<T> reversed = forward.reversed();

    private final BNode<T> root;
    private final Comparator<T> comparator; // todo: does this class need this?

    public BTree() {
        this(Comparable::compareTo);
    }

    public BTree(final Comparator<T> pComparator) {
        this.comparator = Objects.requireNonNull(pComparator, "Comparator<T> is null");
        root = new BNode<>(comparator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return root.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return root.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(final Object object) {
        return root.contains(object);
    }

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
    public boolean add(final T t) {
        return root.add(t);
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
    public boolean addAll(final Collection<? extends T> collection) {
        AtomicBoolean success = new AtomicBoolean(true);
        collection.forEach(elem -> {
            final boolean next = this.add(elem);
            success.set(success.get() && next);
        });
        return success.get();
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
    public void clear() {
        root.clear();
    }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @Override
    public SortedSet<T> subSet(final T fromElement, final T toElement) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public SortedSet<T> headSet(final T toElement) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public SortedSet<T> tailSet(final T fromElement) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public T first() {
        BNode<T> leftMost = root;
        while (leftMost.getLeft() != null) {
            leftMost = leftMost.getLeft();
        }
        return leftMost.getValue();
    }

    @Override
    public T last() {
        BNode<T> rightMost = root;
        while (rightMost.getRight() != null) {
            rightMost = rightMost.getRight();
        }
        return rightMost.getValue();
    }
}
