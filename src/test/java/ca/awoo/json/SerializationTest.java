package ca.awoo.json;

import org.junit.Test;

public class SerializationTest {

    private static class TestClass {
        private int a;
        private String b;
        private boolean c;
        private double d;
        private float e;
        private long f;
        private short g;
        private byte h;
        private char i;
        private int[] j;
        private String[] k;
        private boolean[] l;
        private double[] m;
        private float[] n;
        private long[] o;
        private short[] p;
        private byte[] q;
        private char[] r;
        private TestClass2 s;
        private TestClass2[] t;
    }

    private static class TestClass2 {
        private int a;
        private String b;
        private boolean c;
        private double d;
        private float e;
        private long f;
        private short g;
        private byte h;
        private char i;
        private int[] j;
        private String[] k;
        private boolean[] l;
        private double[] m;
        private float[] n;
        private long[] o;
        private short[] p;
        private byte[] q;
        private char[] r;
    }

    private static class SmallClass {
        private String a;
        private boolean b;

        public SmallClass(String a, boolean b) {
            this.a = a;
            this.b = b;
        }
    }

    @Test
    public void test() throws JsonSerializationException {
        SmallClass sc = new SmallClass("Hello", true);
        Json json = new Json();
        json.defaultConfig();
        System.out.println(json.toJson(sc, SmallClass.class));
    }
    
}
