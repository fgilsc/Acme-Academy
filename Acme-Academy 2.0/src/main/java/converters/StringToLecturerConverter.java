package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.LecturerRepository;

import domain.Lecturer;

@Component
@Transactional
public class StringToLecturerConverter implements Converter<String,Lecturer>{
	
	@Autowired
	LecturerRepository lecturerRepository;
	
	@Override
	public Lecturer convert(String text){
		Lecturer res;
		int id;
		try{
			if(StringUtils.isEmpty(text)){
				res = null;
			}else{
				id = Integer.valueOf(text);
				res = lecturerRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
