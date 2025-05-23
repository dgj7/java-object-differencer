package io.dgj7.jod.testonly.model.binarytree.notset;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Comparator;
import java.util.Objects;

/**
 * <p>
 * Simple binary tree node implementation.
 * </p>
 */
public class BinaryNode<T extends Comparable<T>> {
    /* data storage */
    @Getter
    @Setter
    private T value;
    @Getter
    @Setter
    private BinaryNode<T> left;
    @Getter
    @Setter
    private BinaryNode<T> right;

    /* traversal */
    @Getter
    private BinaryNode<T> parent;

    /* ordering */
    private final Comparator<T> comparator;

    protected BinaryNode(final Comparator<T> pComparator) {
        this.comparator = Objects.requireNonNull(pComparator, "Comparator<T> is null");
    }

    public int size() {
        final int leftSize = left == null ? 0 : left.size();
        final int rightSize = right == null ? 0 : right.size();
        return leftSize + rightSize + 1;
    }

    public boolean isEmpty() {
        return (left == null || left.isEmpty())
                && (right == null || right.isEmpty())
                && value == null;
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

        parent = null;
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
                left = new BinaryNode<>(comparator);
                left.parent = this;
            }
            return left.add(next);
        } else if (next.compareTo(value) > 0) {
            if (right == null) {
                right = new BinaryNode<>(comparator);
                right.parent = this;
            }
            return right.add(next);
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof BinaryNode<?> other) {
            return new EqualsBuilder()
                    .append(value, other.value)
                    .append(left, other.left)
                    .append(right, other.right)
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return "BNode[" + recurseString(this) + "]";
    }

    private String recurseString(final BinaryNode<?> node) {
        if (node == null) {
            return "";
        } else {
            final String leftStr = recurseString(node.left);
            final String rightStr = recurseString(node.right);
            return (StringUtils.isBlank(leftStr) ? "" : leftStr + ",")
                    + node.value
                    + (StringUtils.isBlank(rightStr) ? "" : "," + rightStr);
        }
    }
}
