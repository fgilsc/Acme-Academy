package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.StudentRepository;

import domain.Student;

@Component
@Transactional
public class StringToStudentConverter implements Converter<String,Student>{
	
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public Student convert(String text){
		Student res;
		int id;
		try{
			if(StringUtils.isEmpty(text)){
				res = null;
			}else{
				id = Integer.valueOf(text);
				res = studentRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
