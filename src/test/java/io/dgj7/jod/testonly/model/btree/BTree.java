package io.dgj7.jod.testonly.model.btree;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class BTree<T extends Comparable<T>> {
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
    public int size() {
        return root.size();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return root.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(final Object object) {
        return root.contains(object);
    }

    /**
     * {@inheritDoc}
     */
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
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    /**
     * {@inheritDoc}
     */
    public <T1> T1[] toArray(final T1[] a) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    public boolean add(final T t) {
        return root.add(t);
    }

    /**
     * {@inheritDoc}
     */
    public boolean remove(final Object object) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsAll(final Collection<?> collection) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
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
    public boolean retainAll(final Collection<?> collection) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    public boolean removeAll(final Collection<?> collection) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        root.clear();
    }

    public Comparator<? super T> comparator() {
        return comparator;
    }

    public SortedSet<T> subSet(final T fromElement, final T toElement) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public SortedSet<T> headSet(final T toElement) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public SortedSet<T> tailSet(final T fromElement) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public T first() {
        BNode<T> leftMost = root;
        while (leftMost.getLeft() != null) {
            leftMost = leftMost.getLeft();
        }
        return leftMost.getValue();
    }

    public T last() {
        BNode<T> rightMost = root;
        while (rightMost.getRight() != null) {
            rightMost = rightMost.getRight();
        }
        return rightMost.getValue();
    }
}
