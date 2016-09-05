package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Assesment;

@Component
@Transactional
public class AssesmentToStringConverter implements Converter<Assesment,String>{
	
	@Override
	public String convert(Assesment a){
		String res;
		if (a==null)
			res = null;
		else
			res = String.valueOf(a.getId());
		return res;
	}

}
