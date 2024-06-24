import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

class Main {
    static int N;
    static Map<String, Node> map;

    static public class Node {
        String left;
        String right;

        public Node(String left, String right) {
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            String c = st.nextToken();
            map.put(a, new Node(b, c));
        }

        preorder("A");
        System.out.println();
        inorder("A");
        System.out.println();
        postorder("A");
    }
    private static void preorder(String root) {
        if (Objects.equals(root, ".")) {
            return;
        }
        Node node = map.get(root);
        System.out.print(root);
        preorder(node.left);
        preorder(node.right);
    }

    private static void inorder(String root) {
        if (Objects.equals(root, ".")) {
            return;
        }
        Node node = map.get(root);
        inorder(node.left);
        System.out.print(root);
        if (!Objects.equals(node.right, ".")) {
            inorder(node.right);
        }
    }

    private static void postorder(String root) {
        if (Objects.equals(root, ".")) {
            return;
        }
        Node node = map.get(root);
        postorder(node.left);
        postorder(node.right);
        System.out.print(root);
    }
}
