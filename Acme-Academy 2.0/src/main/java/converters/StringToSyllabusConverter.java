package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Syllabus;

import repositories.SyllabusRepository;

@Component
@Transactional
public class StringToSyllabusConverter implements Converter<String,Syllabus>{
	
	@Autowired
	SyllabusRepository syllabusRepository;
	
	@Override
	public Syllabus convert(String text){
		Syllabus res;
		int id;
		try{
			if(StringUtils.isEmpty(text)){
				res = null;
			}else{
				id = Integer.valueOf(text);
				res = syllabusRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
