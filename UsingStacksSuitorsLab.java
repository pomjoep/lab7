import java.util.LinkedList;
import java.util.Queue;

/** CSSSKL 162
 *
 * UsingStacksSuitorsLab
 *
 * This class is mostly a driver for playing with Strings as palindromes,
 * both iteratively and recursively.  The UsingStacksSuitorsLab class itself is
 * a runnable object, so it can be passed to a thread in our Queue demo
 *Quinn Epstein
 */

public class UsingStacksSuitorsLab implements Runnable {
    private static int threadCount = 0;
    private String name;

    public UsingStacksSuitorsLab() {
        name = "#" + threadCount++ + "Thread";
    }

    public static void main(String[] args) {
        String s1 = "food";		    //not a palindrome
        String s2 = "racecar";      //a palindrome

        System.out.println("String1 is \"" + s1 + "\"");
        System.out.println("String2 is \"" + s2 + "\"");

		System.out.println(s1 + " reversed is: ");
		printReverse(s1);
		System.out.println(s2 + " reversed is: ");
		printReverse(s2);

	    recPrintReverse(s1);
		System.out.println();
		recPrintReverse(s2);
		System.out.println();

		System.out.println(s1 + " is a palindrome: " + isPalindrome(s1));
		System.out.println(s2 + " is a palindrome: " + isPalindrome(s2));

		System.out.println(s1 + " is a palindrome(recursively): " + isPalindromeRec(s1));
		System.out.println(s2 + " is a palindrome(recursively): " + isPalindromeRec(s2));

		System.out.println("Did we build a Queue of Threads and start them? " + buildThreadQueue());

		int n = 6;
		System.out.println("For " + n + " suitors, stand in place:" + findPlaceToStand(n));

		n = 10;
		System.out.println("For " + n + " suitors, stand in place:" + findPlaceToStand(n));
    }




    public static void printReverse(String target) {
        //todo: use a stack
        LLStack stack = new LLStack();
        for (int i = 0; i < target.length(); i++) {
            stack.addToStart(target.charAt(i));
        }
        System.out.println(target + " reversed is: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.deleteHead());
        }
        System.out.println();
    }

    public static void recPrintReverse(String target) {
        //todo
        if (target.length() == 0) {
            return;
        }
        recPrintReverse(target.substring(1));
        System.out.print(target.charAt(0));
    }

    public static boolean isPalindrome(String input) {
        //todo: use a stack
        LLStack stack = new LLStack();
        for (int i = 0; i < input.length(); i++) {
            stack.addToStart(input.charAt(i));
        }
        for (int i = 0; i < input.length(); i++) {
            if (!stack.deleteHead().equals(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeRec(String sentence)	{
        //todo
        if (sentence.length() <= 1) {
            return true;
        }
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) {
            return false;
        }
        return isPalindromeRec(sentence.substring(1, sentence.length() - 1));
    }

    public static Object findPlaceToStand(int numSuitors) {
        //todo
        LLQueue queue = new LLQueue();
        for (int i = 1; i <= numSuitors; i++) {
            queue.offer(i);
        }
        while (queue.size() > 1) {
            queue.offer(queue.poll());
            queue.poll();
        }
        return queue.poll();
    }


    public static boolean buildThreadQueue() {	//returns true upon success
        Queue<Thread> q = new LinkedList<Thread>();

        //when our program starts up, it might create multiple threads to use
        q.offer( new Thread(new UsingStacksSuitorsLab()));
        q.offer( new Thread(new UsingStacksSuitorsLab()));
        q.offer( new Thread(new UsingStacksSuitorsLab()));

        System.out.println("Initial Thread order:");
        q.toString();

        //We need to iterate over our pool of threads and call start() on each one
        //Make a loop that dequeues a thread, calls start on it, and //enqueues it again
        //to do:
        //current = get a thread
        //current.start();
        //put the thread back

        System.out.println("Thread order after start()ing:");
        q.toString();

        return true;  //on successful start
    }


    /*
     * No need to edit anything below here,
     * unless you'd like to change the
     * behaviour of each thread in the thread pool above
     */

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            System.out.println(name + ": " + i + "th iteration");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                //do nothing here
            }
        }
    }
}
