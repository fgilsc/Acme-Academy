package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.AssigmentRepository;

import domain.Assigment;

@Component
@Transactional
public class StringToAssigmentConverter implements Converter<String,Assigment>{
	
	@Autowired
	AssigmentRepository assigmentRepository;
	
	@Override
	public Assigment convert(String text){
		Assigment res;
		int id;
		try{
			if(StringUtils.isEmpty(text)){
				res = null;
			}else{
				id = Integer.valueOf(text);
				res = assigmentRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
