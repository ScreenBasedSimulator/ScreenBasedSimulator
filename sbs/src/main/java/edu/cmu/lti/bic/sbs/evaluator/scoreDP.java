package edu.cmu.lti.bic.sbs.evaluator;

/**
 * 
 * This class, we try to use dynamic programming algorithm to 
 * implement the score generation function for the Evaluator.
 * 
 * @author Ryan Sun
 *
 */
public class scoreDP {
  
  
  
  private static double[][] matrix;
  
  /**
   * constructor of the scoreDP class
   * 
   */
  
  public scoreDP(){
  }
  
  /**
   * Main function of scoreDP class,
   * The method will receive two parameters
   *  one is the Golden Standard Path,
   *  another is the real Path from the user
   *  
   */
  
  public static double scoreDP(Path p1, Path p2){
    // firstly, check which one is the golden standard path
    // make sure p1 always the golden standard path
    if(!p1.getTag().equals("Gold Standard")){
       if(!p2.getTag().equals("Gold Standard")){
          throw new IllegalArgumentException(
                  "None of the input is golden standard path, illegal input!");
       }else{
         return scoreDP(p2, p1);
       }
    }
    
    int l1 = p1.size();
    int l2 = p2.size();
    matrix = new double[l1+1][l2+1];
    
    // the following is the DP algorithm
      // initialization for base cases:
      for(int i = 0; i <= l1; i++){
        matrix[i][0] = i*1;
      }
      for(int i = 0; i <= l2; i++){
        matrix[0][i] = i*1;
      }
      
      // the dp algorithm for String Alignment
      for(int i = 0; i <= l1; i++){
        for(int j = 0; j <= l2; j++){
            double match = matrix[i-1][j-1] 
                    + p1.get(i).stepScore(p2.get(j));
            double skip1 = matrix[i][j-1];
            double skip2 = matrix[i-1][j];
            matrix[i][j] = Math.max(match, 
                    Math.max(skip1, skip2));
        }
      }
     return matrix[l1][l2];
  }
  
  
  /**
   * 
   * Main function for Unit test
   *
   */
  
  public static void main(String[] args) {
    // The pathScore test case.
    // Due to the inefficient test case, calculating multiple scores 
    // cannot be achieved.
    Path p1 = new Path();
    Path p2 = new Path();
    System.out.println("The score is " + p1.pathScore(p2));
    // TODO: Test scoring function locally inside Path class.
    // p1.add(new Step())

      }
}
