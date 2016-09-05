package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.BiographyEntry;

@Component
@Transactional
public class BiographyEntryToStringConverter implements Converter<BiographyEntry,String>{
	
	@Override
	public String convert(BiographyEntry s){
		String res;
		if (s==null)
			res = null;
		else
			res = String.valueOf(s.getId());
		return res;
	}

}
