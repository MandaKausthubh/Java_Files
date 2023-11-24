import java.util.Queue;
import java.util.LinkedList;


class Tree {

    private int data;
    private Tree LeftChild;
    private Tree RightChild;

    Tree(int data, Tree Left, Tree Right) {
        this.data = data;
        this.RightChild = Right;
        this.LeftChild = Left;
    }

    public void PreOrder() {
        if(this != null){
            System.out.print(this.data);
            System.out.print(' ');
            if(this.LeftChild != null) this.LeftChild.PreOrder();
            if(this.RightChild != null) this.RightChild.PreOrder();
        }
    }

    public void inOrder() {
        if(this != null) {
            if(this.LeftChild != null) this.LeftChild.inOrder();
            System.out.println(this.data + ' ');
            if(this.RightChild != null) this.RightChild.inOrder();
        }
    }

    public void postOrder() {
        if(this != null) {
            if(this.LeftChild != null) this.LeftChild.inOrder();
            if(this.RightChild != null) this.RightChild.inOrder();
            System.out.println(this.data + ' ');
        }
    }

    public int getSize() {
        if(this != null) return 1 + this.LeftChild.getSize() + this.RightChild.getSize();
        return 0;
    }

    public int getHeight() {
        if(this != null) {
            return 1 + Math.max(this.LeftChild.getHeight(), this.RightChild.getHeight());
        }
        return 0;
    }

    public int findMax() {
        if(this.LeftChild == null && this.RightChild == null) {
            return this.data;
        }
        else if(this.LeftChild == null) {
            return Math.max(data, this.RightChild.findMax());
        }
        else if(this.RightChild == null) {
            return Math.max(data, this.LeftChild.findMax());
        }
        else{
            return Math.max(this.data, Math.max(this.LeftChild.findMax(), this.RightChild.findMax()));
        }
    }

    public int findMin() {
        if(this.LeftChild == null && this.RightChild == null) {
            return this.data;
        }
        else if(this.LeftChild == null) {
            return Math.min(data, this.RightChild.findMin());
        }
        else if(this.RightChild == null) {
            return Math.min(data, this.LeftChild.findMin());
        }
        else{
            return Math.min(this.data, Math.min(this.LeftChild.findMin(), this.RightChild.findMin()));
        }
    }

    public boolean contain(int value) {
        if(this != null) {
            if(this.data == value) return true;
            else{
                return this.RightChild.contain(value) || this.LeftChild.contain(value) ;
            }
        }
        return false;
    }

    public void printLevelOrder()
    {
        Queue<Tree> queue = new LinkedList<Tree>();
        queue.add(this);
        while (!queue.isEmpty()) {
 
            // poll() removes the present head. 
            Tree tempNode = queue.poll();
            System.out.print(tempNode.data + " ");
 
            // Enqueue left child
            if (tempNode.LeftChild != null) {
                queue.add(tempNode.LeftChild);
            }
 
            // Enqueue right child
            if (tempNode.RightChild != null) {
                queue.add(tempNode.RightChild);
            }
        }
    }

    public void insert(int value) {
        if(this != null) {
            if(this.data > value) this.LeftChild.insert(value);
            if(this.data <= value) this.RightChild.insert(value);
        }
        //else this = new Tree(value, null, null);
    }
}



class MainTree {
    public static void main(String Args[]) {
        Tree Node1 = new Tree(1, null, null);
        Node1.insert(2);
        Node1.insert(3);
        Node1.insert(4);
        Node1.insert(5);

        System.out.println(Node1.findMax());
        System.out.println(Node1.findMin());

        Node1.PreOrder();
        Node1.inOrder();
        Node1.postOrder();
        System.out.println();
    }
}
