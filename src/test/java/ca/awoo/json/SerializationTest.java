package ca.awoo.json;

import org.junit.Test;

public class SerializationTest {

    private static class TestClass {
        private int          intfield;
        private String       stringfield;
        private boolean      booleanfield;
        private double       doublefield;
        private float        floatfield;
        private long         longfield;
        private short        shortfield;
        private byte         bytefield;
        private char         charfield;
        private int[]        intarrayfield;
        private String[]     stringarrayfield;
        private boolean[]    booleanarrayfield;
        private double[]     doublearrayfield;
        private float[]      floatarrayfield;
        private long[]       longarrayfield;
        private short[]      shortarrayfield;
        private byte[]       bytearrayfield;
        private char[]       chararrayfield;
        private TestClass2   t2;
        private TestClass2[] t2arrayfield;
        public TestClass(
            int intfield,
            String stringfield,
            boolean booleanfield,
            double doublefield,
            float floatfield,
            long longfield,
            short shortfield,
            byte bytefield,
            char charfield,
            int[] intarrayfield,
            String[] stringarrayfield,
            boolean[] booleanarrayfield,
            double[] doublearrayfield,
            float[] floatarrayfield,
            long[] longarrayfield,
            short[] shortarrayfield,
            byte[] bytearrayfield,
            char[] chararrayfield,
            TestClass2 t2,
            TestClass2[] t2arrayfield
            ) {
            this.intfield = intfield;
            this.stringfield = stringfield;
            this.booleanfield = booleanfield;
            this.doublefield = doublefield;
            this.floatfield = floatfield;
            this.longfield = longfield;
            this.shortfield = shortfield;
            this.bytefield = bytefield;
            this.charfield = charfield;
            this.intarrayfield = intarrayfield;
            this.stringarrayfield = stringarrayfield;
            this.booleanarrayfield = booleanarrayfield;
            this.doublearrayfield = doublearrayfield;
            this.floatarrayfield = floatarrayfield;
            this.longarrayfield = longarrayfield;
            this.shortarrayfield = shortarrayfield;
            this.bytearrayfield = bytearrayfield;
            this.chararrayfield = chararrayfield;
            this.t2 = t2;
            this.t2arrayfield = t2arrayfield;
        }

        
        
    }

    private static class TestClass2 {
        private int aField;

        public TestClass2(int aField) {
            this.aField = aField;
        }
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

    @Test
    public void serializerComplexObject() throws JsonSerializationException {
        TestClass tc = new TestClass(
            0,
            "Hello",
            false,
            0.5,
            0.6f,
            2000000000L,
            (short)10000,
            (byte)56,
            'w',
            new int[]{1, 2, 3, 4},
            new String[]{"a", "b", "c"},
            new boolean[]{true, false},
            new double[]{1.1, 2.2, 3.3},
            new float[]{1.1f, 2.2f, 3.3f},
            new long[]{1, 2, 3},
            new short[]{1, 2, 3},
            new byte[]{1, 2, 3},
            new char[]{'a', 'b', 'c'},
            new TestClass2(1),
            new TestClass2[]{new TestClass2(1), new TestClass2(2)}
            );
        Json json = new Json();
        json.defaultConfig();
        System.out.println(json.toJson(tc, TestClass.class));
    }
    
}
