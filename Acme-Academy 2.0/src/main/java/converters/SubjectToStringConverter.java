package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Subject;

@Component
@Transactional
public class SubjectToStringConverter implements Converter<Subject,String>{
	
	@Override
	public String convert(Subject s){
		String res;
		if (s==null)
			res = null;
		else
			res = String.valueOf(s.getId());
		return res;
	}

}
