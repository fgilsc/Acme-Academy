package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.LearningMaterial;

@Component
@Transactional
public class LearningMaterialToStringConverter implements Converter<LearningMaterial,String>{
	
	@Override
	public String convert(LearningMaterial lm){
		String res;
		if (lm==null)
			res = null;
		else
			res = String.valueOf(lm.getId());
		return res;
	}	
}
