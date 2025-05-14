package io.dgj7.jod.testonly.model.btree;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Objects;

/**
 * <p>
 * Simple binary tree node implementation.
 * </p>
 */
public class BNode<T extends Comparable<T>> {
    @Getter
    @Setter
    private T value;
    @Getter
    @Setter
    private BNode<T> left;
    @Getter
    @Setter
    private BNode<T> right;

    private final Comparator<T> comparator;

    protected BNode(final Comparator<T> pComparator) {
        this.comparator = Objects.requireNonNull(pComparator, "Comparator<T> is null");
    }

    public int size() {
        final int leftSize = left == null ? 0 : left.size();
        final int rightSize = right == null ? 0 : right.size();
        return leftSize + rightSize + 1;
    }

    public boolean isEmpty() {
        return left.isEmpty() && right.isEmpty() && value == null;
    }

    public boolean contains(final Object object) {
        return (value != null && value.equals(object))
                || (left != null && left.contains(object))
                || (right != null && right.contains(object));
    }

    public void clear() {
        if (left != null) {
            left.clear();
        }
        left = null;

        if (right != null) {
            right.clear();
        }
        right = null;

        value = null;
    }

    public boolean add(final T next) {
        Objects.requireNonNull(next, "T is null");

        if (value == null) {
            if (left != null || right != null) {
                throw new IllegalStateException("expect left & right to be null");
            }
            value = next;
            return true;
        } else if (next.compareTo(value) < 0) {
            if (left == null) {
                left = new BNode<>(comparator);
            }
            return left.add(next);
        } else if (next.compareTo(value) > 0) {
            if (right == null) {
                right =  new BNode<>(comparator);
            }
            return right.add(next);
        } else {
            return false;
        }
    }
}
