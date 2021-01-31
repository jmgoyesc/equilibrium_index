package com.github.jmgoyesc.equilibriumindex;

class EquilibriumIndex {

    private final int[] elements;
    private final long[] sumFromTopToBottom;
    private final long[] sumFromBottomToTop;

    EquilibriumIndex(int[] elements) {
        this.elements = elements;
        this.sumFromTopToBottom = new long[elements.length];
        this.sumFromBottomToTop = new long[elements.length];
    }

    int find() {
        sumAll();
        subtractEquilibriumIndex();
        return getOrDefault();
    }

    private void sumAll() {
        for (int i = 0, j = elements.length - 1, k = 0; i < elements.length; i++, j--,k = 1) {
            sumFromBottomToTop[i] = sumFromBottomToTop[i - k] + elements[i];
            sumFromTopToBottom[j] = sumFromTopToBottom[j + k] + elements[j];
        }
    }

    private void subtractEquilibriumIndex() {
        for (int i = 0; i < elements.length; i++) {
            sumFromBottomToTop[i] -= elements[i];
            sumFromTopToBottom[i] -= elements[i];
        }
    }

    private int getOrDefault() {
        for (int i = 0; i < elements.length; i++) {
            if (sumFromBottomToTop[i] == sumFromTopToBottom[i]) {
                return i;
            }
        }
        return -1;
    }

}
