package lab2.algorithm;

import lab2.model.Node;
import lab2.model.Symbol;
import utils.AlgorithmCoder;

import java.util.*;

public class ShannonFanoCoder implements AlgorithmCoder{

    @Override
    public Node createCode(List<Symbol> symbols) {
        final Node root = new Node();
        symbols.sort(Comparator.comparingDouble(Symbol::probability).reversed());
        recursiveCoding(symbols, 0, symbols.size(), root);
        return root;
    }

    private void recursiveCoding(List<Symbol> symbols, int start, int end, Node node) {
        if (end - start <= 1) {
            Symbol symbol = symbols.get(start);
            if(symbol.code().length() == 0) symbol.add("0");
            node.setValue(symbol.letter());
            return;
        }
        var dividerIndex = Divider.getBestDiff(symbols, start, end);
        for (int i = start; i < dividerIndex; i++) {
            symbols.get(i).add("0");
        }
        for (int i = dividerIndex; i < end; i++) {
            symbols.get(i).add("1");
        }
        node.setLeft(new Node());
        node.setRight(new Node());
        recursiveCoding(symbols, start, dividerIndex, node.getLeft());
        recursiveCoding(symbols, dividerIndex, end, node.getRight());
    }

    @Override
    public String getName() {
        return "Shannon–Fano";
    }
}
