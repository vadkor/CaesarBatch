package rms.caesar.batch.processor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CaesarProcessorTest {

	@Test
	public void testShift1() {
		String ciphertext = CaesarProcessor.encode("HAL", 1);
		assertEquals("IBM", ciphertext);
	}

	@Test
	public void testShift25() {
		String ciphertext = CaesarProcessor.encode("abc", 25);
		assertEquals("zab", ciphertext);
	}
}
