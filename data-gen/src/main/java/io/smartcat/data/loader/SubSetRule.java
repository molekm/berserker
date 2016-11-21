package io.smartcat.data.loader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Rule for creating a set of random values that is a subset of passed allowed values.
 *
 * @param <T>
 */
public class SubSetRule<T> implements Rule<Collection<T>> {

    private boolean exclusive;
    private final Collection<T> values = new HashSet<>();

    private SubSetRule() {
    }

    public static <T> SubSetRule<T> withValues(Collection<T> allowedValues) {
        SubSetRule<T> subSetRule = new SubSetRule<>();
        subSetRule.values.addAll(allowedValues);
        return subSetRule;
    }

    public static <T> SubSetRule<T> withValuesX(Collection<T> allowedValues) {
        SubSetRule<T> subSetRule = new SubSetRule<>();
        subSetRule.values.addAll(allowedValues);
        subSetRule.exclusive = true;
        return subSetRule;
    }

    @Override
    public boolean isExclusive() {
        return this.exclusive;
    }

    @Override
    public Rule<Collection<T>> recalculatePrecedance(Rule<Collection<T>> exclusiveRule) {
        return null;
    }

    @Override
    public Collection<T> getRandomAllowedValue() {
        return getRandomSubset(values);
    }

    private Collection<T> getRandomSubset(Collection<T> values) {
        int randomSize = ThreadLocalRandom.current().nextInt(0, values.size());

        List<T> list = new ArrayList<>(values);
        Collections.shuffle(list);
//        Collection<T> randomSubset = new HashSet(list.subList(0, randomSize));
//
//        return randomSubset;
        return list;
    }

}