package ca.awoo.json;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class SerializationTest {

    public static class TestClass {
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

        public TestClass(){}

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
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + intfield;
            result = prime * result + ((stringfield == null) ? 0 : stringfield.hashCode());
            result = prime * result + (booleanfield ? 1231 : 1237);
            long temp;
            temp = Double.doubleToLongBits(doublefield);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            result = prime * result + Float.floatToIntBits(floatfield);
            result = prime * result + (int) (longfield ^ (longfield >>> 32));
            result = prime * result + shortfield;
            result = prime * result + bytefield;
            result = prime * result + charfield;
            result = prime * result + Arrays.hashCode(intarrayfield);
            result = prime * result + Arrays.hashCode(stringarrayfield);
            result = prime * result + Arrays.hashCode(booleanarrayfield);
            result = prime * result + Arrays.hashCode(doublearrayfield);
            result = prime * result + Arrays.hashCode(floatarrayfield);
            result = prime * result + Arrays.hashCode(longarrayfield);
            result = prime * result + Arrays.hashCode(shortarrayfield);
            result = prime * result + Arrays.hashCode(bytearrayfield);
            result = prime * result + Arrays.hashCode(chararrayfield);
            result = prime * result + ((t2 == null) ? 0 : t2.hashCode());
            result = prime * result + Arrays.hashCode(t2arrayfield);
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            TestClass other = (TestClass) obj;
            if (intfield != other.intfield)
                return false;
            if (stringfield == null) {
                if (other.stringfield != null)
                    return false;
            } else if (!stringfield.equals(other.stringfield))
                return false;
            if (booleanfield != other.booleanfield)
                return false;
            if (Double.doubleToLongBits(doublefield) != Double.doubleToLongBits(other.doublefield))
                return false;
            if (Float.floatToIntBits(floatfield) != Float.floatToIntBits(other.floatfield))
                return false;
            if (longfield != other.longfield)
                return false;
            if (shortfield != other.shortfield)
                return false;
            if (bytefield != other.bytefield)
                return false;
            if (charfield != other.charfield)
                return false;
            if (!Arrays.equals(intarrayfield, other.intarrayfield))
                return false;
            if (!Arrays.equals(stringarrayfield, other.stringarrayfield))
                return false;
            if (!Arrays.equals(booleanarrayfield, other.booleanarrayfield))
                return false;
            if (!Arrays.equals(doublearrayfield, other.doublearrayfield))
                return false;
            if (!Arrays.equals(floatarrayfield, other.floatarrayfield))
                return false;
            if (!Arrays.equals(longarrayfield, other.longarrayfield))
                return false;
            if (!Arrays.equals(shortarrayfield, other.shortarrayfield))
                return false;
            if (!Arrays.equals(bytearrayfield, other.bytearrayfield))
                return false;
            if (!Arrays.equals(chararrayfield, other.chararrayfield))
                return false;
            if (t2 == null) {
                if (other.t2 != null)
                    return false;
            } else if (!t2.equals(other.t2))
                return false;
            if (!Arrays.equals(t2arrayfield, other.t2arrayfield))
                return false;
            return true;
        }
        
        
    }

    public static class TestClass2 {
        private int aField;

        public TestClass2(){}

        public TestClass2(int aField) {
            this.aField = aField;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + aField;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            TestClass2 other = (TestClass2) obj;
            if (aField != other.aField)
                return false;
            return true;
        }

        
    }

    /**
     * Tests the serialization and deserialization of a complex object
     * @throws Exception if an error occurs
     */
    @Test
    public void serializerComplexObject() throws Exception {
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
        String s = json.toJson(tc, TestClass.class);
        System.out.println(s);
        TestClass tc2 = json.fromJson(s, TestClass.class);
        System.out.println(tc2);
        assertEquals("Before and after json", tc, tc2);

    }
    
    /**
     * Tests the serialization and deserialization of an int
     * @throws Exception if an error occurs
     */
    @Test
    public void testInt() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(1, int.class);
        System.out.println(s);
        int i = json.fromJson(s, int.class);
        System.out.println(i);
        assertEquals("Before and after json", 1, i);
    }

    /**
     * Tests the serialization and deserialization of a string
     * @throws Exception if an error occurs
     */
    @Test
    public void testString() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson("Hello", String.class);
        System.out.println(s);
        String i = json.fromJson(s, String.class);
        System.out.println(i);
        assertEquals("Before and after json", "Hello", i);
    }

    /**
     * Tests the serialization and deserialization of a boolean
     * @throws Exception if an error occurs
     */
    @Test
    public void testBoolean() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(true, boolean.class);
        System.out.println(s);
        boolean i = json.fromJson(s, boolean.class);
        System.out.println(i);
        assertEquals("Before and after json", true, i);
    }

    /**
     * Tests the serialization and deserialization of a double
     * @throws Exception if an error occurs
     */
    @Test
    public void testDouble() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(1.0, double.class);
        System.out.println(s);
        double i = json.fromJson(s, double.class);
        System.out.println(i);
        assertEquals("Before and after json", 1.0, i, 0.0001);
    }

    /**
     * Tests the serialization and deserialization of a float
     * @throws Exception if an error occurs
     */
    @Test
    public void testFloat() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(1.0f, float.class);
        System.out.println(s);
        float i = json.fromJson(s, float.class);
        System.out.println(i);
        assertEquals("Before and after json", 1.0f, i, 0.0001);
    }

    /**
     * Tests the serialization and deserialization of a long
     * @throws Exception if an error occurs
     */
    @Test
    public void testLong() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(1L, long.class);
        System.out.println(s);
        long i = json.fromJson(s, long.class);
        System.out.println(i);
        assertEquals("Before and after json", 1L, i);
    }

    /**
     * Tests the serialization and deserialization of a short
     * @throws Exception if an error occurs
     */
    @Test
    public void testShort() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson((short)1, short.class);
        System.out.println(s);
        short i = json.fromJson(s, short.class);
        System.out.println(i);
        assertEquals("Before and after json", (short)1, i);
    }

    /**
     * Tests the serialization and deserialization of a byte
     * @throws Exception if an error occurs
     */
    @Test
    public void testByte() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson((byte)1, byte.class);
        System.out.println(s);
        byte i = json.fromJson(s, byte.class);
        System.out.println(i);
        assertEquals("Before and after json", (byte)1, i);
    }

    /**
     * Tests the serialization and deserialization of a char
     * @throws Exception if an error occurs
     */
    @Test
    public void testChar() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson('a', char.class);
        System.out.println(s);
        char i = json.fromJson(s, char.class);
        System.out.println(i);
        assertEquals("Before and after json", 'a', i);
    }

    /**
     * Tests the serialization and deserialization of an int array
     * @throws Exception if an error occurs
     */
    @Test
    public void testIntArray() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(new int[]{1, 2, 3}, int[].class);
        System.out.println(s);
        int[] i = json.fromJson(s, int[].class);
        System.out.println(Arrays.toString(i));
        assertArrayEquals("Before and after json", new int[]{1, 2, 3}, i);
    }

    /**
     * Tests the serialization and deserialization of a string array
     * @throws Exception if an error occurs
     */
    @Test
    public void testStringArray() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(new String[]{"a", "b", "c"}, String[].class);
        System.out.println(s);
        String[] i = json.fromJson(s, String[].class);
        System.out.println(Arrays.toString(i));
        assertArrayEquals("Before and after json", new String[]{"a", "b", "c"}, i);
    }

    /**
     * Tests the serialization and deserialization of a boolean array
     * @throws Exception if an error occurs
     */
    @Test
    public void testBooleanArray() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(new boolean[]{true, false}, boolean[].class);
        System.out.println(s);
        boolean[] i = json.fromJson(s, boolean[].class);
        System.out.println(Arrays.toString(i));
        assertArrayEquals("Before and after json", new boolean[]{true, false}, i);
    }

    /**
     * Tests the serialization and deserialization of a double array
     * @throws Exception if an error occurs
     */
    @Test
    public void testDoubleArray() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(new double[]{1.1, 2.2, 3.3}, double[].class);
        System.out.println(s);
        double[] i = json.fromJson(s, double[].class);
        System.out.println(Arrays.toString(i));
        assertArrayEquals("Before and after json", new double[]{1.1, 2.2, 3.3}, i, 0.0001);
    }

    /**
     * Tests the serialization and deserialization of a float array
     * @throws Exception if an error occurs
     */
    @Test
    public void testFloatArray() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(new float[]{1.1f, 2.2f, 3.3f}, float[].class);
        System.out.println(s);
        float[] i = json.fromJson(s, float[].class);
        System.out.println(Arrays.toString(i));
        assertArrayEquals("Before and after json", new float[]{1.1f, 2.2f, 3.3f}, i, 0.0001f);
    }

    /**
     * Tests the serialization and deserialization of a long array
     * @throws Exception if an error occurs
     */
    @Test
    public void testLongArray() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(new long[]{1, 2, 3}, long[].class);
        System.out.println(s);
        long[] i = json.fromJson(s, long[].class);
        System.out.println(Arrays.toString(i));
        assertArrayEquals("Before and after json", new long[]{1, 2, 3}, i);
    }

    /**
     * Tests the serialization and deserialization of a short array
     * @throws Exception if an error occurs
     */
    @Test
    public void testShortArray() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(new short[]{1, 2, 3}, short[].class);
        System.out.println(s);
        short[] i = json.fromJson(s, short[].class);
        System.out.println(Arrays.toString(i));
        assertArrayEquals("Before and after json", new short[]{1, 2, 3}, i);
    }

    /**
     * Tests the serialization and deserialization of a byte array
     * @throws Exception if an error occurs
     */
    @Test
    public void testByteArray() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(new byte[]{1, 2, 3}, byte[].class);
        System.out.println(s);
        byte[] i = json.fromJson(s, byte[].class);
        System.out.println(Arrays.toString(i));
        assertArrayEquals("Before and after json", new byte[]{1, 2, 3}, i);
    }

    /**
     * Tests the serialization and deserialization of a char array
     * @throws Exception if an error occurs
     */
    @Test
    public void testCharArray() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(new char[]{'a', 'b', 'c'}, char[].class);
        System.out.println(s);
        char[] i = json.fromJson(s, char[].class);
        System.out.println(Arrays.toString(i));
        assertArrayEquals("Before and after json", new char[]{'a', 'b', 'c'}, i);
    }

    /**
     * Tests the serialization and deserialization of an object
     * @throws Exception if an error occurs
     */
    @Test
    public void testObject() throws Exception {
        Json json = new Json();
        json.defaultConfig();
        String s = json.toJson(new TestClass2(1), TestClass2.class);
        System.out.println(s);
        TestClass2 i = json.fromJson(s, TestClass2.class);
        System.out.println(i);
        assertEquals("Before and after json", new TestClass2(1), i);
    }


}
