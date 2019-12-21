import java.util.ArrayList;
import java.util.PriorityQueue;

public class TableBuilder
{

    private String [] Data;
    private ArrayList<TableItem> table;
    private ArrayList<Discriptor> discriptors;
    private ArrayList<Integer> Additional_Numbers;
    private PriorityQueue<TreeNode> HuffmanTree;

    public TableBuilder(String data)
    {
        Data = data.split(" ");
        table = new ArrayList<>();
        discriptors = new ArrayList<>();
        Additional_Numbers = new ArrayList<>();
        HuffmanTree = new PriorityQueue<>();
    }

    ArrayList<Integer> getAdditional_Numbers () {return this.Additional_Numbers;}
    ArrayList<Discriptor> getDiscriptors () {return this.discriptors;}

    public ArrayList<TableItem> Build_table()
    {
        CreateDiscriptors();
        Initialize_HuffmanTree();
        TreeNode Root = Build_Tree();
        Traverse_Tree(Root, "", "");
        return this.table;
    }


    private void Traverse_Tree (TreeNode node, String code, String mark)
    {
        if (node == null) return;
        code += mark;
        if (node.Rchild == null)
        {
            TableItem tableItem = new TableItem(node.discriptor, code);
            this.table.add(tableItem);
        }
        Traverse_Tree(node.Lchild, code, "0");
        Traverse_Tree(node.Rchild, code, "1");
    }
    private void Initialize_HuffmanTree()
    {
        ArrayList<Discriptor> Unique_disc = get_unique_Discriptors();
        for(int i = 0; i< Unique_disc.size(); i++)
        {
            int dis_count = DiscCount(Unique_disc.get(i));
            HuffmanTree.add(new TreeNode(dis_count,Unique_disc.get(i), null, null));
        }
    }

    private int DiscCount (Discriptor dis)
    {
        int count = 0;
        for(int i=0; i< discriptors.size(); i++)
        {
            if(dis.getNumberOfZeroz() == discriptors.get(i).getNumberOfZeroz() &&
                dis.getNumberOfAdditionalBits() == discriptors.get(i).getNumberOfAdditionalBits()) count++;
        }
        return count;
    }


    private ArrayList<Discriptor> get_unique_Discriptors ()
    {
        ArrayList<Discriptor> unique_discs = new ArrayList<>();
        ArrayList<Discriptor> diss = this.discriptors;
        for (int i=0; i < diss.size(); i++)
        {
            Discriptor curr = diss.get(i);
            if (!isExist(curr, unique_discs))
            {
                unique_discs.add(curr);
            }
        }
        return unique_discs;
    }

    private boolean isExist (Discriptor disc, ArrayList<Discriptor> u)
    {
        for (Discriptor itr : u)
        {
            if (itr.getNumberOfZeroz() == disc.getNumberOfZeroz() && itr.getNumberOfAdditionalBits() == disc.getNumberOfAdditionalBits())
                return true;
        }
        return false;
    }


    private TreeNode Build_Tree ()
    {
        while (!HuffmanTree.isEmpty()){

            if(HuffmanTree.size() == 1) return HuffmanTree.poll();

            TreeNode lchld = HuffmanTree.poll();
            TreeNode rchld = HuffmanTree.poll();

            int prntFreq = lchld.Freq + rchld.Freq;
            TreeNode Prnt = new TreeNode(prntFreq,null,lchld,rchld);
            HuffmanTree.add(Prnt);
        }
        return null;
    }


    private void CreateDiscriptors ()
    {
        ArrayList<String> splitted_data = Split_Data();
        for (String discriptor_data : splitted_data)
        {
            Discriptor new_discriptor = new Discriptor();
            String [] discriptor_item = discriptor_data.split(" ");
            int NunZeroNumber = 0;

            if (discriptor_item.length > 1)
            {
                new_discriptor.setNumberOfZeroz(discriptor_item.length - 1);
                NunZeroNumber = Integer.parseInt(discriptor_item[discriptor_item.length - 1]);
                Additional_Numbers.add(NunZeroNumber);
                // here we should handle -ve problem ...
                if (NunZeroNumber < 0) NunZeroNumber *= -1;
                String AdditionalBits = Integer.toBinaryString(NunZeroNumber);
                new_discriptor.setNumberOfAdditionalBits(AdditionalBits.length());

            }else
            {
                new_discriptor.setNumberOfZeroz(0);
                NunZeroNumber = Integer.parseInt(discriptor_item[0]);
                Additional_Numbers.add(NunZeroNumber);
                if (NunZeroNumber < 0) NunZeroNumber *= -1;
                String AdditionalBits = Integer.toBinaryString(NunZeroNumber);
                new_discriptor.setNumberOfAdditionalBits(AdditionalBits.length());
            }

            discriptors.add(new_discriptor);
        }
    }

    private ArrayList<String> Split_Data ()
    {
        ArrayList<String> splittedData = new ArrayList<>();
        String bfr = "";
        for (String itr : Data)
        {
            bfr += itr + " ";
            if (!itr.equals("0"))
            {
                bfr = bfr.trim();
                splittedData.add(bfr);
                bfr = "";
            }
        }


        return splittedData;
    }
}