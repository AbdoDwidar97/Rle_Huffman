import java.util.ArrayList;

public class Main
{
    public static void main (String [] args)
    {
        /*TableBuilder tableBuilder = new TableBuilder("-2 0 0 2 0 0 3 2 0 1 0 0 -2 0 -1 0 0 1 0 0 -1");

        ArrayList<TableItem> table = tableBuilder.Build_table();

        System.out.println("Huffman Table : ");
        for (TableItem itr : table) System.out.println(itr.toString());
        System.out.println("==========================================");

        ArrayList<Discriptor> discriptors = tableBuilder.getDiscriptors();
        ArrayList<Integer> additional_numbers = tableBuilder.getAdditional_Numbers();

        for (int i=0; i < discriptors.size(); i++)
        {
            System.out.println(discriptors.get(i).toString() + " --> " + additional_numbers.get(i));
        }*/


        /*String Data = "-2 0 0 2 0 0 3 2 0 1 0 0 -2 0 -1 0 0 1 0 0 -1 0 0 0 0 0 0 9";
        Compression compression = new Compression(Data);
        compression.compress();
        String compressed = compression.getCompressedData();
        System.out.println(compressed);

        Decompression decompression = new Decompression();
        decompression.decompress();
        String decom_data = decompression.getDecompressed_Data();
        decom_data = decom_data.trim();
        System.out.println(decom_data);
        System.out.println(decom_data.equals(Data));*/

    }
}
