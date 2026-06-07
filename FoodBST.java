public class FoodBST {
    private FoodBSTNode root;

    public void insert(FoodItem food){
        root = insertRec(root, food);
    }

    private FoodBSTNode insertRec(FoodBSTNode node, FoodItem food){
        if (node == null){
            return new FoodBSTNode(food);
        }

        if (food.getName().compareToIgnoreCase(node.food.getName()) < 0){
            node.left = insertRec(node.left, food);
        } else {
            node.right = insertRec(node.right, food);
        }
        return node;
    }

    public FoodItem search(String name){
        return searchRec(root,name);
    }

    private FoodItem searchRec(FoodBSTNode node, String name){
        if (node == null){
            return null;
        }

        int cmp = name.compareToIgnoreCase(node.food.getName());

        if(cmp == 0) return node.food;

        if(cmp < 0) return searchRec(node.left, name);

        return searchRec(node.right, name);
    }

    public void displayMenu(){
        if (root == null){
            System.out.println("Menu is empty");
            return;
        }

        inorder(root);
    }

    public void inorder(FoodBSTNode node){
        if (node == null){
            return;
        }

        inorder(node.left);
        
        System.out.println(node.food);

        inorder(node.right);
    }
}
