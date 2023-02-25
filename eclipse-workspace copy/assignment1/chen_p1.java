import java.util.Arrays;

class chen_p1
{
// constructor for the class                                                                                                                                                                                
public chen_p1()
{

}
// method to sort an array of string values using selection sort                                                                                                                                            
// sort the strings by length and then alphabetically                                                                                                                                                       
public String[] selectionSort(String[] values, int lowerBound, int upperBound)
{
        int lb=lowerBound;
        int ub=upperBound;

        int minn;
        String a;
        String minstr;
        String valuesj;

        for(int i=0;i<values.length-1;i++){
        		if((i>=lb) && (i<=ub)) {
                minn=i;
                minstr=values[i];
                for(int j=i+1; j<values.length; j++){
                	if((j>=lb) && (j<=ub)) {
                		if(values[j].length()<minstr.length()) {
                           minstr=values[j];
                           minn=j;
                           }
                		if(values[j].length()==minstr.length()) {
                			if(values[j].compareTo(minstr)<0) {
                				minstr=values[j];
                                minn=j;
                			}
                		}
                	}
                }
            
                if(minn != i){
                a=values[i];
                values[i]=values[minn];
                values[minn]=a;
                }
        	}   
        }
                return values;


                }

//method to return the number of array elements > testValue with indices in                                                                                                                                
//[lowerBound, upperBound] using a for loop to examine the array elements                                                                                                                                  
public int forLoopTest(int[] values, int lowerBound, int upperBound, int testValue)
{
     int lb=lowerBound;
     int ub=upperBound;
     int tvalue=testValue;

     int cur=0;
     int counter=0;

     for(int i=0;i<values.length;i++){
             cur=values[i];
             if((i <= ub) && (i >= lb) && (cur > tvalue)){
                     counter++;
             }
     }

     return counter;

}
//method to return the number of array elements <= testValue with indices in                                                                                                                               
//[lowerBound, upperBound) using a while loop to examine the array elements                                                                                                                                
public int whileLoopTest(int[] values, int lowerBound, int upperBound, int testValue)
{
     int lb=lowerBound;
     int ub=upperBound;
     int tvalue=testValue;

     int cur=0;
     int counter=0;
     int i=0;

     while(i<values.length){
             cur=values[i];
             if((i < ub) && (i >= lb) && (cur <= tvalue)){
                     counter++;
             }
             i++;
     }
     return counter;
}
//method to return the number of array elements in (testValue1, testValue2)                                                                                                                                
//or (testValue2, testValue1) with indices in [lowerBound, upperBound] using                                                                                                                               
//a do-while loop to examine the array elements                                                                                                                                                            
public int doWhileLoopTest(int[] values, int lowerBound, int upperBound, int testValue1, int
testValue2)
{
     int lb=lowerBound;
     int ub=upperBound;
     int tvalue1=testValue1;
     int tvalue2=testValue2;

     int cur=0;
     int counter=0;
     int i=0;

     do{
             cur=values[i];
             if((i <= ub) && (i >= lb)){
                     if(((cur<tvalue1)&&(cur>tvalue2)) || ((cur>tvalue1)&&(cur<tvalue2))){
                             counter++;
                     }
             }
             i++;
     }while(i<values.length);

     return counter;

}
//method to return the number of array elements that match the switch cases                                                                                                                                
//[0, 3, 6, 9, 12, 15, 18, 21, 24, 27] and the default case with indices in                                                                                                                                
//(lowerBound, upperBound)                                                                                                                                                                                 
public int[] switchTest(int[] values, int lowerBound, int upperBound)
{
     int lb=lowerBound;
     int ub=upperBound;
     int tvalue=0;

     int[] result={0,0,0,0,0,0,0,0,0,0,0};

     for(int i=0;i<values.length;i++){
             if((i<ub) && (i>lb)){
                     tvalue=values[i];
                     switch(tvalue){
                             case 0:
                                     result[0]++;
                                     break;
                             case 3:
                                     result[1]++;
                             case 6:
                                     result[2]++;
                                     break;
                             case 9:
                                     result[3]++;
                             case 12:
                                     result[4]++;
                                     break;
                             case 15:
                                     result[5]++;
                             case 18:
                                     result[6]++;
                                     break;
                             case 21:
                                     result[7]++;
                             case 24:
                                     result[8]++;
                                     break;
                              case 27:
                                      result[9]++;
                              default:
                                      result[10]++;
                                      break;
                      }
              }
      }
      return result;

}

}


