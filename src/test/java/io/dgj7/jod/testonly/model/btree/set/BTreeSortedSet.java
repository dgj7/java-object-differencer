package io.dgj7.jod.testonly.model.btree.set;

import io.dgj7.jod.testonly.model.btree.notset.BNode;
import io.dgj7.jod.testonly.model.btree.notset.BTree;

import java.util.*;

/**
 * {@link BTree} that also implements {@link SortedSet}.
 */
public class BTreeSortedSet<T extends Comparable<T>> extends BTree<T> implements SortedSet<T> {
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return new BTreeInOrderIterator<>(this.root);
    }

    /**
     * <p>
     * {@link Iterator} of {@link BTree}, and descendants.
     * </p>
     * <p>
     * Performs in-order traversal.
     * </p>
     */
    private class BTreeInOrderIterator<T1 extends Comparable<T1>> implements Iterator<T1> {
        private final Iterator<T1> iterator;

        public BTreeInOrderIterator(final BNode<T1> pRoot) {
            final List<T1> cheater = new LinkedList<>();
            recurse(pRoot, cheater);
            iterator = cheater.iterator();
        }

        private void recurse(final BNode<T1> node, final List<T1> list) {
            if (node != null) {
                recurse(node.getLeft(), list);
                if (node.getValue() != null) {
                    list.add(node.getValue());
                }
                recurse(node.getRight(), list);
            }
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T1 next() {
            return iterator.next();
        }
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

    @Override
    public String toString() {
        final Iterator<T> iter = iterator();
        final StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            if (!sb.isEmpty()) {
                sb.append(",");
            }
            sb.append(iter.next());
        }
        return "BTreeSortedSet[" + sb.toString() + "]";
    }
}
