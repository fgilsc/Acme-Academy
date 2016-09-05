package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Group;

@Component
@Transactional
public class GroupToStringConverter implements Converter<Group,String>{
	
	@Override
	public String convert(Group g){
		String res;
		if (g==null)
			res = null;
		else
			res = String.valueOf(g.getId());
		return res;
	}

}
