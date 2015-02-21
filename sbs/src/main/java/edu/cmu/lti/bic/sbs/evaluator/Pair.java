package edu.cmu.lti.bic.sbs.evaluator;

/**
 * 
 * This Pair Class is to support the backtrack function of 
 *  the ScoreDP class.
 *  
 * @author Ryan Sun
 *
 */
public class Pair {
  
  
    private static Object o1;
    private static Object o2;
    
    /**
     * 
     * The constructor of the Pair Class
     * 
     * @param o1
     * @param o2
     */
    public Pair(Object o1, Object o2) 
    { 
      this.o1 = o1; this.o2 = o2; 
    }
   
    
    /**
     * 
     * The isSame function to compare the two object in the pair
     *  if they are the same, return true
     *  otherwise return false
     * 
     * @param o1
     * @param o2
     * @return
     */
    
    public static boolean isSame(Object o1, Object o2) 
    {
      if(o1 == null && o2 == null)
      {
        return true;
      }
      else if(o1 == null)
      {
        return false;
      }
      else if(o2 == null)
      {
        return false;
      }
      else
      {
        return o1 == o2;
      }
    }
   
    /**
     * 
     * function to get the first Object
     * 
     * @return
     */
    public Object getFirst() 
    { 
      return o1; 
    }
    
    /**
     * 
     * function to get the second Object
     * 
     * @return
     */
    public Object getSecond() 
    { 
      return o2; 
    }
   
    /**
     * 
     * function to set the first Object
     * 
     * @return
     */
    public void setFirst(Object o) 
    {
      o1 = o; 
    }
    
    /**
     * 
     * function to set the second Object
     * 
     * @return
     */
    public void setSecond(Object o) 
    { 
      o2 = o; 
    }
   
    
    /**
     * 
     * the equals function overriding the one in Object
     * compare the Object obj with this Object
     * 
     */
    public boolean equals(Object obj) {
      if( ! (obj instanceof Pair))
      {
        return false;
      }
      Pair p = (Pair) obj;
      
      return isSame(p.o1, this.o1) 
              && isSame(p.o2, this.o2);
    }
   
    
    public String toString() 
    {
      return "Pair{"+o1+", "+o2+"}";
    }
   
      /**
       * Simple example test program.
       */
      public static void main(String[] args) {
          Pair
              p1 = new Pair("a", "b"),
              p2 = new Pair("a", null),
              p3 = new Pair("a", "b"),
              p4 = new Pair(null, null);
          System.out.println(p1.equals(new Pair(new Integer(1), new Integer(2))) + " should be false");
          System.out.println(p4.equals(p2) + " should be false");
          System.out.println(p2.equals(p4) + " should be false");
          System.out.println(p1.equals(p3) + " should be true");
          System.out.println(p4.equals(p4) + " should be true");
      }


      /**
       * @return the o1
       */
      public static Object getO1() {
        return o1;
      }


      /**
       * @param o1 the o1 to set
       */
      public static void setO1(Object o1) {
        Pair.o1 = o1;
      }


      /**
       * @return the o2
       */
      public static Object getO2() {
        return o2;
      }


      /**
       * @param o2 the o2 to set
       */
      public static void setO2(Object o2) {
        Pair.o2 = o2;
      }

      
}
