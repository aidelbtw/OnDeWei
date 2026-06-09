public class MenuBST {
    private FoodBSTNode root;

    public MenuBST() {
        this.root = null;
    }

    public void insert(FoodItem food) {
        root = insertRec(root, food);
    }

    private FoodBSTNode insertRec(FoodBSTNode node, FoodItem food) {
        if (node == null) {
            System.out.println("'" + food.getName() + "' added to menu.");
            return new FoodBSTNode(food);
        }

        int cmp = food.getName().compareToIgnoreCase(node.food.getName());

        if (cmp < 0) {
            node.left = insertRec(node.left, food);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, food);
        } else {
            System.out.println("'" + food.getName() + "' already exists in the menu.");
        }

        return node;
    }

    public FoodItem search(String name) {
        return searchRec(root, name);
    }

    private FoodItem searchRec(FoodBSTNode node, String name) {
        if (node == null) return null;

        int cmp = name.compareToIgnoreCase(node.food.getName());

        if (cmp == 0) {
            return node.food;
        } else if (cmp < 0) {
            return searchRec(node.left, name);
        } else {
            return searchRec(node.right, name);
        }
    }

    public void displayMenuInOrder() {
        if (root == null) {
            System.out.println("(Menu is empty)");
            return;
        }
        System.out.printf("%-20s | %-15s | %s%n", "Food Name", "Category", "Price");
        inOrderRec(root);
    }

    private void inOrderRec(FoodBSTNode node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.println("  " + node.food.toString());
        inOrderRec(node.right);
    }

    public boolean delete(String name) {
        if (root == null) return false;
        boolean[] deleted = {false};
        root = deleteRec(root, name, deleted);
        return deleted[0];
    }

    private FoodBSTNode deleteRec(FoodBSTNode node, String name, boolean[] deleted) {
        if (node == null) return null;

        int cmp = name.compareToIgnoreCase(node.food.getName());

        if (cmp < 0) {
            node.left = deleteRec(node.left, name, deleted);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, name, deleted);
        } else {
            deleted[0] = true;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            FoodBSTNode successor = findMin(node.right);
            node.food = successor.food;
            node.right = deleteRec(node.right, successor.food.getName(), deleted);
        }
        return node;
    }

    private FoodBSTNode findMin(FoodBSTNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
