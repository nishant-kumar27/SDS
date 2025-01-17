package retailsols;

import java.math.*;

public class BigDecimalTest {
  public static void main(String[] args) {
    BigDecimal bd = new BigDecimal(24362463.23);
 
    if (bd.compareTo(BigDecimal.ZERO)== 1) {
      System.out.println(bd);
    }
    
 // create 2 BigDecimal objects
    BigDecimal bg1, bg2;

    bg1 = new BigDecimal("10");
    bg2 = new BigDecimal("20");

    //create int object
    int res;

    res = bg1.compareTo(bg2); // compare bg1 with bg2

    String str1 = "Both values are equal ";
    String str2 = "First Value is greater ";
    String str3 = "Second value is greater";

    if( res == 0 )
       System.out.println( str1 );
    else if( res == 1 )
       System.out.println( str2 );
    else if( res == -1 )
       System.out.println( str3 );
 }

    
  }

 