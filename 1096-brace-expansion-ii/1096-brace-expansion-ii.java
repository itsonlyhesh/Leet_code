import java.util.*;

class Solution {
    private int idx = 0;

    public List<String> braceExpansionII(String expression) {
        idx = 0;
        Set<String> resSet = parseExpr(expression);
        List<String> result = new ArrayList<>(resSet);
        Collections.sort(result);
        return result;
    }

    // Parses an expression consisting of comma-separated terms (Union)
    private Set<String> parseExpr(String s) {
        Set<String> unionSet = new TreeSet<>();
        
        while (idx < s.length() && s.charAt(idx) != '}') {
            Set<String> termSet = parseTerm(s);
            unionSet.addAll(termSet);
            
            if (idx < s.length() && s.charAt(idx) == ',') {
                idx++; // skip ','
            }
        }
        
        return unionSet;
    }

    // Parses adjacent factors concatenated together (Cartesian Product)
    private Set<String> parseTerm(String s) {
        Set<String> productSet = new HashSet<>();
        productSet.add(""); // Identity for concatenation

        while (idx < s.length() && s.charAt(idx) != '}' && s.charAt(idx) != ',') {
            Set<String> factorSet = parseFactor(s);
            
            // Cartesian product of productSet and factorSet
            Set<String> nextProduct = new HashSet<>();
            for (String a : productSet) {
                for (String b : factorSet) {
                    nextProduct.add(a + b);
                }
            }
            productSet = nextProduct;
        }

        return productSet;
    }

    // Parses a single atomic unit: either a lowercase string or a '{...}' group
    private Set<String> parseFactor(String s) {
        Set<String> res = new HashSet<>();
        
        if (s.charAt(idx) == '{') {
            idx++; // skip '{'
            res = parseExpr(s);
            idx++; // skip '}'
        } else {
            // Read consecutive lowercase characters
            StringBuilder sb = new StringBuilder();
            while (idx < s.length() && Character.isLowerCase(s.charAt(idx))) {
                sb.append(s.charAt(idx++));
            }
            res.add(sb.toString());
        }
        
        return res;
    }
}