package RC4;
import java.util.*;
public class rc4 
{

    public static void swap(int[] state,int i, int j)
    {
      int temp=state[i];
      state[i]=state[j];
      state[j]=temp;
    }

    public static String encrypt(String message, String key)
    {
        int[] state = new int[256];
        for(int i=0;i<256;i++)
        {
            state[i]=i;
        }
        int j=0;
        for(int i=0;i<256;i++)
        {
            j=(j+state[i]+key.charAt(i%key.length()))%256;
            swap(state,i,j);
        }
        StringBuilder output = new StringBuilder();
        int i=0;
        j=0;
        for(char c : message.toCharArray())
        {
            i=(i+1)%256;
            j=(j+state[i])%256;
            swap(state, i, j);
            int keystream=state[(state[i]+state[j])%256];
            output.append((char)(c^keystream));
        }
        return output.toString();
    }
    public static void main(String[] args) 
    {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the key : ");
      String key = sc.nextLine();
      System.out.println("Enter the text : ");
      String message = sc.nextLine();
      String EncryptedMessage=encrypt(message,key);    
      String DecryptedMessage=encrypt(EncryptedMessage, key);
      System.out.println("Encrypted message :"+EncryptedMessage);
      System.out.println("Decrypted message : "+DecryptedMessage);
      sc.close();
    }
}
