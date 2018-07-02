package Project2_PageReplacement;
import java.io.*;
import java.util.ArrayList;
public class ReplacementAlgorithms	
{
	  public static void main(String args[])throws IOException
	   {
	    BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
	   int ch;
	   int frames, pointer = 0, hit = 0, fault = 0,ref_len;
       int buffer[];
       int reference[];
       int mem_layout[][];
	do{
		System.out.println("Menu");
		System.out.println("1.FIFO");
		System.out.println("2.LRU");
		System.out.println("3.CPR");
		System.out.println("4.OPT");
		System.out.println("4.EXIT");
		System.out.println("ENTER YOUR CHOICE: ");
		 ch=Integer.parseInt(obj.readLine());
		switch(ch)
		{
		case 1:
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Please enter the number of Frames: ");
	        frames = Integer.parseInt(br.readLine());
	        
	        System.out.println("Please enter the length of the Reference string: ");
	        ref_len = Integer.parseInt(br.readLine());
	        
	        reference = new int[ref_len];
	        mem_layout = new int[ref_len][frames];
	        buffer = new int[frames];
	        for(int j = 0; j < frames; j++)
	                buffer[j] = -1;
	        
	        System.out.println("Please enter the reference string: ");
	        for(int i = 0; i < ref_len; i++)
	        {
	            reference[i] = Integer.parseInt(br.readLine());
	        }
	        System.out.println();
	        for(int i = 0; i < ref_len; i++)
	        {
	         int search = -1;
	         for(int j = 0; j < frames; j++)
	         {
	          if(buffer[j] == reference[i])
	          {
	           search = j;
	           hit++;
	           break;
	          } 
	         }
	         if(search == -1)
	         {
	          buffer[pointer] = reference[i];
	          fault++;
	          pointer++;
	          if(pointer == frames)
	           pointer = 0;
	         }
	            for(int j = 0; j < frames; j++)
	                mem_layout[i][j] = buffer[j];
	        }
	        
	        for(int i = 0; i < frames; i++)
	        {
	            for(int j = 0; j < ref_len; j++)
	                System.out.printf("%3d ",mem_layout[j][i]);
	            System.out.println();
	        }
	        
	        System.out.println("The number of Hits: " + hit);
	        System.out.println("Hit Ratio: " + (float)((float)hit/ref_len));
	        System.out.println("The number of Faults: " + fault);	
		case 2: 
			BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
	        int frames2,pointer2 = 0, hit2 = 0, fault2 = 0,ref_len2;
	        Boolean isFull = false;
	        int buffer2[];
	        ArrayList<Integer> stack = new ArrayList<Integer>();
	        int reference2[];
	        int mem_layout2[][];
	        
	        System.out.println("Please enter the number of Frames: ");
	        frames2 = Integer.parseInt(br1.readLine());
	        
	        System.out.println("Please enter the length of the Reference string: ");
	        ref_len2 = Integer.parseInt(br1.readLine());
	        
	        reference2 = new int[ref_len2];
	        mem_layout2 = new int[ref_len2][frames2];
	        buffer2 = new int[frames2];
	        for(int j = 0; j < frames2; j++)
	                buffer2[j] = -1;
	        
	        System.out.println("Please enter the reference string: ");
	        for(int i = 0; i < ref_len2; i++)
	        {
	            reference2[i] = Integer.parseInt(br1.readLine());
	        }
	        System.out.println();
	        for(int i = 0; i < ref_len2; i++)
	        {
	            if(stack.contains(reference2[i]))
	            {
	             stack.remove(stack.indexOf(reference2[i]));
	            }
	            stack.add(reference2[i]);
	            int search2 = -1;
	            for(int j = 0; j < frames2; j++)
	            {
	                if(buffer2[j] == reference2[i])
	                {
	                    search2 = j;
	                    hit2++;
	                    break;
	                }
	            }
	            if(search2 == -1)
	            {
	             if(isFull)
	             {
	              int min_loc2 = ref_len2;
	                    for(int j = 0; j < frames2; j++)
	                    {
	                     if(stack.contains(buffer2[j]))
	                        {
	                            int temp = stack.indexOf(buffer2[j]);
	                            if(temp < min_loc2)
	                            {
	                                min_loc2 = temp;
	                                pointer2 = j;
	                            }
	                        }
	                    }
	             }
	                buffer2[pointer2] = reference2[i];
	                fault2++;
	                pointer2++;
	                if(pointer2 == frames2)
	                {
	                 pointer2 = 0;
	                 isFull = true;
	                }
	            }
	            for(int j = 0; j < frames2; j++)
	                mem_layout2[i][j] = buffer2[j];
	        }
	        
	        for(int i = 0; i < frames2; i++)
	        {
	            for(int j = 0; j < ref_len2; j++)
	                System.out.printf("%3d ",mem_layout2[j][i]);
	            System.out.println();
	        }
	        
	        System.out.println("The number of Hits: " + hit2);
	        System.out.println("Hit Ratio: " + (float)((float)hit2/ref_len2));
	        System.out.println("The number of Faults: " + fault2);
	    
		case 3:
			BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
	        int frames1, pointer1 = 0, hit1 = 0, fault1 = 0,ref_len1;
	        int buffer1[][];
	        int reference1[];
	        int mem_layout1[][];
	  int used_layout1[][];
	        
	        System.out.println("Please enter the number of Frames: ");
	        frames1 = Integer.parseInt(br2.readLine());
	        
	        System.out.println("Please enter the length of the Reference string: ");
	        ref_len1 = Integer.parseInt(br2.readLine());
	        
	        reference1 = new int[ref_len1];
	        mem_layout1 = new int[ref_len1][frames1];
	  used_layout1 = new int[ref_len1][frames1];
	        buffer1 = new int[frames1][2];
	        for(int j = 0; j < frames1; j++)
	        {
	         buffer1[j][0] = -1;
	         buffer1[j][1] = 0;
	        }
	        System.out.println("Please enter the reference string: ");
	        for(int i = 0; i < ref_len1; i++)
	        {
	            reference1[i] = Integer.parseInt(br2.readLine());
	        }
	        System.out.println();
	        for(int i = 0; i < ref_len1; i++)
	        {
	         int search1 = -1;
	         for(int j = 0; j < frames1; j++)
	         {
	          if(buffer1[j][0] == reference1[i])
	          {
	           search1 = j;
	           hit1++;
	           buffer1[j][1] = 1;
	           break;
	          } 
	         }
	         if(search1 == -1)
	         {
	          
	          while(buffer1[pointer1][1] == 1)
	          {
	           buffer1[pointer1][1] = 0;
	           pointer1++;
	           if(pointer1 == frames1)
	            pointer1 = 0;
	          }
	          buffer1[pointer1][0] = reference1[i];
	          buffer1[pointer1][1] = 1;
	          fault1++;
	          pointer1++;
	          if(pointer1 == frames1)
	           pointer1 = 0;
	         }
	            for(int j = 0; j < frames1; j++)
	   {
	    mem_layout1[i][j] = buffer1[j][0];
	    used_layout1[i][j] = buffer1[j][1];
	   }
	        }
	        
	        for(int i = 0; i < frames1; i++)
	        {
	            for(int j = 0; j < ref_len1; j++)
	                System.out.printf("%3d %d ",mem_layout1[j][i],used_layout1[j][i]);
	            System.out.println();
	        }
	        
	        System.out.println("The number of Hits: " + hit1);
	        System.out.println("Hit Ratio: " + (float)((float)hit1/ref_len1));
	        System.out.println("The number of Faults: " + fault1);
	    
		case 4:
			BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
	        int frames3, pointer3 = 0, hit3 = 0, fault3 = 0,ref_len3;
	        boolean isFull3 = false;
	        int buffer3[];
	        int reference3[];
	        int mem_layout3[][];
	        
	        System.out.println("Please enter the number of Frames: ");
	        frames3 = Integer.parseInt(br3.readLine());
	        
	        System.out.println("Please enter the length of the Reference string: ");
	        ref_len3 = Integer.parseInt(br3.readLine());
	        
	        reference3 = new int[ref_len3];
	        mem_layout3 = new int[ref_len3][frames3];
	        buffer3 = new int[frames3];
	        for(int j = 0; j < frames3; j++)
	                buffer3[j] = -1;
	        
	        System.out.println("Please enter the reference string: ");
	        for(int i = 0; i < ref_len3; i++)
	        {
	            reference3[i] = Integer.parseInt(br3.readLine());
	        }
	        System.out.println();
	        for(int i = 0; i < ref_len3; i++)
	        {
	         int search3 = -1;
	         for(int j = 0; j < frames3; j++)
	         {
	          if(buffer3[j] == reference3[i])
	          {
	           search3 = j;
	           hit++;
	           break;
	          } 
	         }
	         if(search3 == -1)
	         {
	          if(isFull3)
	          {
	           int index3[] = new int[frames3];
	           boolean index_flag3[] = new boolean[frames3];
	           for(int j = i + 1; j < ref_len3; j++)
	           {
	            for(int k = 0; k < frames3; k++)
	            {
	             if((reference3[j] == buffer3[k]) && (index_flag3[k] == false))
	             {
	              index3[k] = j;
	              index_flag3[k] = true;
	              break;
	             }
	            }
	           }
	           int max3 = index3[0];
	           pointer3 = 0;
	           if(max3 == 0)
	            max3 = 200;
	           for(int j = 0; j < frames3; j++)
	           {
	            if(index3[j] == 0)
	             index3[j] = 200;
	            if(index3[j] > max3)
	            {
	             max3 = index3[j];
	             pointer3 = j;
	            }
	           }
	          }
	          buffer3[pointer3] = reference3[i];
	          fault3++;
	          if(!isFull3)
	          {
	           pointer3++;
	              if(pointer3 == frames3)
	              {
	               pointer3 = 0;
	               isFull3 = true;
	              }
	          }
	         }
	            for(int j = 0; j < frames3; j++)
	                mem_layout3[i][j] = buffer3[j];
	        }
	        
	        for(int i = 0; i < frames3; i++)
	        {
	            for(int j = 0; j < ref_len3; j++)
	                System.out.printf("%3d ",mem_layout3[j][i]);
	            System.out.println();
	        }
	        
	        System.out.println("The number of Hits: " + hit3);
	        System.out.println("Hit Ratio: " + (float)((float)hit3/ref_len3));
	        System.out.println("The number of Faults: " + fault3);
	    
		case 5:
		break;
		}
	     }	while(ch!=5);
	   }
	}

