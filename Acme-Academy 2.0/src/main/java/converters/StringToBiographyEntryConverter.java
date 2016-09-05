package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.BiographyEntry;

import repositories.BiographyEntryRepository;

@Component
@Transactional
public class StringToBiographyEntryConverter implements Converter<String,BiographyEntry>{
	
	@Autowired
	BiographyEntryRepository biographyEntryRepository;
	
	@Override
	public BiographyEntry convert(String text){
		BiographyEntry res;
		int id;
		try{
			if(StringUtils.isEmpty(text)){
				res = null;
			}else{
				id = Integer.valueOf(text);
				res = biographyEntryRepository.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
