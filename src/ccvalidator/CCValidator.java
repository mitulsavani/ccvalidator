/* Mitul Savani
   11/14/2016
   Whether Credit card is valid or not.
 */
package ccvalidator;
import java.util.Arrays;


public class CCValidator {
    
    //it returns the total digit in the number. Parameter: 'd' with datatype long
    public static int getSize(long d)   
    {
        int count=0;                            //creating and initializing the variable 'count', which I will use to count the no. of digit in the credit card number.
        double lastdigit=0;                     //varialbe to get the last digit from the credit card number.
        
        //this loop will count the number of digit in a credit card number.
        while(d!=0)
        {
            lastdigit=d%10;                     //extracting the last digit
            count++;                            //incrementing the value of count.
            d=d/10;                             //cutting of the last digit
        }
        return count;                           //returning the count value (i.e) the number of digits.
    }
    
     /**
     * this method will Return the first k number of digits from number. If the number of digits
     * in 'number' is less than k, return number.
     * Note: This method is exactly same as given by the instructor 
     */
    public static long getPrefix(long number, int k)        
    {
        int size = getSize(number);             
        if(size < k) return number;             
        int remove_count = size - k;
        while (remove_count > 0) {
            number /= 10;
            remove_count--;
        }
        return number;
    }
    
     /**
     * this method will Return true if the digit d is a prefix for number
     */
    public static boolean prefixMatched(long number, int d) 
    {
        int size=getSize((long)d);
        long prefix=getPrefix((number),size);       //calling other method named as 'getPrefix' to get the prefix of the the credit card number
        return prefix==d;    //checking wether the return value is same as d.
    }
     
    /**
     * This method will Return number if it is a single digit, otherwise, it will return the sum
     * of the two digits
     */
    public static int getDigit(int number)      //parameter: 'number' with datatype integer
    {
      int LastDigit=0;                          //creating variable 'LastDigit' to pair the two numbers
      int FirstDigit=0;                         //creating variable 'FirstDigit' to get the even place place digit
      int sum=0;
      if(number>9)                              //checking wether the number is single sigit or contain more than one digit
      {
       
        LastDigit=number%10;                    //getting tha last digit
        FirstDigit=number/10;                   //extracting the last digit
        
        sum=LastDigit+FirstDigit;               //sumation of the two digit
        return sum;                             //returning th sum of the digit
        
      }
      return number;                            //if it is single digit that returning that digit
    }
    
    /**
     * This method will Return sum of odd place digits in number
     */
    public static int sumOfOddPlace(long number) 
    {
        long sum=0;                             //this varaible will do the sum of the number
        long lastdigit=0;                       //this variable will give the last digit
        while(number!=0)                        //while loop will the number becomes 0.
        {
            lastdigit=number%10;                //getting the last digit
            sum=sum+lastdigit;                  //sumation of the even place digit of the credit card number
            number=number/100;                  //extracting two digits from the end
        }
        return (int)sum;                        //returning the sumation of the even place digit, need to do type casting because initially the sum was Long
    }
    
    //This method will do the double of the even place digit of the credit card.
    public static int sumOfDoubleEvenPlace(long number) 
    {
        long sum=0;                             //This variable will do the summation of the all the even place (2*digit)
        long pairoftwo=0;                       //This variable will give the pair of two digit from the end
        long evenplace=0;                       //This will give the even place digit
        long doubleofdigit=0;                   //This variable will do the double of even plac digit
        while(number!=0)                        //loop will run until the number becomes 0.
        {
            pairoftwo=number%100;               //getting the two last digit from the end
            evenplace=pairoftwo/10;             //now extracting the the last digit from the above pair of two digit
            doubleofdigit=2*evenplace;          //(2*digit of even place)
            
            
            sum=sum+getDigit((int)doubleofdigit);   //summation of all the even place of (2*digit)
            
            number=number/100;                  //extracting the last two digit from the credit card number
        }
        return (int)sum;                        //returning the sum, need to do type casting cause initially the variable 'sum' was long.
    }
    
     /**
     * Return true if the card number is valid
     *
     * This function will take a credit card number as its parameter. It will
     * first determine if the card belongs to one of the given vendors 
     *
     * Then it will compute the two sums needed for the mod 10 check.
     *
     * And, finally it will perform the mod 10 check.
     */
    public static boolean isValid(long number) 
    {
        if(isValidVendor(number)==true)                 //calling method 'isValidVendor' to check whether the prefix of the credit card number is valid
        {
            int sumeven=sumOfDoubleEvenPlace(number);   //calling the method 'sumOfDoubleEvenPlace' and storing the value in the variable 'sumeven'
            int sumodd=sumOfOddPlace(number);           //calling the method 'sumOfOddPlace' and storing the value in the variable 'sumodd'
       
            if((sumeven+sumodd)%10==0)                  //Checking wether the sum of odd place and even place is divisible by 10, if condition is satisfied then it will return true.
            {
            return true;                                
            }
        }
       return false;                                    //Will return false is the prefix of credit card number is not matched with the valid prefix(i.e) 4,5,6,37.
    }
    
    //This method will check if the credit card prefix is whether checking the valid prefix of the valid card number (i.e) 4,5,6,37
    public static boolean isValidVendor(long number) 
    {
        if(prefixMatched(number, 4)==true || prefixMatched(number, 5)==true || prefixMatched(number,6)==true|| prefixMatched(number,37)==true) //calling the method prefixMatched, to know whether the the prefix is matching
        {
            return true;                                //will return true if the above condition is satisfied
        }
        return false;                                   //will return false if the prefix is not valid.
    }
    
    public static void main(String[] args) {

        /**
         *  Begin testing for getSize 
		 *  Inputs: 213,452343,21312
    	 *  Outputs: 3,6,5
         */
        int[] ins = {213, 452343, 21312};
        int[] outs = {3, 6, 5};
        int size = -1;
        for (int num = 0; num < ins.length; num++)
        {
            size = getSize(ins[num]);
            if (size != outs[num]) {
                System.out.println("getSize test has Failed\nInput : " + ins[num] + "\nOutput: " + size + "\nExpected Output: " + outs[num]);
                System.exit(-1);
            }
        }
        System.out.println("All getSize tests have passed");

        /**
         * Begin testing for getPrefix 
         * Inputs: (213,1),(452343,3),(21312,6)
         * Outputs: 2,452,21312
         */
        int[][] t1_ins = {{213,1},{452343,3}, {21312,6}};
        int[] t1_outs = {2, 452, 21312};
        long pref = -1;
        for (int num = 0; num < t1_ins.length; num++) {
            pref = getPrefix(t1_ins[num][0],t1_ins[num][1]);
            if (pref != t1_outs[num]) {
                System.out.println("getPrefix test has Failed\nInput : " + Arrays.toString(t1_ins[num]) + "\nOutput: " + pref + "\nExpected Output: " + t1_outs[num]);
                System.exit(-1);
            }
        }
        System.out.println("All getPrefix tests have passed");
        
        /**
         * Begin testing for prefixMatched
         * Inputs: (213,1),(452343,3),(21312,6)
         * Outputs: 2,452,21312
         */
        int[][] t2_ins = {{213,21},{452343,452}, {21312,21312},{56547,23}};
        boolean[] t2_outs = {true, true, true,false};
        boolean boolMatched = false;
        for (int num = 0; num < t2_ins.length; num++) {
            boolMatched = prefixMatched(t2_ins[num][0],t2_ins[num][1]);
            if (boolMatched  != t2_outs[num]) {
                System.out.println("prefixMatched test has Failed\nInput : " + Arrays.toString(t2_ins[num]) + "\nOutput: " + boolMatched + "\nExpected Output: " + t2_outs[num]);
                System.exit(-1);
            }
        }
        System.out.println("All prefixMatched tests have passed");
        
        /**
         * Begin testing for getDigit
         * Inputs: 12,1,18,2,4
         * Outputs: 3,1,9,2,4
         */
        int[] t3_ins = {12,1,18,2,4};
        int[] t3_outs = {3,1,9,2,4};
        int d = -1;
        for (int num = 0; num < t3_ins.length; num++) {
            d = getDigit(t3_ins[num]);
            if (d  != t3_outs[num]) {
                System.out.println("getDigit test has Failed\nInput : " + t3_ins[num] + "\nOutput: " + d + "\nExpected Output: " + t3_outs[num]);
                System.exit(-1);
            }
        }
        System.out.println("All getDigit tests have passed");
        
         /**
         * Begin testing for sumofOddPlace
         * Inputs: 46546556465465l,42568559665995l,46543333465465l,465465789358565l
         * Outputs: 36,42,31,46
         */
        long[] t4_ins = {46546556465465l,42568559665995l,46543333465465l,465465789358565l};
        int[] t4_outs = {36,42,31,46};
        int sum_odd = -1;
        for (int num = 0; num < t4_ins.length; num++) {
            sum_odd = sumOfOddPlace(t4_ins[num]);
            if (sum_odd  != t4_outs[num]) {
                System.out.println("sumOfOddPlace test has Failed\nInput : " + t4_ins[num] + "\nOutput: " + sum_odd + "\nExpected Output: " + t4_outs[num]);
                System.exit(-1);
            }
        }
        System.out.println("All sumOfOddPlace tests have passed");
        
         /**
         * Begin testing for sumofOddPlace
         * Inputs: 46546556465465l,42568559665995l,46543333465465l,465465789358565l
         * Outputs: 36,42,31,46
         */
        long[] t5_ins = {46546556465465l,42568559665995l,46543333465465l,465465789358565l};
        int[] t5_outs = {25,30,33,35};
        int sum_even = -1;
        for (int num = 0; num < t5_ins.length; num++) {
            sum_even = sumOfDoubleEvenPlace(t5_ins[num]);
            if (sum_even  != t5_outs[num]) {
                System.out.println("sumOfDoubleEvenPlace test has Failed\nInput : " + t5_ins[num] + "\nOutput: " + sum_even + "\nExpected Output: " + t5_outs[num]);
                System.exit(-1);
            }
        }
        System.out.println("All sumOfDoubleEvenPlace tests have passed");
        
        //Valid CC Number
        long validTest = 4388576018410707L;
        //invalid CC number
        long inValidTest = 4388576018402626L;

        System.out.println("Credit Card Number " + validTest + (isValid(validTest) ? " is Valid" : " is Invalid"));
        System.out.println("Credit Card Number " + inValidTest + (isValid(inValidTest) ? " is Valid" : " is Invalid"));
    }

}
