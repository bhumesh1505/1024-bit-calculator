
// CPL Assignment ... 
//Roll Number : BT16CSE012 
//Name : BHUMESH BHOYAR
package star;
import java.util.*;

public class Star {

    static String Add(String m , String n)
    {
        String result="",SubStringM,SubStringN,sumString,zero;
        int carry=0,i,j,lengthM,lengthN,stopM=0,stopN=0,lenSum;
        long m18,n18,sum; 

        int signM=0,signN=0;
        if(m.charAt(0) == '-' )
        {
            signM = 1;
            m = m.substring(1);
        }
        if(n.charAt(0) == '-')
        {
            signN = 1;
            n = n.substring(1);
        }
        
        if(signM == signN)      // same sign so add m + n 
        {
            lengthM = m.length();
            lengthN = n.length();
            i=lengthM;
            j=lengthN;
            while( stopM==0 || stopN==0)
            {
                if(stopM==0)
                {
                    if(i>18)
                    {
                        SubStringM = m.substring(i-18,i);
                        m18 = Long.parseLong(SubStringM);
                    }
                    else
                    {
                        SubStringM = m.substring(0,i);
                        m18 = Long.parseLong(SubStringM);
                        stopM = 1;
                    }
                }
                else
                {
                    m18 = 0;
                }
                
                if(stopN==0)
                {
                    if(j>18)
                    {
                        SubStringN = n.substring(j-18,j);
                        n18 = Long.parseLong(SubStringN);
                    }
                    else
                    {
                        SubStringN = n.substring(0,j);
                        n18 = Long.parseLong(SubStringN);
                        stopN = 1;
                    }
                }
                else
                {
                    n18 = 0;
                }
                
                sum = m18 + n18 + carry;
                carry = 0;
                sumString = Long.toString(sum); 
                lenSum = sumString.length();

                if(lenSum < 18 && (stopN == 0 || stopM == 0))
                {
                    zero = "";
                    for(int p=0;p<(18-lenSum);p++)
                    {
                        zero = "0" + zero ;
                    }
                    sumString = zero + sumString ;
                }
                result = sumString + result;
                
                if(lenSum > 18 ) // 18 thimes
                {
                    carry = 1;
                    result = result.substring(1); // exclude 1st digit whichc is carry thing
                }
                i = i-18;
                j = j-18;
            }
            if(carry==1)
            {
                result = "1" + result ;
            }
            if(signN == 1)   //both signN and signM are same (-ve)
            {
                result = "-" + result;
            }
        }
        else if( signM == 1 && signN == 0)
        {
            result = SubBigNumber(n,m); // n-m
        }
        else if(signM == 0 && signN == 1)
        {
            result = SubBigNumber(m,n); // m-n
        }
        return result;
    }

    static String subtract(String m,String n,int lengthM ,int lengthN)
    {
        int i,j,stopM=0,stopN=0,sign=0,l,lenSub;
        long m18,n18,sub,borrow=0;
        String SubStringM = "",SubStringN,result="",subtractString="",zero;
        
        i=lengthM;
        j=lengthN;
        
        while( stopM==0 || stopN==0)
        {
            if(stopM==0)
            {
                if(i>18)
                {
                    SubStringM = m.substring(i-18,i);
                    m18 = Long.parseLong(SubStringM);
                }
                else
                {
                    SubStringM = m.substring(0,i);
                    m18 = Long.parseLong(SubStringM);
                    stopM = 1;
                }
            }
            else
            {
                m18 = 0;
            }
            
            if(stopN==0)
            {
                if(j>18)
                {
                    SubStringN = n.substring(j-18,j);
                    n18 = Long.parseLong(SubStringN);
                }
                else
                {
                    SubStringN = n.substring(0,j);
                    n18 = Long.parseLong(SubStringN);
                    stopN = 1;
                }
            }
            else
            {
                n18 = 0;
            }
            n18 = n18 + borrow;

            if(m18 >= n18)
            {
                sub = m18 - n18 ;
                borrow = 0;
            }
            else        //  take borrow
            {
                l = SubStringM.length();
                borrow = 1;
                m18 = m18 + (long)(Math.pow(10,l));
                sub = m18 - n18;
            }

            subtractString = Long.toString(sub);
            lenSub = subtractString.length();
            if(lenSub < 18 && (stopN == 0 || stopM == 0))
            {
                zero = "";
                for(int p=0;p<(18-lenSub);p++)
                {
                    zero = "0" + zero ;
                }
                subtractString = zero + subtractString ;
            }
            result = subtractString + result;
            i = i-18;
            j = j-18;
        }
        
        return result;
    }
    
    static String SubBigNumber(String m , String n)
    {
        String result;
        int lengthN,lengthM;

        int signM=0,signN=0;
        
        if(m.charAt(0) == '-' )
        {
            signM = 1;
            m = m.substring(1);
        }
        if(n.charAt(0) == '-')
        {
            signN = 1;
            n = n.substring(1);
        }
        
        if(signN != signM)  // add 
        {
            result = Add(m,n);
            if(signM == 1)
            {
                result = "-" + result;
            }
        }
        else    // subtraction
        {
            lengthM = m.length();
            lengthN = n.length();
            
            if(lengthM > lengthN)
            {
                result = Star.subtract(m,n,lengthM,lengthN);
                if(signM == 1)  //m is larger 
                {
                    result = "-" + result;
                }
            }
            else if(lengthM < lengthN)
            {
                result = Star.subtract(n,m,lengthN,lengthM);
                if(signN == 0)  // -m - (-n) = -m+n
                {
                    result = "-"  + result;
                }
            }
            else
            {
                if(m.compareTo(n) > 0)
                {
                    result = Star.subtract(m,n,lengthM,lengthN);  
                    if(signM == 1)  //m is larger 
                    {
                        result = "-" + result;
                    }     
                }
                else if(m.compareTo(n) < 0)
                {
                    result = Star.subtract(n,m,lengthN,lengthM); 
                    if(signN == 0) // -m - (-n) = -m+n
                    {
                        result = "-"  + result;
                    }
                }
                else
                {
                    result = "0" ;
                }
            }
        } 
        return result;
    }    

    static String Multiplication(String m , String n)
    {
        String result="",partialProduct="",SubStringM,SubStringN,productString,productLowString;
        int i,j,lengthM,lengthN,stopM=0,stopN=0,timesEnterInM=0,lenResult,productLength;
        long n9,sum,prod,productLow,productHigh=0,previous;
        long m9[];
        int signM=0,signN=0;

        if(m.charAt(0) == '-' )
        {
            signM = 1;
            m = m.substring(1);
        }
        if(n.charAt(0) == '-')
        {
            signN = 1;
            n = n.substring(1);
        }
        lengthM = m.length();
        lengthN = n.length();

        int size = (int)(lengthM/9) + 2 ;
        m9 = new long[size];
        stopM = 0;
        i=lengthM;
        size = 0;
        while(stopM == 0)
        {
            if(i>9)
            {
                SubStringM = m.substring(i-9,i);
                m9[size] = Long.parseLong(SubStringM);
                size++;
            }
            else
            {
                SubStringM = m.substring(0,i);
                m9[size] = Long.parseLong(SubStringM);
                stopM = 1;
                size++;
            }
            i=i-9;
        }

        j=lengthN;

            while(stopN==0)
            {
                if(j>9)
                {
                    SubStringN = n.substring(j-9,j);
                    n9 = Long.parseLong(SubStringN);
                }
                else
                {
                    SubStringN = n.substring(0,j);
                    n9 = Long.parseLong(SubStringN);
                    stopN = 1;
                }
                timesEnterInM++;
                previous = 0;
                for(i=0;i<size;i++)
                {
                    prod = m9[i] * n9 + previous; 
                    productHigh = prod / 1000000000;
                    
                    productString = Long.toString(prod);
                    productLength = productString.length();
                    if(productLength >= 9)
                    {
                       productLowString = productString.substring(productLength-9,productLength);            
                    }
                    else
                    {
                        productLowString = productString.substring(0,productLength); // block is not of size 9
                        if(stopM == 0)
                        {
                            int l = productLowString.length();
                            while(l<9)
                            {
                                productLowString = "0" + productLowString;
                                l++;
                            }
                        }    
                    }
                    partialProduct = productLowString + partialProduct;
                    previous = productHigh;
                }
                if(productHigh != 0)
                {
                    partialProduct = Long.toString(productHigh) + partialProduct;                  
                }
                if(result.length() > 0)
                {
                    result = Add(result,partialProduct);   
                }
                else
                {
                    result = partialProduct;
                }
                partialProduct = "";
                for(int p=0;(p<timesEnterInM && stopN == 0);p++)
                {
                    partialProduct = "000000000" ;
                }
                j=j-9;
            }


            if(signN != signM)
            {
                result = "-" + result ;
            }

            return result;
    }
    
    public static void main(String[] args) {

        Scanner x = new Scanner(System.in);
        String m,n,result,operation;
        System.out.println("Enter 1st Number:\n");
        m = x.nextLine();
        System.out.println("Opration : + - * \n");
        operation = x.nextLine();
        System.out.println("Enter 2nd Number:\n");
        n = x.nextLine();
            
        long lenResult;

        switch(operation)
        {
            case "+" :  
                                result = Add(m,n);
                                lenResult = result.length();
                                if(lenResult>310)
                                {
                                    System.out.println("Overflowed");
                                }
                                System.out.println("Result = " + result + "\n" );
                                break;

            case "-" :  
                                result = SubBigNumber(m,n);
                                lenResult = result.length();
                                if(lenResult>310)
                                {
                                    System.out.println("Overflowed");
                                }
                                System.out.println("Result = " + result + "\n" );
                                break;

            case "*" :  
                                result = Multiplication(m,n);
                                lenResult = result.length();
                                if(lenResult>310)
                                {
                                    System.out.println("Overflowed");
                                }
                                System.out.println("Result = " + result + "\n" );
                                break;

            default:    break;
        }
    }
}
