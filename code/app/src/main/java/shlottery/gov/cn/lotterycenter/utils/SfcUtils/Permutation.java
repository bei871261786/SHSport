package shlottery.gov.cn.lotterycenter.utils.SfcUtils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-11-11-0011 09:54
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class Permutation<E> implements Iterable<List<E>> {
    public static final PermutationAlgorithm DEFAULT_PERMUTATION_ALGORITHM =
            AlgorithmVER01.INSTANCE;
    public static final CombinationAlgorithm DEFAULT_COMBINATION_ALGORITHM =
            Combination.DEFAULT_ALGORITHM;

    public static <E> Permutation<E> of(Collection<E> elements) {
        return of(elements, elements.size());
    }

    public static <E> Permutation<E> of(Collection<E> elements,
                                        int numberToPick) {

        return of(elements, numberToPick, DEFAULT_PERMUTATION_ALGORITHM);
    }

    public static <E> Permutation<E> of(Collection<E> elements,
                                        PermutationAlgorithm pAlgorithm) {
        return of(elements, elements.size(), pAlgorithm);
    }

    public static <E> Permutation<E> of(Collection<E> elements,
                                        int numberToPick,
                                        PermutationAlgorithm pAlgorithm) {

        return of(elements, numberToPick, pAlgorithm, DEFAULT_COMBINATION_ALGORITHM);
    }

    public static <E> Permutation<E> of(Collection<E> elements,
                                        int numberToPick,
                                        PermutationAlgorithm pAlgorithm,
                                        CombinationAlgorithm cAlgorithm) {
        if (elements == null)
            throw new NullPointerException();
        if (pAlgorithm == null)
            throw new NullPointerException();
        if (elements.size() > pAlgorithm.getMaxSupportedSize())
            throw new IllegalArgumentException("Element collection size not supported by the permutation algorithm.");
        return new Permutation<E>(elements, numberToPick, pAlgorithm, cAlgorithm);
    }

    @SuppressWarnings("unchecked")
    private E[] elements, picked;
    private PermutationAlgorithm pAlgorithm;
    private CombinationAlgorithm cAlgorithm;
    private BigInteger cCount, pCount;
    private BigInteger count;
    private int numberToPick;

    private Permutation(Collection<E> elements,
                        int numberToPick,
                        PermutationAlgorithm pAlgorithm,
                        CombinationAlgorithm cAlgorithm) {

        assert elements != null;
        assert pAlgorithm != null;
        assert cAlgorithm != null;
        assert numberToPick >= 0;
        assert numberToPick <= elements.size();

        this.elements = (E[]) elements.toArray();
        this.picked = (E[]) new Object[numberToPick];
        this.pAlgorithm = pAlgorithm;
        this.cAlgorithm = cAlgorithm;
        this.numberToPick = numberToPick;

        this.cCount = this.cAlgorithm.getCombinationCount(this.elements.length,
                this.numberToPick);
        this.pCount = this.pAlgorithm.getPermutationCount(this.numberToPick);
        this.count = this.cCount.multiply(this.pCount);
    }

    public BigInteger getPermutationCount() {

        return count;
    }

    public List<E> getPermutation(BigInteger ordinal) {

        if (ordinal == null)
            throw new NullPointerException();

        if (ordinal.compareTo(ZERO) < 0 || ordinal.compareTo(count) >= 0)
            throw new IllegalArgumentException(
                    "Ordinal value out of range : " + ordinal);

        if (numberToPick == elements.length) {

            System.arraycopy(elements, 0, picked, 0, elements.length);
        } else {

            cAlgorithm.fetchCombination(elements, picked, ordinal.divide(pCount));
            ordinal = ordinal.mod(pCount);
        }
        pAlgorithm.fetchPermutation(picked, ordinal);
        return Arrays.asList(picked);
    }

    @Override
    public Iterator<List<E>> iterator() {
        return this.new OrdinalIterator();
    }

    private class OrdinalIterator implements Iterator<List<E>> {

        private BigInteger ordinal;

        private OrdinalIterator() {

            this.ordinal = ZERO;
        }

        @Override
        public boolean hasNext() {

            return ordinal.compareTo(count) < 0;
        }

        @Override
        public List<E> next() {

            List<E> result = getPermutation(ordinal);
            ordinal = ordinal.add(ONE);
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static enum AlgorithmVER01 implements PermutationAlgorithm {

        INSTANCE;

        @Override
        public int getMaxSupportedSize() {

            return MAX_SUPPORT;
        }

        @Override
        public BigInteger getPermutationCount(int numberOfElements) {

            if (numberOfElements < 0)
                throw new IllegalArgumentException(
                        "Invalid number of elements : " + numberOfElements);

            if (numberOfElements > getMaxSupportedSize())
                throw new IllegalArgumentException(
                        "Number of elements out of range : " + numberOfElements);

            return Calculator.factorial(numberOfElements);
        }

        @Override
        public void fetchPermutation(Object[] elements, BigInteger ordinal) {

            for (int i = 0; i < elements.length - 1; i++) {

                int left = elements.length - i - 1;
                BigInteger leftCount = Calculator.factorial(left);
                int curr = ordinal.divide(leftCount).intValue();
                ordinal = ordinal.mod(leftCount);
                if (curr > 0) {

                    Object temp = elements[curr + i];
                    for (int j = curr + i; j > i; j--)
                        elements[j] = elements[j - 1];
                    elements[i] = temp;
                }
            }
        }

        private static final int MAX_SUPPORT = 1024;
    }
}

