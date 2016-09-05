package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Rubric;

@Component
@Transactional
public class RubricToStringConverter implements Converter<Rubric,String>{
	
	@Override
	public String convert(Rubric r){
		String res;
		if (r==null)
			res = null;
		else
			res = String.valueOf(r.getId());
		return res;
	}

}
