import java.security.PublicKey;

/**
 * Created by Rafael on 4/8/2016.
 */
class Ordering<Base> {
    private class Pair{
        Base left;
        Base right;
        Pair next;
        public Pair(Base left,Base right, Pair next){
            this.left=left;
            this.right=right;
            this.next= next;
        }
    }
    private class Element{
        Base object;
        Element next;
        public Element(Base object,Element next){
            this.object=object;
            this.next=next;
        }
    }
    Pair pairs;
    Element elements;

    public Ordering(){
        pairs = null;
        elements = new Element(null,null);
    }
    public boolean isEmpty(){
        return (elements.next==null);
    }
    private boolean isElement(Base object){
        Element temp = elements.next;
        //boolean flag = false;
        while(temp !=null){
            if(temp.object.equals(object)){
                return true;
            }
            else{
                temp = temp.next;
            }
        }
        return false;
    }
    private boolean isPair(Base left,Base right){
        Pair temp = pairs;
        //boolean flag = false;
        while(temp != null){
            if (temp.left.equals(left) && temp.right.equals(right)){
                //flag = true;
                //break;
                return true;
            }
            else{
                temp = temp.next;
            }
        }
        //return flag;
        return false;
    }
    private boolean isMinimum(Base right){
        Element temp = elements.next;
        //boolean flag = true;
        while(temp != null){
            if(isPair(temp.object,right)){
                //flag = false;
                return false;

            }
            else{
                temp = temp.next;
            }
        }
        //return flag;
        return true;
    }
    private Base minimum(){
        Element left = elements;
        Element right = elements.next;
        while (right!=null){
            if (isMinimum(right.object)){
                left.next = right.next;
                //System.out.println(right.object);
                return right.object;
            }
            else{
                left = right;
                right = right.next;
            }
        }
        throw new IllegalArgumentException("No minimum");
    }
    public void precedes(Base left, Base right){
        if(left==null || right == null){
            throw new IllegalArgumentException("One or both of your arguments are NULL");
        }
        else {
            pairs = new Pair(left, right, pairs);

            if (!isElement(left)) {
                elements.next = new Element(left, elements.next);
                //System.out.println(elements.next.object);
            }
            if (!isElement(right)) {
                elements.next = new Element(right, elements.next);
            }
        }
    }
    public String satisfy(){
        String a = "";
        while(!isEmpty()){
            Base temp = minimum();
            if(elements.next!=null) {
                a = a+ temp + " < ";
            }
            else{
                a = a+temp;
            }
        }
        return a;
    }

}

class PastramiOnRye
{
    public static void main(String [] args)
    {
        Ordering<String> ordering = new Ordering<String>();

        ordering.precedes("A", "B");
        ordering.precedes("A", "C");
        ordering.precedes("B", "D");
        ordering.precedes("B", "J");
        ordering.precedes("C", "E");
        ordering.precedes("D", "F");
        ordering.precedes("D", "H");
        ordering.precedes("E", "H");
        ordering.precedes("F", "C");
        ordering.precedes("G", "E");
        ordering.precedes("G", "I");
        ordering.precedes("I", "D");
        ordering.precedes("I", "J");

//  This should print G < I < A < B < J < D < F < C < E < H.

        System.out.println(ordering.satisfy());
    }
}
/*
OUTPUT
G < I < A < B < J < D < F < C < E < H
*/