package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import domain.Assigment;

@Component
@Transactional
public class AssigmentToStringConverter implements Converter<Assigment,String>{
	
	@Override
	public String convert(Assigment a){
		String res;
		if (a==null)
			res = null;
		else
			res = String.valueOf(a.getId());
		return res;
	}

}
