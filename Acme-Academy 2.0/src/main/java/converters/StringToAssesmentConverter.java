package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.AssesmentRepository;

import domain.Assesment;


@Component
@Transactional
public class StringToAssesmentConverter implements Converter<String,Assesment>{
	
	@Autowired
	AssesmentRepository assesmentRepository;
	
	@Override
	public Assesment convert(String text){
		Assesment res;
		int id;
		try{
			if(StringUtils.isEmpty(text)){
				res = null;
			}else{
				id = Integer.valueOf(text);
				res = assesmentRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
