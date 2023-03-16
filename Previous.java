// todo: Find the next previous element for every element in Unsorted Array without using any Sorting Technique and only using Stack Data Structure...

import java.util.Scanner;
import java.util.Stack;
public class Previous
{
    //! Node class to define Node properties...
    public class Node
    {
        public int data;
        public Node next;
        public Node prev;
        public Node(int value)
        {
            this.data = value;
            next = null; prev = null;
        }
    }
    Node head;     //? Head Node pointer created...
    public void InsertNode(int val)     //! function to Insert Node in the Linked List... o(n) time...
    {
        Node newNode = new Node(val);
        if(head == null)     // If Linked List is empty...
        {
            head = newNode;
            newNode.next = head;     // Making the Linked List Circular...
            newNode.prev = null;
            return;
        }
        Node temp = head;          // If the Linked List is Not Empty...
        while(temp.next != head)
            temp = temp.next;
        temp.next = newNode;
        newNode.next = head;     // Making the Linked List Circular...
        newNode.prev = temp;
        return;
    }
    public void PrintCircular()    //! Function to print the Linked List... O(n) time...
    {
        Node temp = head;
        System.out.print(temp.data+" -> ");
        temp = temp.next;
        while(temp.next != head)
        {
            System.out.print(temp.data+" -> ");
            temp = temp.next;
        }
        System.out.print(temp.data+" -> ");
        System.out.println(temp.next.data);
        return;
    }
    //! Function to find the next Greater element for every Element in the Circular List... O(n^2) time...
    public void FindPrevious(Stack<Integer> sck)  
    {
        Node temp = head;
        int gr = 0;
        while(temp.next != head)     // Accessing one Node at a time... O(n) time...
        {
            gr = GetPrevious(sck, temp.data);    //? Nested Function Call... O(n) time...
            System.out.println("The previous number than "+temp.data+" is : "+gr);
            while(!sck.isEmpty())
                sck.pop();
            temp = temp.next;
        }
        gr = GetPrevious(sck, temp.data);
        System.out.println("The previous number than "+temp.data+" is : "+gr);
        return;
    }
    //! The Function to check the next Greater element... O(n) time...
    public int GetPrevious(Stack<Integer> sck, int t)
    {
        Node temp1 = head;
        while(temp1.next != head)     // Iterating through the Linked List once...
        {
            if(temp1.data < t)
            {
                if(sck.isEmpty())     // When the Stack is Empty...
                    sck.push(temp1.data);
                else
                {
                    if(sck.peek() < temp1.data)
                        sck.push(temp1.data);     // Top pointer updation...
                    else
                    {
                        int x = sck.pop();       // Top pointer updation...
                        sck.push(temp1.data);
                        sck.push(x);
                    }
                }
            }
            temp1 = temp1.next;
        }
        if(temp1.data < t)    //! Method specific for the last Node checking...
            {
                if(sck.isEmpty())
                    sck.push(temp1.data);
                else
                {
                    if(sck.peek() < temp1.data)
                        sck.push(temp1.data);
                    else
                    {
                        int x = sck.pop();
                        sck.push(temp1.data);
                        sck.push(x);
                    }
                }
            }
        if(sck.isEmpty())     // If the Stack was Empty... No larger element was found...
            return -1;
        return sck.peek();     // If the Stack was not Empty... Some larger element was found...
    }
    //! Main Function begins...
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int x, a;
        Previous previous = new Previous();
        Stack<Integer> stack = new Stack<Integer>(); 
        System.out.print("Enter the number of Nodes in an Array : ");
        x = scanner.nextInt();
        for(int i = 0; i < x; i++)
        {
            System.out.print("Enter the Node value : ");
            a = scanner.nextInt();
            previous.InsertNode(a);    //? Function call... O(n) time...
        }
        previous.PrintCircular();      //? Function call... O(n) time...
        previous.FindPrevious(stack);   //? Function call... O(n^2) time...
        scanner.close();
    }
}


//* The Time Complexity is O(n^2) time... Since there are two nested functions... Each having O(n) time complexity... */
//* The Space Complexity is O(n-1) for Stacks and O(n) for the Linked List... */
//! Time Complexity -  O(n^2) time...
//! Space Complexity - O(n) space...