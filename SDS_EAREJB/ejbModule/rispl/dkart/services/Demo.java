package rispl.dkart.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		
		list.add(BigDecimal.ONE);
		list.add(BigDecimal.TEN);
		list.add(BigDecimal.ZERO);
		list.add(new BigDecimal(9));
		list.add(new BigDecimal(5));
		list.add(new BigDecimal(2));
		list.add(new BigDecimal(4));
		list.add(new BigDecimal(9));
		list.add(new BigDecimal(8));
		
		
		Collections.sort(list,Collections.reverseOrder());
		
		for(BigDecimal b :list){
			System.out.println(b);
		}
	}

}
