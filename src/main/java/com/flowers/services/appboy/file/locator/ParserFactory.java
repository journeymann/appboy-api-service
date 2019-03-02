package com.flowers.services.appboy.file.locator;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.Set;
import java.util.WeakHashMap;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flowers.services.appboy.constants.Constants;
import com.flowers.services.appboy.constants.FeedType;
import com.flowers.services.appboy.facade.FunctionFacade;
import static com.flowers.services.appboy.facade.ParserFacade.*;
import com.flowers.services.appboy.file.xml.NullParser;
import com.flowers.services.appboy.file.xml.OrderFileParser;
import com.flowers.services.appboy.file.xml.FileParser;
import com.flowers.services.appboy.file.xml.GenericFileParser;
import com.flowers.services.appboy.thread.tasks.AbstractProcessFile;
import com.flowers.services.appboy.thread.tasks.NullProcessFile;

/**
 * @author cgordon
 * @created 10/17/2017
 * @version 1.0
 * 
 * ResourceInterface implementation (Abstract Factory design Pattern) that produces resource for parsing various (XML) file feeds. 
 * 
 */
@SuppressWarnings("unused")
public enum ParserFactory implements ResourceInterface{

	/** modern method for implementing singleton design pattern */
	INSTANCE;

	/**Cache-ing implemented using SoftReference so if cache not needed or being used then data structure 
	 * would be available for garbage collection by the JVM.
	 * 
	 *  @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/ref/SoftReference.html">Java SE 1.8 Document SoftReference Class</a>
	 * 
	 */
	private @NotNull SoftReference<Set<Class<? extends GenericFileParser>>> cache; 
	public static final transient Logger logger = LoggerFactory.getLogger(ParserFactory.class);
	
	/** do not allow no-arg object instantiation */
	private ParserFactory(){

	}

	public static ParserFactory getInstance(){
		return INSTANCE;
	}

	public FileParser getXmlParser(FeedType type) throws InstantiationException, IllegalAccessException{

		if(cache==null) cache = fetchCache();	

		/** parser type descriptor may not match file name search for like name key */
		GenericFileParser parser = cache.get().stream()
				.filter(t -> t.getName().toLowerCase().contains(String.valueOf(type).toLowerCase())).findFirst().orElse(NullParser.class).newInstance();

		logger.debug("PARSER:  Factory: type: {}, class: {} \n", type.toString(), parser);
		return parser; 
	}	
	
	private @NotNull SoftReference<Set<Class<? extends GenericFileParser>>> fetchCache() {
		
		return new SoftReference<Set<Class<? extends GenericFileParser>>>((Set<Class<? extends GenericFileParser>>)getSetOfSubClasses(GenericFileParser.class, Constants.PARSE_RESOURCE_PACKAGE));
	}
}