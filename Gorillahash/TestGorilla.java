import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Andreas Blanke
 * @version 02-04-2017
 * @project bads
 */
public class TestGorilla {

    @Test
    public void testCos() {
        int[] a = new int[] {0,1};
        int[] b = new int[] {0,1};
        double angle = Gorilla.vectorCosAngle(a,b);
        printContents(a);
        printContents(b);
        System.out.println("Angle: "+angle+"\n");
        assertEquals(1.0,angle,0.001);
    }

    @Test
    public void testCosDiffValuesInFirst() {
        int[] a = new int[] {0,1};
        int[] b = new int[] {1,0};
        double angle = Gorilla.vectorCosAngle(a,b);
        printContents(a);
        printContents(b);
        System.out.println("Angle: "+angle);
        assertEquals(0.0,angle,0.001);
    }

    @Test
    public void testCosDiffValuesInSecond() {
        int[] a = new int[] {0,1};
        int[] b = new int[] {0,2};
        double angle = Gorilla.vectorCosAngle(a,b);
        printContents(a);
        printContents(b);
        System.out.println("Angle: "+angle);
        assertEquals(1.0,angle,0.001);
    }

    @Test
    public void testCosWithNegative() {
        int[] a = new int[] {0,1};
        int[] b = new int[] {0,-1};
        double angle = Gorilla.vectorCosAngle(a,b);
        printContents(a);
        printContents(b);
        System.out.println("Angle: "+angle);
        assertEquals(-1.0,angle,0.001);
    }

    @Test
    public void testCosManyNumbers() {
        int[] a = new int[10000];
        int[] b = new int[10000];
        a[0] = 1;
        b[9999] = 1;
        double angle = Gorilla.vectorCosAngle(a,b);
        printContents(a);
        printContents(b);
        System.out.println("Angle: "+angle);
        assertEquals(0.0,angle,0.001);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testCosWithDifferentD() {
        int[] a = new int[] {0,0,1};
        int[] b = new int[] {0,1};
        printContents(a);
        printContents(b);
        Gorilla.vectorCosAngle(a,b);
        System.out.println("ArrayIndexOutOfBoundsException not thrown!");
    }

    @Test
    public void testLength() {
        int[] a = new int[] {0,0,1};
        printContents(a);
        double length = Gorilla.vectorLength(a);
        System.out.println("Length: "+length);
        assertEquals(1.0,length,0.001);
    }

    @Test
    public void testLengthWithLarge() {
        int[] a = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
        printContents(a);
        double length = Gorilla.vectorLength(a);
        System.out.println("Length: "+length);
        assertEquals(1.0,length,0.001);
    }

    @Test
    public void testLargeLength() {
        int[] a = new int[] {0,10000};
        printContents(a);
        double length = Gorilla.vectorLength(a);
        System.out.println("Length: "+length);
        assertEquals(10000.0,length,0.001);
    }

    @Test
    public void testLengthNegative(){
        int[] a = new int[] {2,-3,6};
        printContents(a);
        double length = Gorilla.vectorLength(a);
        System.out.println("Length: "+length);
        assertEquals(7.0,length,0.001);
    }

    @Test
    public void testLengthRepeated() {
        int[] a = new int[] {2,4,2,4,2,4,2,4,2,4};
        printContents(a);
        double length = Gorilla.vectorLength(a);
        System.out.println("Length: "+length);
        assertEquals(10.0,length,0.001);
    }

    @Test
    public void testLengthDecimal() {
        int[] a = new int[] {1,2,3,4,5};
        printContents(a);
        double length = Gorilla.vectorLength(a);
        System.out.println("Length: "+length);
        assertEquals(7.416,length,0.001);
    }

    @Test
    public void testLengthFives() {
        int[] a = new int[] {5,5,5,5};
        printContents(a);
        double length = Gorilla.vectorLength(a);
        System.out.println("Length: "+length);
        assertEquals(10.0,length,0.001);
    }

    @Test
    public void testLengthOrdered() {
        int[] a = new int[] {3,4,5,3,4,5};
        printContents(a);
        double length = Gorilla.vectorLength(a);
        System.out.println("Length: "+length);
        assertEquals(10.0,length,0.001);
    }

    private void printContents(int[] array) {
        System.out.print("Contents: ");
        for (int number: array) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}