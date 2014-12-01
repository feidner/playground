package hfe;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TicTac {
	
	@Test
	public void hfe() {
		
		List<String> ticTac = Arrays.asList("XXX", "XX0", "AAA");
		ticTac.sort( (a, b) -> a.compareTo(b));		
		System.out.println(ticTac);
	}
	

}
