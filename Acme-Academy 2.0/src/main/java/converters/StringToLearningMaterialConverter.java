package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.LearningMaterialRepository;

import domain.LearningMaterial;


@Component
@Transactional
public class StringToLearningMaterialConverter implements Converter<String,LearningMaterial>{
	
	@Autowired
	LearningMaterialRepository lmRepo;
	
	@Override
	public LearningMaterial convert(String text){
		LearningMaterial res;
		int id;
		try{
			if(StringUtils.isEmpty(text)){
				res = null;
			}else{
				id = Integer.valueOf(text);
				res = lmRepo.findOne(id);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
