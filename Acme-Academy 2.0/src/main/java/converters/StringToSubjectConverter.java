package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SubjectRepository;

import domain.Subject;

@Component
@Transactional
public class StringToSubjectConverter implements Converter<String,Subject>{
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Override
	public Subject convert(String text){
		Subject res;
		int id;
		try{
			if(StringUtils.isEmpty(text)){
				res = null;
			}else{
				id = Integer.valueOf(text);
				res = subjectRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
