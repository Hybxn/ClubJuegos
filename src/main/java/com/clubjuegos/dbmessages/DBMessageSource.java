package com.clubjuegos.dbmessages;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import com.clubjuegos.models.LenguageEntity;
import com.clubjuegos.repositories.LenguageEntityRepository;

@Component("messageSource")
public class DBMessageSource extends AbstractMessageSource{
	
	@Autowired
    private LenguageEntityRepository languageRepository;
	
	private static final String DEFAULT_LOCALE_CODE = "es";

	@Override
	protected MessageFormat resolveCode(String key, Locale locale) {
		LenguageEntity message = languageRepository.findByKeyAndLocale(key,locale.getLanguage());
		if (message == null) {
		    message = languageRepository.findByKeyAndLocale(key,DEFAULT_LOCALE_CODE);
		}
		if (message != null) {
			return new MessageFormat(message.getContent(), locale);			
		}
		return new MessageFormat(key);
	}

}
