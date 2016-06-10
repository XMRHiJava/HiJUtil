package hij.util.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import hij.util.HiCBO;
import hij.util.HiTypeHelper;
import hij.util.generic.IFuncP1;

public class TestHiCBO {
	@Test
	public void test_CBO() {
		TestObj obj = new TestObj();
		boolean ret = HiCBO.fillObject(obj, TestObj.class, new IFuncP1<String, String>(){

			@Override
			public String handle(String v) {
				if (v.equals("X")) {
					return "test";
				}
				if (v.equals("Y")) {
					return "3";
				}
				if (v.equals("Z")) { 
					return "2.7";
				}
				if (v.equals("Date")) {
					return "2016-05-19";
				}
				if (v.equals("Date2")) {
					return "2016-05-19";
				}
				if (v.equals("Date3")) {
					return "2016-05-19 14:03:14";
				}
				if (v.equals("Date4")) {
					return "2016-05-19 02:03:14";
				}
				return null;
			}
			
		});
		Assert.assertTrue(ret);
		Assert.assertEquals(obj.getX(), "test");
		Assert.assertEquals(obj.getY(), 3);
		Assert.assertEquals(obj.getY2(), -1);
		Assert.assertTrue(obj.getZ() == 2.7f);
		Assert.assertEquals(obj.getDate(), HiTypeHelper.convert2Date("2016-05-19"));
		Assert.assertEquals(obj.getDate2(), HiTypeHelper.convert2SqlDate("2016-05-19"));
		Assert.assertEquals(obj.getDate3(), HiTypeHelper.convert2Date("2016-05-19 14:03:14"));
		Assert.assertEquals(obj.getDate4(), HiTypeHelper.convert2SqlDate("2016-05-19 02:03:14"));
		Assert.assertEquals(obj.getDate5(), null);
	}

	@Test
	public void test_CBOEx() {
		TestObj obj = new TestObj();
		boolean ret = HiCBO.fillObjectEx(obj, TestObj.class, new IFuncP1<String, Object>(){

			@Override
			public Object handle(String v) {
				if (v.equals("X")) {
					return "test";
				}
				if (v.equals("Y")) {
					return 3;
				}
				if (v.equals("Z")) { 
					return 2.7;
				}
				if (v.equals("Date")) {
					return "2016-05-19";
				}
				if (v.equals("Date2")) {
					return "2016-05-19";
				}
				if (v.equals("Date3")) {
					return "2016-05-19 14:03:14";
				}
				if (v.equals("Date4")) {
					return "2016-05-19 02:03:14";
				}
				return null;
			}
			
		});
		Assert.assertTrue(ret);
		Assert.assertEquals(obj.getX(), "test");
		Assert.assertEquals(obj.getY(), 3);
		Assert.assertEquals(obj.getY2(), -1);
		Assert.assertTrue(obj.getZ() == 2.7f);
		Assert.assertEquals(obj.getDate(), HiTypeHelper.convert2Date("2016-05-19"));
		Assert.assertEquals(obj.getDate2(), HiTypeHelper.convert2SqlDate("2016-05-19"));
		Assert.assertEquals(obj.getDate3(), HiTypeHelper.convert2Date("2016-05-19 14:03:14"));
		Assert.assertEquals(obj.getDate4(), HiTypeHelper.convert2SqlDate("2016-05-19 02:03:14"));
		Assert.assertEquals(obj.getDate5(), null);
	}
	
	@Test
	public void test_Date2String() {
		java.sql.Date dt = HiTypeHelper.convert2SqlDate("2016-5-19 2:3:14");
		String dtStr = HiTypeHelper.toLongString(dt);

		Assert.assertEquals(dtStr, "2016-05-19 02:03:14");
		dtStr = HiTypeHelper.toShortString(dt);

		Assert.assertEquals(dtStr, "2016-05-19");
		

		java.util.Date dt2 = HiTypeHelper.convert2Date("2016-5-19 2:3:14");
		String dtStr2 = HiTypeHelper.toLongString(dt2);

		Assert.assertEquals(dtStr2, "2016-05-19 02:03:14");
		dtStr2 = HiTypeHelper.toShortString(dt2);

		Assert.assertEquals(dtStr2, "2016-05-19");
	}
	
	enum EnumTest
	{
		x,
		y
	}
	@Test
	public void test_ValueType() {
		Class<?> up = Number.class.getSuperclass();
		Assert.assertNotNull(up);
		up = Integer.class.getSuperclass();
		Assert.assertNotNull(up);
		up = String.class.getSuperclass();
		Assert.assertNotNull(up);
		up = Float.class.getSuperclass();
		Assert.assertNotNull(up);
		up = char.class.getSuperclass();
		Assert.assertNull(up);
		up = int.class.getSuperclass();
		Assert.assertNull(up);
		up = float.class.getSuperclass();
		Assert.assertNull(up);
		up = EnumTest.class.getSuperclass();
		Assert.assertNotNull(up);
		up = Enum.class.getSuperclass();
		Assert.assertNotNull(up);
		up = Object.class.getSuperclass();
		Assert.assertNull(up);
	}

	@Test
	public void test_Cast() {
		int x = Integer.class.cast(-1);
		Assert.assertEquals(x, -1);
	}
	@Test
	public void test_HiTypeHelper_Cast() {
		Integer val = 1;
		int ret = HiTypeHelper.cast(int.class, val);
		Assert.assertEquals(ret, 1);
		String text = "1";
		ret = HiTypeHelper.cast(int.class, text);
		Assert.assertEquals(ret, 1);
		text = "a";
		ret = HiTypeHelper.cast(int.class, text);
		Assert.assertEquals(ret, -1);
		int val2 = 1;
		Integer ret2 = HiTypeHelper.cast(Integer.class, val2);
		Assert.assertEquals(ret2.intValue(), 1);
		text = "1";
		ret2 = HiTypeHelper.cast(Integer.class, text);
		Assert.assertEquals(ret2.intValue(), 1);
		boolean bl = HiTypeHelper.cast(boolean.class, 1);
		Assert.assertTrue(bl);
		bl = HiTypeHelper.cast(boolean.class, 3);
		Assert.assertTrue(bl);
		bl = HiTypeHelper.cast(boolean.class, 0);
		Assert.assertFalse(bl);
		bl = HiTypeHelper.cast(boolean.class, -1);
		Assert.assertFalse(bl); 
		bl = HiTypeHelper.cast(boolean.class, "1");
		Assert.assertTrue(bl);
		bl = HiTypeHelper.cast(boolean.class, "3");
		Assert.assertTrue(bl);
		bl = HiTypeHelper.cast(boolean.class, "0");
		Assert.assertFalse(bl);
		bl = HiTypeHelper.cast(boolean.class, "-1");
		Assert.assertFalse(bl);
	}
	@Test
	public void test_HiTypeHelper_Number() {
		Assert.assertTrue(HiTypeHelper.isNumer(int.class));
		Assert.assertTrue(HiTypeHelper.isNumer(BigDecimal.class));
		Assert.assertTrue(HiTypeHelper.isNumer(float.class));
		Assert.assertTrue(HiTypeHelper.isNumer(double.class));
		Assert.assertTrue(HiTypeHelper.isNumer(long.class));
		Assert.assertTrue(HiTypeHelper.isNumer(short.class));
		Assert.assertTrue(HiTypeHelper.isNumer(boolean.class));
		Assert.assertTrue(HiTypeHelper.isNumer(char.class));
		Assert.assertTrue(HiTypeHelper.isNumer(Float.class));
		Assert.assertTrue(HiTypeHelper.isNumer(Double.class));
		Assert.assertTrue(HiTypeHelper.isNumer(Long.class));
		Assert.assertTrue(HiTypeHelper.isNumer(Short.class));
		Assert.assertTrue(HiTypeHelper.isNumer(byte.class));
		Assert.assertTrue(HiTypeHelper.isNumer(Byte.class));
		Assert.assertFalse(HiTypeHelper.isNumer(Boolean.class));
		Assert.assertFalse(HiTypeHelper.isNumer(void.class));
		Assert.assertFalse(HiTypeHelper.isNumer(Void.class));
		Assert.assertFalse(HiTypeHelper.isNumer(EnumTest.class));
		Assert.assertFalse(HiTypeHelper.isNumer(Object.class));
	}

	@Test
	public void test_HiTypeHelper_GetDefault() {
		BigDecimal val = HiTypeHelper.getDefault(BigDecimal.class);
		Assert.assertEquals(val, new BigDecimal(-1));
		int val2 = HiTypeHelper.getDefault(int.class);
		Assert.assertEquals(val2, -1);
		Integer val3 = HiTypeHelper.getDefault(Integer.class);
		Assert.assertEquals(val3, new Integer(-1));
		boolean bl = HiTypeHelper.getDefault(boolean.class);
		Assert.assertFalse(bl);
		Boolean bl2 = HiTypeHelper.getDefault(Boolean.class);
		Assert.assertFalse(bl2);
	}
	@Test
	public void test_ToString() {
		TestObj obj = new TestObj();
		obj.setX("test");
		obj.setY(1);
		obj.setDate2(null);
		String str = HiTypeHelper.ToString(TestObj.class, obj);
		Assert.assertTrue(str != null);
		System.out.print(str);
	}
	
	class X{
		
	}
	@Test
	public void test_ToString_null(){
		X x = null;
		String str =  HiTypeHelper.ToString(X.class, x);
		Assert.assertEquals(str, "");
	}

}
