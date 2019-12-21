public class TreeNode implements Comparable<TreeNode>
{
    public int Freq;
    public Discriptor discriptor;
    public TreeNode Lchild = null;
    public TreeNode Rchild = null;

    public TreeNode (int frq, Discriptor dis, TreeNode lft, TreeNode rgt)
    {
        this.Freq = frq;
        this.discriptor = dis;
        this.Lchild = lft;
        this.Rchild = rgt;
    }

    @Override
    public int compareTo(TreeNode treeNode)
    {
        if (this.Freq == treeNode.Freq) return 0;
        else if (this.Freq > treeNode.Freq) return 1;
        else return -1;
    }
}
