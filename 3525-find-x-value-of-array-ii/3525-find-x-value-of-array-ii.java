class Solution {
    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            this.prod = 1;
            this.cnt = new int[k];
        }
    }

    private int k;
    private Node[] tree;

    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node res = new Node(k);
        res.prod = (left.prod * right.prod) % k;

        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
            int newRem = (left.prod * r) % k;
            res.cnt[newRem] += right.cnt[r];
        }
        return res;
    }

    private void build(int node, int l, int r, int[] nums) {
        tree[node] = new Node(k);
        if (l == r) {
            int val = nums[l] % k;
            tree[node].prod = val;
            tree[node].cnt[val] = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            int rem = val % k;
            tree[node].prod = rem;
            for (int i = 0; i < k; i++) tree[node].cnt[i] = 0;
            tree[node].cnt[rem] = 1;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, r, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node queryTree(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }
        int mid = (l + r) / 2;
        if (qr <= mid) {
            return queryTree(2 * node, l, mid, ql, qr);
        }
        if (ql > mid) {
            return queryTree(2 * node + 1, mid + 1, r, ql, qr);
        }
        Node leftRes = queryTree(2 * node, l, mid, ql, qr);
        Node rightRes = queryTree(2 * node + 1, mid + 1, r, ql, qr);
        return merge(leftRes, rightRes);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int qLen = queries.length;
        int[] ans = new int[qLen];

        for (int i = 0; i < qLen; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, val);

            Node resNode = queryTree(1, 0, n - 1, start, n - 1);
            ans[i] = resNode.cnt[x];
        }

        return ans;
    }
}