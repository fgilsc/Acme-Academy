package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Deliverable;

@Component
@Transactional
public class DeliverableToStringConverter  implements Converter<Deliverable,String> {
	
	@Override
	public String convert(Deliverable d){
		String res;
		if (d==null)
			res = null;
		else
			res = String.valueOf(d.getId());
		return res;
	}

}
